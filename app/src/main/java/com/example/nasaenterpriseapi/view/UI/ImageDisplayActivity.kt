package com.example.nasaenterpriseapi.view.UI

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.nasaenterpriseapi.R
import com.example.nasaenterpriseapi.model.NasaHrefResponse.HrefLinksModel
import com.example.nasaenterpriseapi.model.NasaHrefResponse.Item
import com.example.nasaenterpriseapi.model.NasaHrefResponse.Nasa_Href_Base
import com.example.nasaenterpriseapi.network.interfaces.HrefLinksInterface
import com.example.nasaenterpriseapi.network.retrofitClients.HrefClient.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class ImageDisplayActivity : AppCompatActivity() {

    var hrefLinkModels: MutableList<HrefLinksModel>? = null
    private var nasa_href_base: Nasa_Href_Base? = null
    var nasa_id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)

//        val media_type_intent: String? = intent.getStringExtra("media_type")
        val center_intent: String? = intent.getStringExtra("center")
        val nasa_id_intent: String? = intent.getStringExtra("nasa_id")
        val description_intent: String? = intent.getStringExtra("description")
        val photographer_intent: String? = intent.getStringExtra("photographer")
        val keywords_intent: String? = intent.getStringExtra("keywords")
        val title_intent: String? = intent.getStringExtra("title")
        val date_intent: String? = intent.getStringExtra("date")
        val thumbnail_intent: String? = intent.getStringExtra("thumbnail")
        Log.i(" === Clicked on Nasa Id === ", "$nasa_id_intent")

        nasa_id = nasa_id_intent

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

        Log.i("=== The Nasa Id is", "")

        hrefLinkModels = ArrayList()
        getHrefs()
    }

    private fun getHrefs() {
        val nasaId = nasa_id.toString()
        val apiInterface = retrofit!!.create(HrefLinksInterface::class.java)
        val call = apiInterface.getHref(nasaId)

        call.enqueue(object : Callback<Nasa_Href_Base> {
            override fun onResponse(
                call: Call<Nasa_Href_Base>,
                response: Response<Nasa_Href_Base>
            ) {
                if (response.isSuccessful) {
//                    Log.i("===DEBUG DATA RESPONSE HERE===", Gson().toJson(response.body()))
                    nasa_href_base = response.body()!!
                    val hrefs: List<Item>
                    hrefs = nasa_href_base!!.collection.items

                    hrefLinkModels?.clear()

                    // This loop takes the json data return data and creates a list in the ImagesModel
                    for (x in 0 until hrefs.size) {
                        val hrefModel = HrefLinksModel()
                        hrefModel.mOriginalImage = hrefs[x].href
                        hrefModel.mLargeImage = hrefs[x].href
                        hrefModel.mMediumImage = hrefs[x].href
                        hrefModel.mSmallImage = hrefs[x].href
                        hrefModel.mThumbImage = hrefs[x].href
                        hrefModel.mMetadata = hrefs[x].href

                        Log.i("=== Printing one of the images === ", hrefs[1].href)
                        hrefLinkModels!!.add(hrefModel)
                    }
                }
            }

            override fun onFailure(call: Call<Nasa_Href_Base>, t: Throwable) {
                Log.e("TAG", "==failure==>" + t.message)
                Toast.makeText(this@ImageDisplayActivity, "went wrong !", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
