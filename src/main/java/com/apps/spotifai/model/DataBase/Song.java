package com.apps.spotifai.model.DataBase;

public class Song {
    private Long id;
    private String title;
    private String artist;
    private String pathImg;
    private String pathSrc;

    public Song(){}

    public Song(String title, String artist, String pathImg, String pathSrc) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.pathImg = pathImg;
        this.pathSrc = pathSrc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public String getPathSrc() {
        return pathSrc;
    }

    public void setPathSrc(String pathSrc) {
        this.pathSrc = pathSrc;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", pathImg=" + pathImg +
                ", pathSrc='" + pathSrc + '\'' +
                '}';
    }
}
