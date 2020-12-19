package com.example.nasaenterpriseapi.model.NasaHrefResponse


import com.google.gson.annotations.SerializedName

data class Collection(
    @SerializedName("href")
    val href: String,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("version")
    val version: String
)