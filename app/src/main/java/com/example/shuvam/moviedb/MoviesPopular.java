package com.example.shuvam.moviedb;

import java.util.List;

public class MoviesPopular {

    int id;
    int movieId;
    int page;
    List<Movies> result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movies> getResult() {
        return result;
    }

    public void setResult(List<Movies> result) {
        this.result = result;
    }

}
