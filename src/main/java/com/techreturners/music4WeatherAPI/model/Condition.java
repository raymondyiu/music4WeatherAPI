package com.techreturners.music4WeatherAPI.model;

public class Condition {
    private String text;
    private String icon;
    private long code;

    public String getText() {
        return text;
    }

    public String getIcon() {
        return icon;
    }

    public long getCode() {
        return code;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setCode(long code) {
        this.code = code;
    }
}