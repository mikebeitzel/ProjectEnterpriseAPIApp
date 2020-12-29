package com.example.nasaenterpriseapi.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasaenterpriseapi.R
import com.example.nasaenterpriseapi.model.NasaQueryResponse.ImagesModel
import com.example.nasaenterpriseapi.view.UI.ImageDisplayActivity
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


class RecyclerViewSearchAdapter(private val context: Context, imageFragmentsList: List<ImagesModel>) : RecyclerView.Adapter<RecyclerViewSearchAdapter.ViewHolder>() {

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
            val intent = Intent(this@RecyclerViewSearchAdapter.context, ImageDisplayActivity::class.java)

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
    }

    init {
        inflater = LayoutInflater.from(context)
        this.images = imageFragmentsList
    }
}