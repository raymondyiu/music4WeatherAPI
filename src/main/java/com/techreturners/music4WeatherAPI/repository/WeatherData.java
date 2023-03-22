package com.techreturners.music4WeatherAPI.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WeatherData {

    @JsonProperty("temp_c")
    private double temperature;
    @JsonProperty("wind_mph")
    private double wind;
    @JsonProperty("precip_mm")
    private double precipitation;
    private int humidity;
    private int cloud;
    @JsonProperty("vis_km")
    private double visibility;

    public WeatherData() {
    }

    public WeatherData(double temp_c, double wind_mph, double precip_mm, int humidity, int cloud, double vis_km) {
        this.temperature = temp_c;
        this.wind = wind_mph;
        this.precipitation = precip_mm;
        this.humidity = humidity;
        this.cloud = cloud;
        this.visibility = vis_km;
    }

}
