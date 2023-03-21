package com.techreturners.music4WeatherAPI.service;

import lombok.Getter;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WeatherAnalyzer {

    private final WeatherInfo info;

    private String description;
    private float windSpeed;
    private float precipitation;
    private int humidity;
    private int cloud;
    private float visibility;

    @Getter
    List<Keyword> keywords;

    public WeatherAnalyzer(WeatherInfo info) {
        this.info = info;
        // TODO: Deduce keywords in order of least to most specific
    }

    public String generateTerm() {
        int i = ThreadLocalRandom.current().nextInt(this.keywords.size());
        Keyword keyword = this.keywords.get(i);
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
        WINDY(new String[]{"windy","gale","raincloud","cloudy","clouded","grey"}),
        RAINY(new String[]{"rain","rainy","wet"}),
        FOGGY(new String[]{"fog","foggy","misty","hazy"}),
        FROSTY(new String[]{"frost","frosty","ice","freezing","frozen","arctic","icy"}),
        SNOWY(new String[]{"snow","snowy"}),
        HAIL(new String[]{"hail","sleet"}),
        HUMID(new String[]{"humid","humidity"}),
        STORM(new String[]{"storm","stormy","squall"}),
        THUNDER(new String[]{"thunder","thunderstorm","lightning","flash","thunderclap","thunderbolt"}),
        BLIZZARD(new String[]{"blizzard","snowstorm"});

        String[] terms;

        Keyword(String[] terms){
            this.terms = terms;
        }

    }

}
