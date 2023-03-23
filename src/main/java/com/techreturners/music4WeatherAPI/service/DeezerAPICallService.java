package com.techreturners.music4WeatherAPI.service;

import com.techreturners.music4WeatherAPI.model.Track;

import java.util.List;

public interface DeezerAPICallService {

    Track getTrackById(Long id);
    List<Track> getTracksByParam(String param);
    String generateTrackSearchQuery(List<String> keywords);

}
