package com.example.nasaenterpriseapi.network.api

import com.example.nasaenterpriseapi.model.Collection
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NasaImageClient {
    private val BASE_URL = "https://images-api.nasa.gov/"

    var retrofit: Retrofit? = null
        get() {
            if (field == null) {
                field = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return field
        }
        private set

//    private val retrofit: NasaImageAPI = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build()
//        .create(NasaImageAPI::class.java)
//
//    fun getSearch(search: Class<NasaImageAPI>): Call<Collection> {
//        return retrofit.getData(search)
//    }

}