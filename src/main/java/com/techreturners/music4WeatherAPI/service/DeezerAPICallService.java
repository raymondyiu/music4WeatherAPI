package com.techreturners.music4WeatherAPI.service;

import com.techreturners.music4WeatherAPI.exception.RecordNotFoundException;
import com.techreturners.music4WeatherAPI.model.Track;

import java.util.List;

public interface DeezerAPICallService {

    Track getTrackById(Long id); throws RecordNotFoundException;
    List<Track> getTracksByParam(String param); throws RecordNotFoundException;
    String generateTrackSearchQuery(List<String> keywords);
    
}
