package com.moviesuggestions.manojkumar.moviesuggestions;

public class Movies {

    private String movie;
    private String thumbnail;

    public Movies(String movie, String thumbnail) {
        this.movie = movie;
        this.thumbnail = thumbnail;
    }

    public String getMovie() {
        return movie;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


}
