package com.techreturners.music4WeatherAPI.model;

public class Album {

    private String id;
    private String title;
    private String link;
    private String cover_image_link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCover_image_link() {
        return cover_image_link;
    }

    public void setCover_image_link(String cover_image_link) {
        this.cover_image_link = cover_image_link;
    }
}
