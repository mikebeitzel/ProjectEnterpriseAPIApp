package com.example.nasaenterpriseapi.model.NasaImages

import com.google.gson.annotations.SerializedName

data class Collection (
    @SerializedName("version") val version : Double,
    @SerializedName("metadata") val metadata : Metadata,
    @SerializedName("href") val href : String,
    @SerializedName("items") val items : List<Items>
)
