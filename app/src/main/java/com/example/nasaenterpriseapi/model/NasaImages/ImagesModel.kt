package com.example.nasaenterpriseapi.model.NasaImages

class ImagesModel {
    var mTitle: String? = null
    var mPhotographer: String? = null
    var mImageURL: String? = null
    var mDescription: String? = null
    var mKeywords: String? = null
    var mNasaId: String? = null
    var mDate: String? = null
    var mLocation: String? = null

    constructor() {}
    constructor(title: String?, description: String?, photographer: String?,
                imageURL: String?, keywords: String?, nasaId: String?, date: String?) {
        this.mTitle = title
        this.mPhotographer = photographer
        this.mImageURL = imageURL
        this.mDescription = description
        this.mKeywords = keywords
        this.mNasaId = nasaId
        this.mDate = date
    }

}