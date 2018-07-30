package com.example.shuvam.moviedb;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesService {

    @GET("movie/now_playing")
    Call<MoviesNowPlaying> getNowPlayingMovies(@Query("api_key") String key,@Query("page") int page);

    @GET("movie/upcoming")
    Call<MoviesUpcoming> getUpcomingMovies(@Query("api_key") String key, @Query("page") int page  );

    @GET("movie/popular")
    Call<MoviesPopular> getPopularMovies(@Query("api_key") String key, @Query("page") int page  );

    @GET("movie/top_rated")
    Call<MoviesTopRated> getTopRatedMovies(@Query("api_key") String key, @Query("page") int page  );

    @GET("movie/{movie_id}")
    Call<Movies> getMovieDetails(@Path("movie_id") long id, @Query("api_key") String key  );
}
