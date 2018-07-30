package com.example.shuvam.moviedb;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class mViewHolder extends RecyclerView.ViewHolder {
    public View itemview;
    public TextView textViewTitle;
    public ImageView poster;
    public ToggleButton toggleButton;
    public mViewHolder(View itemView) {
        super(itemView);
        this.itemview=itemView;
        textViewTitle=itemView.findViewById(R.id.title);
        poster=itemView.findViewById(R.id.poster);
        toggleButton=itemView.findViewById(R.id.toggleButton);
    }
}
