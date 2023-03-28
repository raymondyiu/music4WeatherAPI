package com.techreturners.music4WeatherAPI.service;

import com.techreturners.music4WeatherAPI.controller.DeezerController;
import com.techreturners.music4WeatherAPI.model.Track;
import com.techreturners.music4WeatherAPI.model.Weather;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class Music4WeatherServiceImpl implements Music4WeatherService{

    @Autowired
    DeezerController deezerController;
    @Autowired
    DeezerAPICallService deezerAPICallService;

    @Override
    public String getWelcomMsg(){
        return ("Welcome to Music for Weather API");
    }

    @Override
    public Set<KeywordGenerator.Keyword> getKeywords(Weather weatherData) {
        KeywordGenerator keywordGenerator = new KeywordGenerator(weatherData);
        return keywordGenerator.getKeywords();
    }

    @Override
    public Track getTrack(ResponseEntity<List<String>> termsResponse) throws Exception {

        List<String> terms = termsResponse.getBody();
        if (terms == null) {throw new Exception(); /*TODO: Established exception handling*/}

        ResponseEntity<List<Track>> tracks = deezerController.getTracksByQueryParam(deezerAPICallService.generateTrackSearchQuery(terms));
        List<Track> trackList = tracks.getBody();
        if (trackList == null) {throw new Exception(); /*TODO: Established exception handling*/}

         return trackList.get(ThreadLocalRandom.current().nextInt(0, trackList.size()));
    }

    @SneakyThrows
    @Override
    public String getPublicIPAddress() {
        URL url = new URL("http://checkip.amazonaws.com/");
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        return in.readLine();
    }

}