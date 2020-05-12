package com.example.ui.fragments;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.example.recyclercardview.NewAdapter;
import com.example.recyclercardview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CityInfo extends Fragment {
    ArrayList<Model> models = new ArrayList<>();

    IpmaWeatherClient client = new IpmaWeatherClient();
    private HashMap<String, City> cities;

    TextView mTitleTv, mDescTv, text;
    ImageView mImageIv;

    RecyclerView mRecyclerView;
    NewAdapter myAdapter;

    public CityInfo() {}

    public static CityInfo newInstance() {
        return new CityInfo();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_city_info, container, false);

        mTitleTv = rootView.findViewById(R.id.title);
        mDescTv = rootView.findViewById(R.id.description);
        mImageIv = rootView.findViewById(R.id.image);
        text = rootView.findViewById(R.id.text_api);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            int column_count;

            Resources res = getResources();

            boolean landscape = rootView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

            if(landscape){
                column_count = res.getInteger(R.integer.info_column_count_landscape);
            }
            else {
                column_count = res.getInteger(R.integer.info_column_count);
            }

            mTitleTv.setText(bundle.getString("title"));
            mDescTv.setText(bundle.getString("desc"));

            callWeatherForecastForACityStep1(bundle.getString("title"));

            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.CityInfoRv);
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), column_count));

            myAdapter = new NewAdapter(this.getContext(), models);
            mRecyclerView.setAdapter(myAdapter);
        }
        else {
            mTitleTv.setText("Error");
            mDescTv.setText("Error");
        }

        return rootView;
    }


    private void callWeatherForecastForACityStep1(final String city) {

        // call the remote api, passing an (anonymous) listener to get back the results
        client.retrieveWeatherConditionsDescriptions(new WeatherTypesResultsObserver() {
            @Override
            public void receiveWeatherTypesList(HashMap<Integer, WeatherType> descriptorsCollection) {
                callWeatherForecastForACityStep2( city);
            }
            @Override
            public void onFailure(Throwable cause) {
//                text.append("Failed to get weather conditions!");
            }
        });

    }

    private void callWeatherForecastForACityStep2(final String city) {

        client.retrieveCitiesList(new CityResultsObserver() {
            @Override
            public void receiveCitiesList(HashMap<String, City> citiesCollection) {
                CityInfo.this.cities = citiesCollection;
                City cityFound = cities.get(city);
                if( null != cityFound) {
                    int locationId = cityFound.getGlobalIdLocal();
                    callWeatherForecastForACityStep3(locationId);
                } else {
//                    text.append("unknown city: " + city);
                }
            }

            @Override
            public void onFailure(Throwable cause) {
                text.append("Failed to get cities list!");
            }
        });
    }

    private void callWeatherForecastForACityStep3(int localId) {

        client.retrieveForecastForCity(localId, new ForecastForACityResultsObserver() {
            @Override
            public void receiveForecastList(List<Weather> forecast) {
                for (Weather day : forecast) {
                    System.out.println("-----------------------------------");

                    Model m = new Model();

                    if((day.getIdWeatherType() >= 0) && (day.getIdWeatherType() <= 4))
                    {
                        m.setImg(R.drawable.ind);

                    }
                    else if((day.getIdWeatherType() >= 5) && (day.getIdWeatherType() <= 15))
                    {
                        m.setImg(R.drawable.coudrain);
                    }
                    else
                    {
                        m.setImg(R.drawable.cloudtunder);
                    }
                    m.setTitle(day.getForecastDate());
                    m.setDescription("Temperatura minima: "+ day.getTMin() +
                            "\nTemperatura máxima: "+ day.getTMax() +
                            "\nVelocidade do vento: " + day.getClassWindSpeed() +" km/h" +
                            "\nProbabilidade de precipitação: " + day.getPrecipitaProb() + "%") ;
                    models.add(m);
                }

                myAdapter.setModels(models);
                mRecyclerView.setAdapter(myAdapter);
            }
            @Override
            public void onFailure(Throwable cause) {
                text.append("Failed to get forecast for 5 days");
            }

        });
        System.out.println("over getting models");
    }
}
