package com.example.nasaenterpriseapi

import android.R.string
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ImageDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)

        val title_intent: String? = intent.getStringExtra("title")
        val description_intent: String? = intent.getStringExtra("description")
//        val date_intent: String? = intent.getStringExtra("date")
//        val photographer_intent: String? = intent.getStringExtra("photographer")
//        val location_intent: String? = intent.getStringExtra("location")
//        val keywords_intent: String? = intent.getStringExtra("keywords")
//        val keywords_intent: String? = intent.getStringExtra("keywords")


        val title_view: TextView = findViewById(R.id.nasa_image_detail_title)
        val description_view: TextView = findViewById(R.id.nasa_image_detail_description)
//        val date_view: TextView = findViewById(R.id.nasa_image_detail_date_created)
//        val photographer_view: TextView = findViewById(R.id.nasa_image_detail_photographer)
//        val location_view: TextView = findViewById(R.id.nasa_image_detail_location)
//        val keywords_view: TextView = findViewById(R.id.nasa_image_detail_keywords)

        title_view.text = title_intent
        description_view.text = description_intent
//        date_view.text = date_intent
//        photographer_view.text = photographer_intent
//        location_view.text = location_intent
//        keywords_view.text = keywords_intent

    }


}