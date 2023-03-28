package com.techreturners.music4WeatherAPI.controller;

import com.techreturners.music4WeatherAPI.model.Track;
import com.techreturners.music4WeatherAPI.service.Music4WeatherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping({"/"})
    public ResponseEntity<Track> getTrackFromLocation(HttpServletRequest request) throws Exception {

        String ipAddress;

        if (request == null) ipAddress = music4WeatherService.getPublicIPAddress();
        else ipAddress = request.getHeader("X-Forwarded-For");

        ResponseEntity<List<String>> termsResponse = weatherController.getTerms(ipAddress);
        if (termsResponse.getStatusCode() != HttpStatus.OK) {throw new Exception(); /*TODO: Established exception handling*/}

        Track chosenTrack = music4WeatherService.getTrack(termsResponse);

        return new ResponseEntity<>(chosenTrack,HttpStatus.OK);

    }

    @GetMapping({"/{city}"})
    public ResponseEntity<Track> getTrackFromCity(@PathVariable String city) throws Exception {

        ResponseEntity<List<String>> termsResponse = weatherController.getTerms(city);
        if (termsResponse.getStatusCode() != HttpStatus.OK) {throw new Exception(); /*TODO: Established exception handling*/}

        Track chosenTrack = music4WeatherService.getTrack(termsResponse);

        return new ResponseEntity<>(chosenTrack,HttpStatus.OK);

    }

}
