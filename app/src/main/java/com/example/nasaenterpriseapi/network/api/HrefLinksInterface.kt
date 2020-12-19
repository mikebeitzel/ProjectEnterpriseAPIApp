package com.example.nasaenterpriseapi.network.api

import com.example.nasaenterpriseapi.model.NasaHrefResponse.Nasa_Href_Base
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HrefLinksInterface {

    @GET("{nasa_id}")
    fun getHref(
        @Path("nasa_id") nasa_id: String
    ): Call<Nasa_Href_Base>
}