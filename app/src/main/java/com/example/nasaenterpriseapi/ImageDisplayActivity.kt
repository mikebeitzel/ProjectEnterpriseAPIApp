package com.example.nasaenterpriseapi

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

class ImageDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)
        val bundle=intent.extras
        if(bundle != null){
            "Set The Code for displaying the Detailed view here?"

        }


    }
}