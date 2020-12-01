package com.example.nasaenterpriseapi.model.NasaImages

import com.google.gson.annotations.SerializedName

data class Metadata (

	@SerializedName("total_hits") val total_hits : Int
)