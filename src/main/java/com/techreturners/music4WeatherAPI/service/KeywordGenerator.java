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

    private final Weather data;

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
        //TODO: Add distinctions between snow and hail
        if (this.precipitation > 0.2) this.keywords.add(RAINY);
        if (this.precipitation > 0.2) this.keywords.add(RAINY);
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

    public String generateTerm() {
        List<Keyword> keywords = new ArrayList<>(this.keywords);
        int i = ThreadLocalRandom.current().nextInt(keywords.size());
        Keyword keyword = keywords.get(i);
        int j = ThreadLocalRandom.current().nextInt(keyword.terms.length);
        return keyword.terms[j];
    }

    // Plenty of room for more terms here - may even want to use a thesaurus API
    // Each term could be weighted by specificity to encourage more specific term generation
    enum Keyword {
        HOT(new String[]{"hot","heat"}),
        COLD(new String[]{"cold","chilly","chilled","chill","cool","arctic"}),
        SUNNY(new String[]{"sunny","sunshine","sunlit","bright"}),
        CLOUDY(new String[]{"cloud","clouds","raincloud","cloudy","clouded","grey"}),
        BREEZY(new String[]{"breezy","breeze","brisk"}),
        WINDY(new String[]{"wind","windy"}),
        GALE(new String[]{"gale","gust","gusty"}),
        STORM(new String[]{"storm","stormy","tempest","tempestuous"}),
        SQUALL(new String[]{"squall"}),
        RAINY(new String[]{"rain","rainy","wet"}),
        FOGGY(new String[]{"fog","foggy","misty","hazy"}),
        FROSTY(new String[]{"frost","frosty","ice","freezing","frozen","arctic","icy"}),
        SNOWY(new String[]{"snow","snowy"}),
        HAIL(new String[]{"hail","sleet"}),
        HUMID(new String[]{"humid","humidity"}),
        THUNDER(new String[]{"thunder","thunderstorm","lightning","flash","thunderclap","thunderbolt"}),
        BLIZZARD(new String[]{"blizzard","snowstorm"});

        String[] terms;

        Keyword(String[] terms){
            this.terms = terms;
        }

    }

}