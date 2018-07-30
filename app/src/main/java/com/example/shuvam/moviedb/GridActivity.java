package com.example.shuvam.moviedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class GridActivity extends AppCompatActivity implements ListDownloadListener {
    RecyclerView recyclerView;
    List<Movies> movies;
    MoviesInfoAdapter moviesInfoAdapter;
    ListDownloader listDownloader;
    GridLayoutManager layoutManager;
    Intent intent;
    Bundle bundle;
    String fragment;
     long id;
    public final String MOVIES_FRAGMENT="moviesFragment";
     int page=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        recyclerView.findViewById(R.id.gridrecycler);
        layoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        intent=new Intent();
        bundle=intent.getExtras();
        fragment=bundle.getString("fragment");
        id=bundle.getLong("id",0);
        listDownloader=new ListDownloader(bundle, GridActivity.this, new ListDownloadListener() {
            @Override
            public void onMoviesListDownloaded(List<Movies> movies) {
                GridActivity.this.movies.addAll(movies);
                moviesInfoAdapter.notifyDataSetChanged();
            }
        });

        if (fragment.equals(MOVIES_FRAGMENT)) {
            listDownloader.getMovies(page, id);
        }

        if (fragment.equals(MOVIES_FRAGMENT)){
            movies.addAll(listDownloader.getMovies(page,id));
            moviesInfoAdapter=new MoviesInfoAdapter(this, movies, new RowListener() {
                @Override
                public void onListItemClicked(View view, int position) {
                    Intent intent=new Intent(GridActivity.this, DetailsActivity.class);
                    intent.putExtra("fragment",MOVIES_FRAGMENT);
                    intent.putExtra("id",(long)movies.get(position).getId());
                    startActivity(intent);
                }

                @Override
                public void onButtonClicked(int position, boolean checked) {

                }
            });
            recyclerView.setAdapter(moviesInfoAdapter);
        }


    }

    @Override
    public void onMoviesListDownloaded(List<Movies> movies) {
        this.movies.clear();
        movies.addAll(movies);
        moviesInfoAdapter.notifyDataSetChanged();

    }
}
