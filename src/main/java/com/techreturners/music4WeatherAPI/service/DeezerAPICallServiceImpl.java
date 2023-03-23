package com.techreturners.music4WeatherAPI.service;

import com.techreturners.music4WeatherAPI.model.Track;
import com.techreturners.music4WeatherAPI.model.TrackList;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DeezerAPICallServiceImpl implements DeezerAPICallService {

    @Override
    public Track getTrackById(Long id) {
        String uri = "https://api.deezer.com/track/" + id;
        RestTemplate restTemplate = new RestTemplate();

        Track track = restTemplate.getForObject(uri, Track.class);
        return track;
    }

    //https://api.deezer.com/search?q=eminem
    @Override
    public List<Track> getTracksByParam(String param) {
        String uri = "https://api.deezer.com/search?q=" + param;
        RestTemplate restTemplate = new RestTemplate();

        TrackList trackList = restTemplate.getForObject(uri, TrackList.class);
        return trackList.getData();
    }

    @Override
    public String generateTrackSearchQuery(List<String> keywords) {
        // URL = https://api.deezer.com/search?q=track:"i need a dollar"
        String chosenKeyword = keywords.get(ThreadLocalRandom.current().nextInt(0, keywords.size()));
        return "track:\"" + chosenKeyword + "\"";
    }

}
