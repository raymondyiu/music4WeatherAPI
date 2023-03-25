package com.techreturners.music4WeatherAPI.service;

import com.techreturners.music4WeatherAPI.model.Album;
import com.techreturners.music4WeatherAPI.model.Artist;
import com.techreturners.music4WeatherAPI.model.Track;
import com.techreturners.music4WeatherAPI.model.TrackList;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
public class DeezerAPICallServiceTest {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private DeezerAPICallServiceImpl deezerAPICallServiceImpl = new DeezerAPICallServiceImpl();
    @Test
    public void testGetTrackById() {

        Artist artist = new Artist("13", "Eminem", "https://www.deezer.com/artist/13", "Artist_picture");
        Album album = new Album("103248", "The Eminem Show", "https://www.deezer.com/album/103248", "Album_cover_small", "Album_release_date");
        Track track1 = new Track("916424", "Without Me", "https://www.deezer.com/track/916424", "Preview", artist, album);

        Long id = 916424L;
        when(restTemplate.getForObject("https://api.deezer.com/track/" + id, Track.class)).thenReturn(track1);

        Track actualResult = deezerAPICallServiceImpl.getTrackById(id);
        assertThat(actualResult.getId()).isEqualTo(track1.getId());
        assertThat(actualResult.getTitle()).isEqualTo(track1.getTitle());
        assertThat(actualResult.getArtist().getName()).isEqualTo(track1.getArtist().getName());
        assertThat(actualResult.getAlbum().getTitle()).isEqualTo(track1.getAlbum().getTitle());

    }

    //
    @Test
    public void testGetTracksByParam() {

        Artist artist = new Artist("13", "Eminem", "https://www.deezer.com/artist/13", "Artist_picture");
        Album album = new Album("103248", "The Eminem Show", "https://www.deezer.com/album/103248", "Album_cover_small", "Album_release_date");

        List<Track>  listOfTracks= new ArrayList<>();
        listOfTracks.add(new Track("916426", "Sing For The Moment", "https://www.deezer.com/track/916426", "Preview",  artist,album));
        listOfTracks.add(new Track("916424", "Without Me", "https://www.deezer.com/track/916424", "Preview", artist, album));
        listOfTracks.add(new Track("6461432", "Not Afraid", "https://www.deezer.com/track/6461432", "Preview", artist,album));

        TrackList trackList = new TrackList();
        trackList.setData(listOfTracks);

        String param = "eminem";
        when(restTemplate.getForObject("https://api.deezer.com/search?q=" + param, TrackList.class)).thenReturn( trackList);

        List<Track> actualResult = deezerAPICallServiceImpl.getTracksByParam(param);
        assertThat(actualResult.size()).isEqualTo(3);
        assertThat(actualResult.get(0).getId()).isEqualTo("916426");
        assertThat(actualResult.get(1).getId()).isEqualTo("916424");
        assertThat(actualResult.get(2).getId()).isEqualTo("6461432");


    }

}
