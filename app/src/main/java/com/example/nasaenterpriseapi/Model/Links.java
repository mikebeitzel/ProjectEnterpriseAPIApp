package com.example.nasaenterpriseapi.Model;

import com.google.gson.annotations.SerializedName;

public class Links {

	@SerializedName("rel")
	private String rel;

	@SerializedName("href")
	private String href;

	@SerializedName("render")
	private String render;

	public String getRel(){
		return rel;
	}

	public String getHref(){
		return href;
	}

	public String getRender(){
		return render;
	}
}