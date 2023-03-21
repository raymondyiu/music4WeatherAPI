package com.techreturners.music4WeatherAPI.service;

import com.techreturners.music4WeatherAPI.model.Track;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeezerAPICallServiceImpl implements DeezerAPICallService{
    @Override
    public Track getTrackById(Long id) {
        String uri = "https://api.deezer.com/track/" + id;
        RestTemplate restTemplate = new RestTemplate();

        Track track = restTemplate.getForObject(uri, Track.class);
        return track;
    }
}
