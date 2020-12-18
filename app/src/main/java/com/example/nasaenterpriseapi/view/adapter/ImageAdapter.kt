package com.example.nasaenterpriseapi.view.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.nasaenterpriseapi.ImageDisplayActivity
import com.example.nasaenterpriseapi.R
import com.example.nasaenterpriseapi.model.NasaImages.ImagesModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.regex.Matcher


class ImageAdapter(private val context: Context, imageFragmentsList: List<ImagesModel>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    var inflater: LayoutInflater
    var images: List<ImagesModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.custom_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val formattedDate = OffsetDateTime.parse(images[position].mDate).format(
            DateTimeFormatter.ofPattern(
                "MMM dd, yyyy"
            )
        )

        // bind the data
        holder.title.text = images[position].mTitle
        holder.photographer.text = images[position].mPhotographer
        holder.date.text = formattedDate

        Glide.with(context)
            .load(images[position].mThumbnailImage)
            .into(holder.imageURL)
        holder.itemView.setOnClickListener {
            val intent = Intent(this@ImageAdapter.context, ImageDisplayActivity::class.java)
            val url = images[position].mHrefLink

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("media_type", images[position].mMediaType)
            intent.putExtra("center", images[position].mCenter)
            intent.putExtra("nasa_id", images[position].mNasaId)
            intent.putExtra("description", images[position].mDescription)
            intent.putExtra("photographer", images[position].mPhotographer)
            intent.putExtra("keywords", images[position].mKeywords)
            intent.putExtra("title", images[position].mDate)
            intent.putExtra("date", images[position].mDate)
            intent.putExtra("thumbnail", images[position].mThumbnailImage)


//            uriParse.putExtra("href", images[position].mHref)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.nasa_image_detail_title)
        var photographer: TextView = itemView.findViewById(R.id.nasa_image_detail_photographer)
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