package com.techreturners.music4WeatherAPI.service;

import com.techreturners.music4WeatherAPI.exception.RecordNotFoundException;
import com.techreturners.music4WeatherAPI.model.Track;
import com.techreturners.music4WeatherAPI.model.TrackList;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DeezerAPICallServiceImpl implements DeezerAPICallService {


    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public Track getTrackById(Long id) throws RecordNotFoundException {
        String uri = "https://api.deezer.com/track/" + id;
        Track track = restTemplate.getForObject(uri, Track.class);
        if(null == track.getId())
                throw new RecordNotFoundException("Records Not Found !!!!");
        return track;
    }

    //https://api.deezer.com/search?q=eminem
    @Override
    public List<Track> getTracksByParam(String param) throws RecordNotFoundException {
        String uri = "https://api.deezer.com/search?q=" + param;

        TrackList trackList = restTemplate.getForObject(uri, TrackList.class);
        if(trackList.getData().isEmpty())
            throw new RecordNotFoundException("Records Not Found !!!!");
        return trackList.getData();
    }

    @Override
    public String generateTrackSearchQuery(List<String> terms) {
        // URL = https://api.deezer.com/search?q=track:"i need a dollar"
        String chosenTerm = terms.get(ThreadLocalRandom.current().nextInt(0, terms.size()));
        return "track:\"" + chosenTerm + "\"";
    }

}
