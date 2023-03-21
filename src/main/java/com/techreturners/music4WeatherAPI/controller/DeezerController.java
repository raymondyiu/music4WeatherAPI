package com.techreturners.music4WeatherAPI.controller;

import com.techreturners.music4WeatherAPI.model.Track;
import com.techreturners.music4WeatherAPI.service.DeezerAPICallService;
import com.techreturners.music4WeatherAPI.service.Music4WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DeezerController {

    @Autowired
    DeezerAPICallService deezerAPICallService;

    @RequestMapping("/")
    @ResponseBody
    private String hello() {
        return "Hello !!!!";
    }


    @RequestMapping("/track/{id}")
    @ResponseBody
    private ResponseEntity<Track>  getTrackById(@PathVariable Long id) {  // http://localhost:8080/track/3135556
        Track track = deezerAPICallService.getTrackById(id);
         return new ResponseEntity<>(track, HttpStatus.OK);
    }
}
