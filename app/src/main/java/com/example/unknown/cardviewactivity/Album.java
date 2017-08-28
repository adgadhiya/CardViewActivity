package com.example.unknown.cardviewactivity;

/**
 * Created by UNKNOWN on 7/11/2016.
 */
public class Album {

    private String name;
    private int numOfSoongs;
    private int thumbnail;

    public Album() {
    }

    public Album(String name, int numOfSoongs, int thumbnail) {
        this.name = name;
        this.numOfSoongs = numOfSoongs;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSoongs() {
        return numOfSoongs;
    }

    public void setNumOfSoongs(int numOfSoongs) {
        this.numOfSoongs = numOfSoongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}