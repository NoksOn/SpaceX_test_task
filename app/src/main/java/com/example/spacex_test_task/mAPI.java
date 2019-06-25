package com.example.spacex_test_task;

import com.example.spacex_test_task.models.RetrofitModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/*
Retrofit interface
 */

public interface mAPI {

    @GET("launches")
    public Call<List<RetrofitModel>> getAllLauches();
}
