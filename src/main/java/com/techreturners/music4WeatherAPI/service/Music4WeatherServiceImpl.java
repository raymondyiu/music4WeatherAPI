package com.techreturners.music4WeatherAPI.service;

import com.techreturners.music4WeatherAPI.controller.DeezerController;
import com.techreturners.music4WeatherAPI.model.Track;
import com.techreturners.music4WeatherAPI.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<String> getKeywords(Weather weatherData) {
        KeywordGenerator keywordGenerator = new KeywordGenerator(weatherData);
        return keywordGenerator.getKeywords().stream().map(Enum::toString).toList();
    }

    @Override
    public Track getTrack(ResponseEntity<List<String>> keywordsResponse) throws Exception {

        List<String> keywords = keywordsResponse.getBody();
        if (keywords == null) {throw new Exception(); /*TODO: Established exception handling*/}

        ResponseEntity<List<Track>> tracks = deezerController.getTracksByQueryParam(deezerAPICallService.generateTrackSearchQuery(keywords));
        List<Track> trackList = tracks.getBody();
        if (trackList == null) {throw new Exception(); /*TODO: Established exception handling*/}

         return trackList.get(ThreadLocalRandom.current().nextInt(0, trackList.size()));
    }

}