package com.example.shuvam.moviedb;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Movie;
import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.shuvam.moviedb.ApiClient.retrofit;

public class DetailsActivity extends AppCompatActivity {

    TextView name,genre;
    ImageView poster,backdropPoster;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView overView;
    CollapsingToolbarLayout collapsingToolbarLayout;

    String posterPath,backdropPosterPath;

    //String name, genre;

    String fragment;
    Movies movies;
    FragmentManager fragmentManager;
    Bundle bundle;

    MainViewFragment mainViewFragment=new MainViewFragment();

    public final String MOVIES_FRAGMENT="moviesFragment";
    String APIKEY="779e45ef0119b76313afebb8a5e89a0f";

    long id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        poster=findViewById(R.id.poster);
        genre=findViewById(R.id.textViewGenre);
        backdropPoster=findViewById(R.id.backdropPoster);
        collapsingToolbarLayout=findViewById(R.id.collapsibleToolbar);
        overView=findViewById(R.id.overview);
        recyclerView=findViewById(R.id.recyclerView);
        //progressBar=findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        Intent intent=getIntent();
        fragment=intent.getStringExtra("fragment");
        if (fragment.equals(MOVIES_FRAGMENT)){
            Call<Movies> moviesCall=ApiClient.getMoviesService().getMovieDetails(id,APIKEY);
            moviesCall.enqueue(new Callback<Movies>() {
                @Override
                public void onResponse(Call<Movies> call, Response<Movies> response) {
                    movies=response.body();
                    collapsingToolbarLayout.setTitle(movies.getTitle());
                    overView.setText(movies.getOverview());
                    Picasso.get().load("http://image.tmdb.org/t/p/w400" + movies.getPosterPath()).fit().into(poster);
                    Picasso.get().load("http://image.tmdb.org/t/p/w400" + movies.getBackdropPath()).fit().into(backdropPoster);
                    if(!(movies.getGenres().size()<=0))
                    {
                        genre.setText(movies.getGenres().get(0).getName());
                    }

                }

                @Override
                public void onFailure(Call<Movies> call, Throwable t) {

                }
            });

            /*bundle.putString("fragment",MOVIES_FRAGMENT);
            //bundle.putString("type",SIMILAR_MOVIES);
            bundle.putLong("id",id);
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            mainViewFragment.setArguments(bundle);
            transaction.replace(R.id.SimilarContainer,listFragment);
            transaction.commit();*/
        }



    }
}
