package com.techreturners.music4WeatherAPI.model;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Track {

    private String id;
    private String title;
    private String link;
    private String preview;
    private Artist artist;
    private Album album;

}
