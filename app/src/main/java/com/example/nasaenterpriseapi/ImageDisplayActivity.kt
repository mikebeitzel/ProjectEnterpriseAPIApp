package com.example.nasaenterpriseapi

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class ImageDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)

        val media_type_intent: String? = intent.getStringExtra("media_type")
        val center_intent: String? = intent.getStringExtra("center")
        val nasa_id_intent: String? = intent.getStringExtra("nasa_id")
        val description_intent: String? = intent.getStringExtra("description")
        val photographer_intent: String? = intent.getStringExtra("photographer")
        val keywords_intent: String? = intent.getStringExtra("keywords")
        val title_intent: String? = intent.getStringExtra("title")
        val date_intent: String? = intent.getStringExtra("date")
        val thumbnail_intent: String? = intent.getStringExtra("thumbnail")


//        val media_type_view: String? = findViewById(R.id.nasa_image_detail_media_type)
        val center_view: TextView = findViewById(R.id.nasa_image_detail_center)
//        val nasa_id_view: TextView = findViewById(R.id.nasa_image_detail_id)
        val description_view: TextView = findViewById(R.id.nasa_image_detail_description)
        val photographer_view: TextView = findViewById(R.id.nasa_image_detail_photographer)
        val keywords_view: TextView = findViewById(R.id.nasa_image_detail_keywords)
        val title_view: TextView = findViewById(R.id.nasa_image_detail_title)
        val date_view: TextView = findViewById(R.id.nasa_image_detail_date_created)
        val thumbnail_view: ImageView = findViewById(R.id.nasa_image_detail_thumbnail)

        center_view.text = center_intent
        description_view.text = description_intent
        photographer_view.text = photographer_intent
        keywords_view.text = keywords_intent
        title_view.text = title_intent
        date_view.text = date_intent
        Glide.with(this)
            .load(thumbnail_intent)
            .into(thumbnail_view)
    }


}