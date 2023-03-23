package com.techreturners.music4WeatherAPI.controller;

//import com.techreturners.music4WeatherAPI.model.Music4Weather;
import com.techreturners.music4WeatherAPI.model.Track;
import com.techreturners.music4WeatherAPI.model.Weather;
import com.techreturners.music4WeatherAPI.service.Music4WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/v1/music4Weather")
public class Music4WeatherController {

    @Autowired
    Music4WeatherService music4WeatherService;
    @Autowired
    WeatherController weatherController;

    @GetMapping
    public ResponseEntity<String> getWelcomeMsg() {
        String message = music4WeatherService.getWelcomMsg();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping({"/{city}"})
    public ResponseEntity<Track> getTrack(@PathVariable String city) throws Exception {

        ResponseEntity<List<String>> keywordsResponse = weatherController.getKeywords(city);
        if (keywordsResponse.getStatusCode() != HttpStatus.OK) {throw new Exception(); /*TODO: Established exception handling*/}

        Track chosenTrack = music4WeatherService.getTrack(keywordsResponse);

        return new ResponseEntity<>(chosenTrack,HttpStatus.OK);

        //TODO: Send Track to View class handler

    }

}
