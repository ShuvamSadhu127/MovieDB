package com.example.shuvam.moviedb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
MainViewFragment popFragment=new MainViewFragment(),nowShowingFragment=new MainViewFragment(),upcomingFragment=new MainViewFragment(),topRatedFragment=new MainViewFragment();
Bundle bundlePop=new Bundle(),bundleNow=new Bundle(),bundleUpcoming=new Bundle(),bundleTop=new Bundle();
    public MoviesFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_movies, container, false);

        FragmentManager manager=getChildFragmentManager();
        bundleNow.putString("type","NOWSHOWINGFRAGMENT");
        bundleNow.putString("fragment","moviesFragment");
        nowShowingFragment.setArguments(bundleNow);
        FragmentTransaction transactionNow=manager.beginTransaction();
        transactionNow.replace(R.id.nowShowingContainer,nowShowingFragment);
        transactionNow.commit();

        bundlePop.putString("type","POPFRAGMENT");
        bundlePop.putString("fragment","moviesFragment");
        popFragment.setArguments(bundlePop);
        FragmentTransaction transactionPop=manager.beginTransaction();
        transactionPop.replace(R.id.popularContainer,popFragment);
        transactionPop.commit();

        bundlePop.putString("type","UPCOMINGFRAGMENT");
        bundlePop.putString("fragment","moviesFragment");
        popFragment.setArguments(bundleUpcoming);
        FragmentTransaction transactionUpcoming=manager.beginTransaction();
        transactionUpcoming.replace(R.id.popularContainer,upcomingFragment);
        transactionUpcoming.commit();

        bundlePop.putString("type","TOPRATEDFRAGMENT");
        bundlePop.putString("fragment","moviesFragment");
        popFragment.setArguments(bundleTop);
        FragmentTransaction transactionTopRated=manager.beginTransaction();
        transactionTopRated.replace(R.id.popularContainer,topRatedFragment);
        transactionTopRated.commit();


        return view;

    }



}
