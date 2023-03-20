package com.techreturners.music4WeatherAPI.controller;

//import com.techreturners.music4WeatherAPI.model.Music4Weather;
import com.techreturners.music4WeatherAPI.service.Music4WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/music4Weather")
public class Music4WeatherController {
    @Autowired
    Music4WeatherService music4WeatherService;

    @GetMapping
    public ResponseEntity<String> getWelcomeMsg() {
        String message = music4WeatherService.getWelcomMsg();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
