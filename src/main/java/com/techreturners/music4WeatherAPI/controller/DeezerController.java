package com.techreturners.music4WeatherAPI.controller;

import com.techreturners.music4WeatherAPI.model.Track;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DeezerController {

    @RequestMapping("/")
    @ResponseBody
    private String hello() {
        return "Hello !!!!";
    }


    @RequestMapping("/track/{id}")
    @ResponseBody
    private ResponseEntity<Track>  getTrackById(@PathVariable Long id) {  // http://localhost:8080/track/3135556
        String uri = "https://api.deezer.com/track/" + id;
        RestTemplate restTemplate = new RestTemplate();

        Track track = restTemplate.getForObject(uri, Track.class);
         return new ResponseEntity<>(track, HttpStatus.OK);
    }
}
