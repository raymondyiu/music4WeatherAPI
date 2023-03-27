package com.techreturners.music4WeatherAPI.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Track {

    private String id;
    private String title;
    private String link;
    private String preview;
    private Artist artist;
    private Album album;

}
