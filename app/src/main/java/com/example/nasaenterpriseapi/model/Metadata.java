package com.example.nasaenterpriseapi.model;

import com.google.gson.annotations.SerializedName;

public class Metadata{

	@SerializedName("total_hits")
	private int totalHits;

	public int getTotalHits(){
		return totalHits;
	}
}