package com.example.shuvam.moviedb;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDownloader {
    ListDownloadListener listener;
    List<Movies> movies = new ArrayList<>();

    String type;
    String APIKEY="779e45ef0119b76313afebb8a5e89a0f";
    int page=1;
    long id;


    public ListDownloader(Bundle bundle, Context context, ListDownloadListener listener) {
        type = bundle.getString("type");
        this.listener = listener;
    }

        public List<Movies> getMovies (int page, long id){
            if (type.equals("NOWSHOWINGFRAGMENT")){
                Call<MoviesNowPlaying> moviesNowPlayingCall=ApiClient.getMoviesService().getNowPlayingMovies(APIKEY,page);
                moviesNowPlayingCall.enqueue(new Callback<MoviesNowPlaying>() {
                    @Override
                    public void onResponse(Call<MoviesNowPlaying> call, Response<MoviesNowPlaying> response) {
                        if (response.body()!=null){
                            MoviesNowPlaying moviesNowResponse;
                            moviesNowResponse=response.body();
                            /*for (int i=0;i<moviesNowResponse.getResult().size();i++){

                            }*/
                            movies.clear();
                            movies.addAll(moviesNowResponse.getResult());
                            listener.onMoviesListDownloaded(movies);
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesNowPlaying> call, Throwable t) {

                    }
                });
            }
            else if (type.equals("POPFRAGMENT")){
                Call<MoviesPopular> moviesPopularCall=ApiClient.getMoviesService().getPopularMovies(APIKEY,page);
                moviesPopularCall.enqueue(new Callback<MoviesPopular>() {
                    @Override
                    public void onResponse(Call<MoviesPopular> call, Response<MoviesPopular> response) {
                        if (response.body()!=null){
                            MoviesPopular moviesPopResponse;
                            moviesPopResponse=response.body();
                            /*for (int i=0;i<moviesNowResponse.getResult().size();i++){

                            }*/
                            movies.clear();
                            movies.addAll(moviesPopResponse.getResult());
                            listener.onMoviesListDownloaded(movies);
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesPopular> call, Throwable t) {

                    }
                });
            }
            else if (type.equals("UPCOMINGFRAGMENT")){
                Call<MoviesUpcoming> moviesUpcomingCall=ApiClient.getMoviesService().getUpcomingMovies(APIKEY,page);
                moviesUpcomingCall.enqueue(new Callback<MoviesUpcoming>() {
                    @Override
                    public void onResponse(Call<MoviesUpcoming> call, Response<MoviesUpcoming> response) {
                        if (response.body()!=null){
                            MoviesUpcoming moviesUpcomingResponse;
                            moviesUpcomingResponse=response.body();
                            /*for (int i=0;i<moviesNowResponse.getResult().size();i++){

                            }*/
                            movies.clear();
                            movies.addAll(moviesUpcomingResponse.getResult());
                            listener.onMoviesListDownloaded(movies);
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesUpcoming> call, Throwable t) {

                    }
                });
            }
            else if (type.equals("TOPRATEDFRAGMENT")){
                Call<MoviesTopRated> moviesTopRatedCall=ApiClient.getMoviesService().getTopRatedMovies(APIKEY,page);
                moviesTopRatedCall.enqueue(new Callback<MoviesTopRated>() {
                    @Override
                    public void onResponse(Call<MoviesTopRated> call, Response<MoviesTopRated> response) {
                        if (response.body()!=null){
                            MoviesTopRated moviesTopRatedResponse;
                            moviesTopRatedResponse=response.body();
                            /*for (int i=0;i<moviesNowResponse.getResult().size();i++){

                            }*/
                            movies.clear();
                            movies.addAll(moviesTopRatedResponse.getResult());
                            listener.onMoviesListDownloaded(movies);
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesTopRated> call, Throwable t) {

                    }
                });
            }
            return movies;
        }


}
