package com.example.nasaenterpriseapi.network

import com.example.nasaenterpriseapi.model.Data
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val BASE_URL = "https://images-api.nasa.gov/"

//    var retrofit: Retrofit? = null
//        get() {
//            if (field == null) {
//                field = Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .build()
//            }
//            return field
//        }
//        private set

    val retrofit: ApiInterfaceKotlin = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ApiInterfaceKotlin::class.java)

    fun getSearch(search: String): Call<Data> {
        return retrofit.getSearch(search)
    }

//    fun getMovieDetails(title: String): Call<MovieDetail> {
//        return api.getMovieDetails(title, BuildConfig.API_KEY)
//    }

}