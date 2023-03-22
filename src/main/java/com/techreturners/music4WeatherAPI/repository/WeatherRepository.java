package com.techreturners.music4WeatherAPI.repository;

public interface WeatherRepository {
    WeatherData getWeatherForCity(String city);
}
