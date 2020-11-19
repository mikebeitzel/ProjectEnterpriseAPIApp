package com.example.nasaenterpriseapi.Model;

import com.google.gson.annotations.SerializedName;

public class NasaJsonResponse{

	@SerializedName("collection")
	private Collection collection;

	public Collection getCollection(){
		return collection;
	}
}