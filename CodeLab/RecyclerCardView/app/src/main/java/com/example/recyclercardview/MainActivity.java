package com.example.recyclercardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String[] cities = new String[]{"Aveiro", "Braga", "Lisboa", "Porto"};
    RecyclerView mRecyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this, getMyList());
        mRecyclerView.setAdapter(myAdapter);
    }

    private ArrayList<Model> getMyList() {
        ArrayList<Model> models = new ArrayList<>();

        for (int i = 0; i < cities.length; i++) {
            Model m = new Model();
            m.setTitle(cities[i]);
            m.setDescription("Cidade de " + cities[i]);
            m.setImg(R.drawable.moeda);
            models.add(m);

        }


        return models;
    }
}
