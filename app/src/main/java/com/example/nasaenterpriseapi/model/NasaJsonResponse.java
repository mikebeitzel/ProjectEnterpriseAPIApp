package com.example.nasaenterpriseapi.model;

import com.google.gson.annotations.SerializedName;

public class NasaJsonResponse{

	@SerializedName("collection")
	private Collection collection;

	public Collection getCollection(){
		return collection;
	}
}