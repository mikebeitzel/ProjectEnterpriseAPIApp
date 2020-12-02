package com.example.nasaenterpriseapi.network.api

import com.example.nasaenterpriseapi.model.NasaImages.Nasa_Images_Base
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ImagesInterface {

    @GET("search")
    fun getSearch(
        @Query("q") searchValue: String
    ): Call<Nasa_Images_Base>

}