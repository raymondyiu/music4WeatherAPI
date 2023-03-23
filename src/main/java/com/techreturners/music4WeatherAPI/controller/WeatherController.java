package com.techreturners.music4WeatherAPI.controller;

import com.techreturners.music4WeatherAPI.model.Weather;
import com.techreturners.music4WeatherAPI.service.Music4WeatherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class WeatherController {

    @Autowired
    Music4WeatherServiceImpl service;

    @GetMapping({"/weather/{city}"})
    public ResponseEntity<Weather> getWeatherDetails(@PathVariable String city) {
        String uri = "http://api.weatherapi.com/v1/current.json?key=8ced75335ba84c23b40231355232003&q=" + city;
        RestTemplate restTemplate = new RestTemplate();

        Weather weather = restTemplate.getForObject(uri, Weather.class);

        return new ResponseEntity<>(weather,HttpStatus.OK);
    }

    @GetMapping({"/keywords/{city}"})
    public ResponseEntity<List<String>> getKeywords(@PathVariable String city) {
        String uri = "http://api.weatherapi.com/v1/current.json?key=8ced75335ba84c23b40231355232003&q=" + city;
        RestTemplate restTemplate = new RestTemplate();

        return new ResponseEntity<>(service.getKeywords(restTemplate.getForObject(uri, Weather.class)),HttpStatus.OK);
    }

}
