package com.techreturners.music4WeatherAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Music4WeatherServiceImpl implements Music4WeatherService{

    @Autowired

    @Override
    public String getWelcomMsg(){
        return ("Welcome to Music for Weather API");
    }
}