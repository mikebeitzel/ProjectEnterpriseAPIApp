package com.example.nasaenterpriseapi.network.api

import com.example.nasaenterpriseapi.model.NasaImages.Nasa_Images_Base
import retrofit2.Call
import retrofit2.http.GET

interface ImagesInterface {
    @get:GET("search?q=saturn")
    val data: Call<Nasa_Images_Base>
}