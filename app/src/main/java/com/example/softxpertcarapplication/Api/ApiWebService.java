package com.example.softxpertcarapplication.Api;

import com.example.softxpertcarapplication.Pojo.CarResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiWebService {

    @GET("cars")
    Call<CarResponse> getCars(@Query("page") int page);

}
