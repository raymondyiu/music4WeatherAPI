package com.techreturners.music4WeatherAPI.service;

import com.techreturners.music4WeatherAPI.model.Weather;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.techreturners.music4WeatherAPI.service.KeywordGenerator.Keyword.*;

public class KeywordGenerator {

    private Weather data;

    private String description;
    private int code;

    private final double temperature;
    private final double windSpeed;
    private final double precipitation;
    private final int humidity;
    private final int cloud;
    private final double visibility;

    @Getter
    Set<Keyword> keywords = new HashSet<>();

    public KeywordGenerator(Weather data) {
        this.data = data;

        this.code = (int) data.getCurrent().getCondition().getCode();

        this.temperature = data.getCurrent().getTemp_c();
        this.windSpeed = data.getCurrent().getWind_kph();
        this.precipitation = data.getCurrent().getPrecip_mm();
        this.humidity = data.getCurrent().getHumidity();
        this.cloud = data.getCurrent().getCloud();
        this.visibility = data.getCurrent().getVis_km();
        // Deducing keywords in order of least to most specific
        calculateTemperature();
        calculateCloudCover();
        calculatePrecipitation();
        calculateWind();
        calculateHumidity();
        calculateVisibility();
        inferCode();
    }

    private void calculateTemperature() {
        if (this.temperature > 25) this.keywords.add(HOT);
        else if (this.temperature < 6) this.keywords.add(COLD);
        if (this.temperature < 0) this.keywords.add(FROSTY);
    }

    private void calculateCloudCover() {
        this.keywords.add(this.cloud > 69 ? CLOUDY : SUNNY);
    }


    private void calculatePrecipitation() {
        if (this.precipitation > 0.2) {
            if (this.temperature > 0) this.keywords.add(RAINY);
        }
        if (this.precipitation > 8) this.keywords.add(STORM);
    }

    private void calculateWind() {
        if (this.windSpeed > 3 && this.windSpeed < 31) this.keywords.add(BREEZY);
        if (this.windSpeed > 19) this.keywords.add(WINDY);
        if (this.windSpeed > 38) {
            this.keywords.add(GALE);
            if (this.windSpeed > 46 && (this.keywords.contains(RAINY) || this.keywords.contains(HAIL) || this.keywords.contains(SNOWY))) {
                this.keywords.add(STORM);
                this.keywords.add(SQUALL);
            }
        }
        if (this.windSpeed > 63) this.keywords.add(STORM);
    }

    private void calculateHumidity() {
        if (this.humidity > 69) this.keywords.add(HUMID);
    }

    private void calculateVisibility() {
        if (this.visibility < 0.4) this.keywords.add(FOGGY);
    }

    public String generateTerm() {
        List<Keyword> keywords = new ArrayList<>(this.keywords);
        int i = ThreadLocalRandom.current().nextInt(keywords.size());
        Keyword keyword = keywords.get(i);
        int j = ThreadLocalRandom.current().nextInt(keyword.terms.length);
        return keyword.terms[j];
    }
    
    private void inferCode() {
        switch (this.code) {
            case 1000 -> {
                if (this.data != null && this.data.getCurrent().getIs_day() == 1) {this.keywords.add(SUNNY);}
                else {this.keywords.add(CLEAR);}
            }
            case 1003, 1006 -> this.keywords.add(CLOUDY);
            case 1009 -> this.keywords.add(OVERCAST);
            case 1030, 1135 -> this.keywords.add(FOGGY);
            case 1114, 1210, 1213, 1216, 1219, 1222, 1225, 1255, 1258 -> this.keywords.add(SNOWY);
            case 1117 -> this.keywords.add(BLIZZARD);
            case 1147 -> {
                this.keywords.add(FOGGY);
                this.keywords.add(COLD);
                this.keywords.add(FROSTY);
            }
            case 1150, 1153, 1180, 1183, 1189, 1240, 1243 -> this.keywords.add(RAINY);
            case 1168, 1171 -> {
                this.keywords.add(RAINY);
                this.keywords.add(COLD);
                this.keywords.add(STORM);
            }
            case 1195, 1246 -> {
                this.keywords.add(RAINY);
                this.keywords.add(STORM);
            }
            case 1198, 1201 -> {
                this.keywords.add(RAINY);
                this.keywords.add(COLD);
            }
            case 1204, 1207, 1237, 1249, 1261, 1264 -> this.keywords.add(HAIL);
            case 1273, 1276 -> {
                this.keywords.add(RAINY);
                this.keywords.add(STORM);
                this.keywords.add(THUNDER);
            }
            case 1279, 1282 -> {
                this.keywords.add(SNOWY);
                this.keywords.add(STORM);
                this.keywords.add(THUNDER);
            }
        }
    }

    // Plenty of room for more terms here - may even want to use a thesaurus API
    // Each term could be weighted by specificity to encourage more specific term generation
    public enum Keyword {
        CLEAR(new String[]{"clear","fair"}),
        HOT(new String[]{"hot","heat"}),
        COLD(new String[]{"cold","chilly","chilled","chill","cool","arctic"}),
        SUNNY(new String[]{"sun","sunny","sunshine","sunlit","bright"}),
        CLOUDY(new String[]{"cloud","clouds","raincloud","cloudy","clouded","grey"}),
        OVERCAST(new String[]{"overcast"}),
        BREEZY(new String[]{"breezy","breeze","brisk"}),
        WINDY(new String[]{"wind","windy"}),
        GALE(new String[]{"gale","gust","gusty"}),
        STORM(new String[]{"storm","stormy","tempest","tempestuous"}),
        SQUALL(new String[]{"squall"}),
        RAINY(new String[]{"rain","rainy","wet","raindrop","raindrops"}),
        FOGGY(new String[]{"mist", "fog","foggy","misty","hazy"}),
        FROSTY(new String[]{"frost","frosty","ice","freezing","frozen","arctic","icy"}),
        SNOWY(new String[]{"snow","snowy"}),
        HAIL(new String[]{"hail","sleet"}),
        HUMID(new String[]{"humid","humidity","close","heavy"}),
        THUNDER(new String[]{"thunder","thunderstorm","lightning","flash","thunderclap","thunderbolt"}),
        BLIZZARD(new String[]{"blizzard","snowstorm"});

        @Getter
        String[] terms;

        Keyword(String[] terms){
            this.terms = terms;
        }

    }

}
