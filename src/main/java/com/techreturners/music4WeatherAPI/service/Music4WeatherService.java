package com.techreturners.music4WeatherAPI.service;

import com.techreturners.music4WeatherAPI.model.Track;
import com.techreturners.music4WeatherAPI.model.Weather;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Music4WeatherService {

    String getWelcomMsg();
    List<String> getKeywords(Weather weatherData);
    Track getTrack(ResponseEntity<List<String>> keywordsResponse) throws Exception;

}
