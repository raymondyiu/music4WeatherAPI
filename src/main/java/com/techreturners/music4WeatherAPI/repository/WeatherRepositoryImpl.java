package com.techreturners.music4WeatherAPI.repository;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherRepositoryImpl implements WeatherRepository {

    private final RestTemplate restTemplate;

    String urlStub = "http://api.weatherapi.com/v1/current.json?key=";
    String apiKey = "3b4c926f0f3b44de8da145646232103";
    String rest = "&q=Oxford&aqi=no";

    public WeatherRepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public WeatherData getWeatherForCity(String city) {
        String url = urlStub + apiKey + rest;
        WeatherData response = restTemplate.getForObject(url, WeatherData.class);
        return response;
    }
}

