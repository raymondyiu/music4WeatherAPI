package com.techreturners.music4WeatherAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
                            MockMvcRequestBuilders.get("/search?q=track:test"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            System.out.println("##### raymond testing");
        }
    }

}
