package com.example.nasaenterpriseapi.model.NasaHrefResponse


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("href")
    val href: String
)