package com.techreturners.music4WeatherAPI.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Weather {
    private Location location;
    private Current current;
}
