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
    DeezerController deezerController;
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
        if (keywordsResponse.getStatusCode() != HttpStatus.OK) {
            throw new Exception(); //TODO: Established exception handling
        }
        List<String> keywords = keywordsResponse.getBody();

        if (keywords == null) {
            throw new Exception(); //TODO: Established exception handling
        }

        //TODO: Make into dedicated method
        String chosenKeyword = keywords.get(ThreadLocalRandom.current().nextInt(0, keywords.size()));

        ResponseEntity<List<Track>> tracks = deezerController.getTracksByQueryParam(chosenKeyword);

        if (tracks.getStatusCode() != HttpStatus.OK) {
            throw new Exception(); //TODO: Established exception handling
        }

        List<Track> trackList = tracks.getBody();

        if (trackList == null) {
            throw new Exception(); //TODO: Established exception handling
        }

        //TODO: Make into dedicated method
        Track chosenTrack= trackList.get(ThreadLocalRandom.current().nextInt(0, trackList.size()));

        return new ResponseEntity<>(chosenTrack,HttpStatus.OK);

        //TODO: Send Track to View class

    }



//    @RequestMapping("/keywords/{city}")
//    public ResponseEntity<String> getKeywords() {
//        String message = music4WeatherService.getWelcomMsg();
//        return new ResponseEntity<>(message, HttpStatus.OK);
//    }

}
