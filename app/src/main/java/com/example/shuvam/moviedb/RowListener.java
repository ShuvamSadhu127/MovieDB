package com.example.shuvam.moviedb;

import android.view.View;

public interface RowListener {
    void onListItemClicked(View view, int position);
    void onButtonClicked(int position,boolean checked);
}
