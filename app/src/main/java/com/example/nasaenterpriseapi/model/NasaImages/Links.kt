package com.example.nasaenterpriseapi.model.NasaImages

import com.google.gson.annotations.SerializedName

data class Links (

	@SerializedName("render") val render : String,
	@SerializedName("href") val href : String,
	@SerializedName("rel") val rel : String
)