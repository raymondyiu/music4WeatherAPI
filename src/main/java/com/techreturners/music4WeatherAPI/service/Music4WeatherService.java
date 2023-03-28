package com.techreturners.music4WeatherAPI.service;

import com.techreturners.music4WeatherAPI.model.Track;
import com.techreturners.music4WeatherAPI.model.Weather;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface Music4WeatherService {

    String getWelcomMsg();
    Set<KeywordGenerator.Keyword> getKeywords(Weather weatherData);
    Track getTrack(ResponseEntity<List<String>> termsResponse) throws Exception;
    String getPublicIPAddress();


}
