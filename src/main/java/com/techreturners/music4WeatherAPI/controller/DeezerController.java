package com.techreturners.music4WeatherAPI.controller;

import com.techreturners.music4WeatherAPI.model.Track;
import com.techreturners.music4WeatherAPI.model.TrackList;
import com.techreturners.music4WeatherAPI.service.DeezerAPICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeezerController {

    @Autowired
    DeezerAPICallService deezerAPICallService;

    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello !!!!";
    }

    //https://api.deezer.com/track/3135556
    @RequestMapping("/track/{id}")
    @ResponseBody
    public ResponseEntity<Track>  getTrackById(@PathVariable Long id) {  // http://localhost:8080/track/3135556
        Track track = deezerAPICallService.getTrackById(id);
         return new ResponseEntity<>(track, HttpStatus.OK);
    }

    //https://api.deezer.com/search?q=eminem
    @RequestMapping("/search")
    @ResponseBody
    public ResponseEntity<List<Track>>  getTracksByQueryParam(@RequestParam("q") String q) {  // http://localhost:8080/search?q=eminem
        List<Track> trackList = deezerAPICallService.getTracksByParam(q);
        return new ResponseEntity<>(trackList, HttpStatus.OK);
    }
}
