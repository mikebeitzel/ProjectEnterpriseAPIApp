package com.example.nasaenterpriseapi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Collection{

	@SerializedName("metadata")
	private Metadata metadata;

	@SerializedName("href")
	private String href;

	@SerializedName("items")
	private List<Items> items;

	@SerializedName("version")
	private String version;

	public Metadata getMetadata(){
		return metadata;
	}

	public String getHref(){
		return href;
	}

	public List<Items> getItems(){
		return items;
	}

	public String getVersion(){
		return version;
	}
}