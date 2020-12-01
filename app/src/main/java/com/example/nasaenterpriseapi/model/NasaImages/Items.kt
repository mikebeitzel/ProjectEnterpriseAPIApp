package com.example.nasaenterpriseapi.model.NasaImages

import com.google.gson.annotations.SerializedName

data class Items (

	@SerializedName("links") val links : List<Links>,
	@SerializedName("href") val href : String,
	@SerializedName("data") val data : List<Data>
)