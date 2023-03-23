package com.techreturners.music4WeatherAPI.service;

import com.techreturners.music4WeatherAPI.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Music4WeatherServiceImpl implements Music4WeatherService{

    @Autowired

    @Override
    public String getWelcomMsg(){
        return ("Welcome to Music for Weather API");
    }




    public List<String> getKeywords(Weather weatherData) {
        KeywordGenerator keywordGenerator = new KeywordGenerator(weatherData);
        return keywordGenerator.getKeywords().stream().map(Enum::toString).toList();
    }

}