package com.example.btm;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static SelectedApi selectedApi;

    @Override
    public void onCreate() {
        super.onCreate();
        String BASE_URL = "https://finnhub.io";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
            selectedApi = retrofit.create(SelectedApi.class);
    }
    public static SelectedApi getApi() {
        return selectedApi;
    }
}
