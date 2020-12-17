package com.example.nasaenterpriseapi.view.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasaenterpriseapi.ImageDisplayActivity
import com.example.nasaenterpriseapi.R
import com.example.nasaenterpriseapi.model.NasaImages.ImagesModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


class ImageAdapter(private val context: Context, imageFragmentsList: List<ImagesModel>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    var inflater: LayoutInflater
    var images: List<ImagesModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.custom_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val dateInString = images[position].mDate
        val formattedDate = OffsetDateTime.parse(images[position].mDate).format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
        println(formattedDate)

        Log.i("=== Created on Date ===", "$dateInString")

        // bind the data
        holder.title.text = images[position].mTitle
        holder.photographer.text = images[position].mPhotographer
        holder.date.text = formattedDate
        Glide.with(context)
            .load(images[position].mImageURL)
            .into(holder.imageURL)
        holder.itemView.setOnClickListener {
            val intent = Intent(this@ImageAdapter.context, ImageDisplayActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.nasa_image_cardview_title)
//        var description: TextView = itemView.findViewById(R.id.nasa_image_cardview_description)
        var photographer: TextView = itemView.findViewById(R.id.nasa_image_cardview_photographer)
        var imageURL: ImageView = itemView.findViewById(R.id.nasa_image_cardview_image)
        var date: TextView = itemView.findViewById(R.id.date_created)

        init {
            // handle onClick
            itemView.setOnClickListener { v -> Toast.makeText(
                v.context,
                "You clicked $adapterPosition",
                Toast.LENGTH_SHORT
            ).show()
            }
        }
    }

    init {
        inflater = LayoutInflater.from(context)
        this.images = imageFragmentsList
    }
}