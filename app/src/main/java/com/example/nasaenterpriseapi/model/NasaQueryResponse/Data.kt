package com.example.nasaenterpriseapi.model.NasaQueryResponse

import com.google.gson.annotations.SerializedName

data class Data (

	@SerializedName("media_type") val media_type : String,
	@SerializedName("center") val center : String,
	@SerializedName("nasa_id") val nasa_id : String,
	@SerializedName("description") val description : String,
	@SerializedName("photographer") val photographer : String,
	@SerializedName("keywords") val keywordswords : List<String>,
	@SerializedName("date_created") val date_created : String,
	@SerializedName("title") val title : String
)