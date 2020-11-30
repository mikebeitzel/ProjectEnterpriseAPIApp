package com.example.nasaenterpriseapi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data {

	@SerializedName("keywords")
	private List<String> keywords;

	@SerializedName("media_type")
	private String mediaType;

	@SerializedName("date_created")
	private String dateCreated;

	@SerializedName("center")
	private String center;

	@SerializedName("description")
	private String description;

	@SerializedName("nasa_id")
	private String nasaId;

	@SerializedName("title")
	private String title;

	@SerializedName("description_508")
	private String description508;

	public List<String> getKeywords(){
		return keywords;
	}

	public String getMediaType(){
		return mediaType;
	}

	public String getDateCreated(){
		return dateCreated;
	}

	public String getCenter(){
		return center;
	}

	public String getDescription(){
		return description;
	}

	public String getNasaId(){
		return nasaId;
	}

	public String getTitle(){
		return title;
	}

	public String getDescription508(){
		return description508;
	}
}