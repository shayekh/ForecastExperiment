package com.example.android.forecastexperiment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiR {
    private static Retrofit retrofit;
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static Retrofit getForecast() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}
