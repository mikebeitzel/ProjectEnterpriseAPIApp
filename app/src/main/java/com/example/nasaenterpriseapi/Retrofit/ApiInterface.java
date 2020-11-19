package com.example.nasaenterpriseapi.Retrofit;


import com.example.nasaenterpriseapi.Model.NasaImageAndVideoSearch;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("search?q=cough")
    Call<NasaImageAndVideoSearch> getData();

}
