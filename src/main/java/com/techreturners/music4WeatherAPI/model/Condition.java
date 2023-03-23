package com.techreturners.music4WeatherAPI.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Condition {
    private String text;
    private String icon;
    private long code;
}