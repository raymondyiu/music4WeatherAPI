package com.techreturners.music4WeatherAPI.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Current {
    private double temp_c;
    private double temp_f;
    private int is_day;
    private Condition condition;
    private double wind_mph;
    private double wind_kph;
    private double wind_degree;
    private double precip_mm;
    private double precip_in;
    private int humidity;
    private int cloud;
    private double vis_km;
    private double vis_miles;
    private double uv;
}
