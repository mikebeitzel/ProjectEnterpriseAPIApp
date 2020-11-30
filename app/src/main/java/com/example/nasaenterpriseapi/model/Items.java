package com.example.nasaenterpriseapi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Items {

	@SerializedName("data")
	private List<Data> data;

	@SerializedName("href")
	private String href;

	@SerializedName("links")
	private List<Links> links;

	public List<Data> getData(){
		return data;
	}

	public String getHref(){
		return href;
	}

	public List<Links> getLinks(){
		return links;
	}
}