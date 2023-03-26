package com.techreturners.music4WeatherAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.music4WeatherAPI.model.Album;
import com.techreturners.music4WeatherAPI.model.Artist;
import com.techreturners.music4WeatherAPI.model.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.techreturners.music4WeatherAPI.service.DeezerAPICallServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class DeezerControllerTest {

    @Mock
    private DeezerAPICallServiceImpl deezerAPICallServiceImpl;

    @InjectMocks
    private DeezerController deezerController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(deezerController).build();
        mapper = new ObjectMapper();
    }

    @Test
    void getTracksByQueryParamTest() throws Exception {
        try {
            this.mockMvcController.perform(
                            MockMvcRequestBuilders.get("/api/v1/track/search?q=test"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            System.out.println("##### raymond testing");
        }
    }

    @Test
    void getTrackByIdTest() throws Exception {
        try {
            this.mockMvcController.perform(
                            MockMvcRequestBuilders.get("/api/v1/track/8"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            System.out.println("##### raymond testing");
        }
    }

    @Test
    public void testGetTrackById() throws Exception {

        Artist artist = new Artist("111", "Artist1",  "Artist_link",  "Artist_picture");
        Album album = new Album("222", "Album_title",  "Album_link",  "Album_cover_small",  "Album_release_date");
        Track track = new Track( "3135556","Track_title",  "Track_link",  "Track_preview_mp3",  artist,  album);

        when(deezerAPICallServiceImpl.getTrackById(Long.valueOf(3135556))).thenReturn(track);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/track/" + "3135556"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("3135556"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Track_title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.link").value("Track_link"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.preview").value("Track_preview_mp3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist.id").value("111"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist.name").value("Artist1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist.picture").value("Artist_picture"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.album.id").value("222"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.album.title").value("Album_title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.album.link").value("Album_link"));
    }

    @Test
    public void testGetTracksByQueryParam() throws Exception {

        Artist artist = new Artist("13", "Eminem",  "https://www.deezer.com/artist/13",  "Artist_picture");
        Album album = new Album("103248", "The Eminem Show",  "https://www.deezer.com/album/103248","Album_cover_small","Album_release_date");

        List<Track> trackList = new ArrayList<>();
        trackList.add(new Track("916424", "Without Me", "https://www.deezer.com/track/916424","Preview", artist,album));
        trackList.add(new Track("916426", "Sing For The Moment", "https://www.deezer.com/track/916426", "Preview",  artist,album));
        trackList.add(new Track("6461432", "Not Afraid", "https://www.deezer.com/track/6461432", "Preview", artist,album));

        when(deezerAPICallServiceImpl.getTracksByParam("eminem")).thenReturn(trackList);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/track/search?q="+"eminem"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("916424"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Without Me"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].artist.id").value("13"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].artist.name").value("Eminem"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("916426"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Sing For The Moment"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].artist.id").value("13"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].artist.name").value("Eminem"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value("6461432"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].title").value("Not Afraid"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].artist.id").value("13"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].artist.name").value("Eminem"));
    }

}
