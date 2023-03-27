package com.techreturners.music4WeatherAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Current {
    private double temp_c;
    private int is_day;
    private Condition condition;
    private double wind_mph;
    private double wind_kph;
    private double wind_degree;
    private double precip_mm;
    private int humidity;
    private int cloud;
    private double vis_km;
    private double vis_miles;
    private double uv;
}
