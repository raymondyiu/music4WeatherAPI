package com.techreturners.music4WeatherAPI.service;

import com.techreturners.music4WeatherAPI.model.Track;
import com.techreturners.music4WeatherAPI.model.TrackList;

import java.util.List;

public interface DeezerAPICallService {

    Track getTrackById(Long id);

    List<Track> getTracksByParam(String param);
}
