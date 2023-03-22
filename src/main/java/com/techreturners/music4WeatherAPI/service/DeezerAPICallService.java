package com.techreturners.music4WeatherAPI.service;

import com.techreturners.music4WeatherAPI.model.Track;

public interface DeezerAPICallService {

    Track getTrackById(Long id);
}
