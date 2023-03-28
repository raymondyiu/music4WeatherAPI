package com.techreturners.music4WeatherAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    private String id;
    private String title;
    private String link;
    private String cover_small;
    private String release_date;

}
