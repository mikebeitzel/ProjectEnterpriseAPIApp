package com.example.nasaenterpriseapi.network;

import com.example.nasaenterpriseapi.model.NasaJsonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("search?q=saturn")
    Call<NasaJsonResponse> getData();

}
