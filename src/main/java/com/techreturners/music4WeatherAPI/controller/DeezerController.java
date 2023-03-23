package com.techreturners.music4WeatherAPI.controller;

import com.techreturners.music4WeatherAPI.exception.RecordNotFoundException;
import com.techreturners.music4WeatherAPI.model.Track;
import com.techreturners.music4WeatherAPI.service.DeezerAPICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/track")
public class DeezerController {

    @Autowired
    DeezerAPICallService deezerAPICallService;

    // http://localhost:8080/api/v1/track/3135556
    @RequestMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Track>  getTrackById(@PathVariable Long id) {
        Track track;
        //HttpHeaders httpHeaders = new HttpHeaders();
        try {
            track = deezerAPICallService.getTrackById(id);
            return new ResponseEntity<>(track, HttpStatus.OK);
        } catch (Exception e){
            //httpHeaders.add("track", "track with " + id + " not exist");
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
           //return new ResponseEntity<>(e.getMessage(), httpHeaders, HttpStatus.NO_CONTENT);
        }
    }

    // http://localhost:8080/api/v1/track/search?q=eminem


    //Returns List of Tracks
    @RequestMapping("/search")
    @ResponseBody
    public ResponseEntity<List<Track>>  getTracksByQueryParam(@RequestParam("q") String q) {
        List<Track> trackList = null;
        try {
            trackList = deezerAPICallService.getTracksByParam(q);
            return new ResponseEntity<>(trackList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
