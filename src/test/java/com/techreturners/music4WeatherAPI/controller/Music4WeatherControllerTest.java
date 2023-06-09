package com.techreturners.music4WeatherAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.techreturners.music4WeatherAPI.service.Music4WeatherServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
class Music4WeatherControllerTest {

    @Mock
    private Music4WeatherServiceImpl music4WeatherServiceImp;

    @InjectMocks
    private Music4WeatherController music4WeatherController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @Mock
    private WeatherController weatherController;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(music4WeatherController).build();
        mapper = new ObjectMapper();
        ResponseEntity<List<String>> responseKeywords = new ResponseEntity<>(Arrays.asList("london","snowy","rainy"), HttpStatus.OK);;
        when(weatherController.getTerms(anyString())).thenReturn(responseKeywords);
    }
    @Test
    void getWelcomeMsgTest() throws Exception {
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/music4Weather/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void music4WeatherByCityTest() throws Exception {
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/music4Weather/london"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



}
