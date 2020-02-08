package com.example.android.forecastexperiment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.forecastexperiment.Forecast.ForecastResponse;
import com.example.android.forecastexperiment.Forecast.List;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiS service;
    private Double lat=23.750841;
    private Double lon=90.393293;
    private TextView placeText;
    //private int radious=1000;
    // private String place_type;
   // private RecyclerView recyclerView;
    private ForecastAdapter adapter;
    RecyclerView recyclerView;
    private String unit = "metric";
    ArrayList<List> lists;
   // ArrayList<Weather>weathers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rid);
        placeText=findViewById(R.id.place);

        //String url = String.format("place/nearbysearch/json?location=%f,%f&radius=%d&type=%s&key=%s",lat,lon,radious,place_type,getResources().getString(R.string.place_key));
        String url = String.format("forecast?lat=23.750841&lon=90.393293&appid=32465ba61b4765bcc16dc9b1d258880e");
        service = ApiR.getForecast().create(ApiS.class);
        Call<ForecastResponse> forecastResponseCall = service.getAllForecast(url);
        forecastResponseCall.enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                if (response.code()==200){
                    ForecastResponse forecastResponse = response.body();
                    placeText.setText(forecastResponse.getCity().getName());
                    lists = (ArrayList<List>) forecastResponse.getList();
                    //weathers=(ArrayList<List>) forecastResponse.;
                    adapter = new ForecastAdapter(lists);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(adapter);
                     Toast.makeText(MainActivity.this, String.valueOf(lists.size()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {

            }
        });


    }
}
