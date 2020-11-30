package com.example.nasaenterpriseapi.network

import com.example.nasaenterpriseapi.model.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterfaceKotlin {

    @GET("search?")
    fun getSearch(
        @Query("q=") title: String,
    ): Call<Data>

}