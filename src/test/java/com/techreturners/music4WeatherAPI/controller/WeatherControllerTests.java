package com.techreturners.music4WeatherAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.music4WeatherAPI.model.Condition;
import com.techreturners.music4WeatherAPI.model.Current;
import com.techreturners.music4WeatherAPI.model.Location;
import com.techreturners.music4WeatherAPI.model.Weather;
import com.techreturners.music4WeatherAPI.service.Music4WeatherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class WeatherControllerTests {

    @InjectMocks
    private WeatherController weatherController;

    @Mock
    private Music4WeatherServiceImpl music4WeatherServiceImpl;

    @Mock
    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvcController;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(weatherController).build();
        ObjectMapper mapper = new ObjectMapper();

    }

    @Test
    public void testGetMappingGetWeatherDetails() throws Exception {

        String city = "london";

        Location location = new Location("London", "City of London, Greater London", "United Kingdom", "Europe/London", "2023-03-26 18:55");
        Condition condition = new Condition("Partly cloudy", "//cdn.weatherapi.com/weather/64x64/day/116.png", 1003);
        Current current = new Current(8, 1, condition, 10.5, 16.9, 30, 0, 61, 50, 10, 6, 2);
        Weather weatherData = new Weather(location, current);

        when(restTemplate.getForObject("http://api.weatherapi.com/v1/current.json?key=8ced75335ba84c23b40231355232003&q=" + city, Weather.class)).thenReturn(weatherData);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/weather/" + location.getName()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.name").value("London"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.region").value("City of London, Greater London"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.country").value("United Kingdom"));
    }

    @Test
    public void testGetMappingGetWeatherKeywordsByCity() throws Exception {

        String city = "london";

        Location location = new Location("London", "City of London, Greater London", "United Kingdom", "Europe/London", "2023-03-26 18:55");
        Condition condition = new Condition("Partly cloudy", "//cdn.weatherapi.com/weather/64x64/day/116.png", 1003);
        Current current = new Current(8, 1, condition, 10.5, 16.9, 30, 0, 61, 50, 10, 6, 2);
        Weather weatherData = new Weather(location, current);

        List<String> keywords = Arrays.asList("WINDY", "CLOUDY", "BREEZY");

        when(music4WeatherServiceImpl.getKeywords(weatherData)).thenReturn(keywords);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/keywords/" + location.getName()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
