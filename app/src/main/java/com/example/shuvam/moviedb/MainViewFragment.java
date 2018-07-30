package com.example.shuvam.moviedb;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.os.Build.VERSION_CODES.N;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainViewFragment extends Fragment implements ListDownloadListener{
    RecyclerView recyclerView;
    TextView textView;
    Button buttonViewAll;
    ProgressBar progressBar;
    List<Movies> movies=new ArrayList<>();
    Bundle bundle;
    public ListDownloader listDownloader;
    String fragment,type;
    long id;
    public final String MOVIES_FRAGMENT="moviesFragment";
    int page=1;
    MoviesInfoAdapter moviesAdapter;


    public MainViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View output=inflater.inflate(R.layout.fragment_main_view,container,false);
        recyclerView=output.findViewById(R.id.recyclerView);
        textView=output.findViewById(R.id.textView);
        buttonViewAll=output.findViewById(R.id.buttonViewAll);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        bundle=getArguments();
        listDownloader=new ListDownloader(bundle,getContext(),this);
        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),GridActivity.class);
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });

        fragment = bundle.getString("fragment");
        Log.d("MainViewFragment",fragment);
        type=bundle.getString("type");
        id=bundle.getLong("id",0);
        if(fragment.equals(MOVIES_FRAGMENT)){
            movies.addAll(listDownloader.getMovies(page,id));
            if (type.equals("NOWSHOWINGFRAGMENT")){
                textView.setText("Now Playing");
            }
            else if (type.equals("POPFRAGMENT")){
                textView.setText("Popular Movies");
            }
            else if (type.equals("UPCOMINGFRAGMENT")){
                textView.setText("Upcoming Movies");
            }
            else if (type.equals("TOPRATEDFRAGMENT")){
                textView.setText("Top Rated Movies");
            }
            if (movies.size()>0){
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);

            }
            moviesAdapter=new MoviesInfoAdapter(getContext(),movies,new RowListener(){
                @Override
                public void onListItemClicked(View view, int position) {
                    Intent intent=new Intent(getActivity(),DetailsActivity.class);
                    intent.putExtra("fragment",MOVIES_FRAGMENT);
                    long id=movies.get(position).getId();
                    intent.putExtra("id",id);
                    getActivity().startActivity(intent);
                }

                @Override
                public void onButtonClicked(int position, boolean checked) {

                }


            });
            recyclerView.setAdapter(moviesAdapter);


        }
        return output;
    }

    @Override
    public void onMoviesListDownloaded(List<Movies> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        moviesAdapter.notify();
        if (movies.size()>0){
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
