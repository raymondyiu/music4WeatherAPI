package com.techreturners.music4WeatherAPI.service;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.techreturners.music4WeatherAPI.service.WeatherAnalyzer.Keyword.*;

public class WeatherAnalyzer {

    private final WeatherInfo info;

    private String description;
    private float temperature;
    private float windSpeed;
    private float precipitation;
    private int humidity;
    private int cloud;
    private float visibility;

    @Getter
    Set<Keyword> keywords = new HashSet<>();

    public WeatherAnalyzer(WeatherInfo info) {
        this.info = info;
        // Deducing keywords in order of least to most specific
        calculateTemperature();
        calculateCloudCover();
    }

    private void calculateTemperature() {
        if (this.temperature > 25) this.keywords.add(HOT);
        else if (this.temperature < 6) this.keywords.add(COLD);
        if (this.temperature < 0) this.keywords.add(FROSTY);
    }

    private void calculateCloudCover() {
        this.keywords.add(this.cloud > 69 ? CLOUDY : SUNNY);
    }

    //TODO: Calculate rain first

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
