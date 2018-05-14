package com.moviesuggestions.manojkumar.moviesuggestions;

public class Movies {

    private String movie;
    private int thumbnail;

    public Movies(String movie, int thumbnail) {
        this.movie = movie;
        this.thumbnail = thumbnail;
    }

    public String getMovie() {
        return movie;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }


}
