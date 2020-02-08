package com.example.android.forecastexperiment;

import com.example.android.forecastexperiment.Forecast.ForecastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiS {
    @GET()
    Call<ForecastResponse> getAllForecast(@Url String url);

}
