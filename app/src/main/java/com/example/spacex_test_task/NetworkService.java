package com.example.spacex_test_task;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 Singleton Class to initialize Retrofit object and make question from other class
 */

public class NetworkService {

    private static NetworkService mInstance;
    private static final String BASE_URL="https://api.spacexdata.com/v3/";
    private Retrofit mRetrofit;

    public static NetworkService getInstance(){
        if(mInstance == null){
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    private NetworkService(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public mAPI getMyAPI(){
        return mRetrofit.create(mAPI.class);
    }

}
