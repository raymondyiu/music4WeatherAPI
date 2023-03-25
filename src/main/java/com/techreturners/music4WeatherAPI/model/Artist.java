package com.techreturners.music4WeatherAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    private String id;
    private String name;
    private String link;
    private String picture;

}
