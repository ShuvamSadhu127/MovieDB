package com.example.shuvam.moviedb;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit;
    public static MoviesService moviesService;

    public static Retrofit getRetrofit() {
        if (retrofit==null){
            Retrofit.Builder builder=new Retrofit.Builder().baseUrl("https://developers.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create());
            retrofit=builder.build();
        }
        return retrofit;
    }

    public static MoviesService getMoviesService(){
        if (moviesService==null){
            moviesService = getRetrofit().create(MoviesService.class);
        }
        return moviesService;
    }

}
