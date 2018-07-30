package com.example.shuvam.moviedb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;



public class MoviesInfoAdapter extends RecyclerView.Adapter<mViewHolder> {
    List<Movies> movies;
    LayoutInflater inflater;
    Context context;
    RowListener rowListener;

    public MoviesInfoAdapter(Context context, List<Movies> movies, RowListener rowListener) {
        this.context=context;
        this.rowListener=rowListener;
        this.movies=movies;
        this.inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.id.rowLayout,parent,false);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final mViewHolder holder, int position) {
        holder.textViewTitle.setText(movies.get(position).getTitle());
        if (movies.get(position).getPosterPath()!=null){
            Picasso.get().load("http://image.tmdb.org/t/p/w400" + movies.get(position).getPosterPath()).into(holder.poster);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowListener.onListItemClicked(view,holder.getAdapterPosition());
            }
        });
        holder.toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowListener.onButtonClicked(holder.getAdapterPosition(),holder.toggleButton.isChecked());
            }
        });
    }



    @Override
    public int getItemCount() {
        return movies.size();
    }
}
