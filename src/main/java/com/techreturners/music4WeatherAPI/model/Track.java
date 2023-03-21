package com.techreturners.music4WeatherAPI.model;


public class Track {

    private String id;
    private String title;
    private String link;
    private String preview_link;
    private Artist artist;
    private Album album;

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

    public String getPreview_link() {
        return preview_link;
    }

    public void setPreview_link(String preview_link) {
        this.preview_link = preview_link;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
