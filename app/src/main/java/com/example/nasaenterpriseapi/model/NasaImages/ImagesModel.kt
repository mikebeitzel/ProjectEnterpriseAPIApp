package com.example.nasaenterpriseapi.model.NasaImages

class ImagesModel {
    var mTitle: String? = null
    var mPhotographer: String? = null
    var mImageURL: String? = null
    var mDescription: String? = null

    constructor() {}
    constructor(title: String?, description: String?, photographer: String?, imageURL: String?) {
        this.mTitle = title
        this.mPhotographer = photographer
        this.mImageURL = imageURL
        this.mDescription = description
    }

}