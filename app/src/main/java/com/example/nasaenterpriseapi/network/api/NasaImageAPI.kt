package com.example.nasaenterpriseapi.network.api

import com.example.nasaenterpriseapi.model.NasaImages.Items
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaImageAPI {

    @GET("search")
    fun getData(
        @Query("?q=") searchString: String
    ): Call<List<Items>>

}