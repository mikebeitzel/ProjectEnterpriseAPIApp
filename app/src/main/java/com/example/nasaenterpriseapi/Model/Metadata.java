package com.example.nasaenterpriseapi.Model;

import com.google.gson.annotations.SerializedName;

public class Metadata{

	@SerializedName("total_hits")
	private int totalHits;

	public int getTotalHits(){
		return totalHits;
	}
}