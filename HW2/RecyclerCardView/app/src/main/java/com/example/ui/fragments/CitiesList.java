package com.example.ui.fragments;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datamodel.City;
import com.example.datamodel.Weather;
import com.example.datamodel.WeatherType;
import com.example.network.CityResultsObserver;
import com.example.network.ForecastForACityResultsObserver;
import com.example.network.IpmaWeatherClient;
import com.example.network.WeatherTypesResultsObserver;
import com.example.recyclercardview.Model;
import com.example.recyclercardview.MyAdapter;
import com.example.recyclercardview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CitiesList extends Fragment {
    IpmaWeatherClient client = new IpmaWeatherClient();

    String[] cities = new String[]{"Aveiro", "Braga", "Lisboa", "Porto", "Faro", "Vila Real", "Viana do Castelo"};
    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    GridLayoutManager grid = new GridLayoutManager(getActivity(), 1);
    public CitiesList() {
        // Required empty public constructor
    }

    public static CitiesList newInstance() {
        return new CitiesList();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        int orientation = this.getResources().getConfiguration().orientation;

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            System.out.println("------------------------------- changed to portrait");

            grid = new GridLayoutManager(getActivity(), R.integer.main_column_count);
            myAdapter = new MyAdapter(this.getContext(), getMyList());
            mRecyclerView.setAdapter(myAdapter);
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            System.out.println("------------------------------- changed to landscape");

            grid = new GridLayoutManager(getActivity(), R.integer.main_column_count_landscape);
            myAdapter = new MyAdapter(this.getContext(), getMyList());
            mRecyclerView.setAdapter(myAdapter);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int column_count;

        Resources res = getResources();
        View rootView = inflater.inflate(R.layout.fragment_cities_list, container, false);

        boolean landscape = rootView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if(landscape){
            column_count = res.getInteger(R.integer.main_column_count_landscape);
        }
        else {
            column_count = res.getInteger(R.integer.main_column_count);
        }

        // Inflate the layout for this fragment

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), column_count));

        myAdapter = new MyAdapter(this.getContext(), getMyList());
        mRecyclerView.setAdapter(myAdapter);

        return rootView;
    }

    private ArrayList<Model> getMyList() {
        ArrayList<Model> models = new ArrayList<>();

        for (String city : cities) {
            Model m = new Model();
            m.setTitle(city);
            m.setDescription("Cidade de " + city);
            m.setImg(R.drawable.place);
            models.add(m);

        }
        return models;
    }

}
