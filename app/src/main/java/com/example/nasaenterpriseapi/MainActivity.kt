package com.example.nasaenterpriseapi

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaenterpriseapi.model.NasaImages.HrefLinks
import com.example.nasaenterpriseapi.model.NasaImages.ImagesModel
import com.example.nasaenterpriseapi.model.NasaImages.Nasa_Images_Base
import com.example.nasaenterpriseapi.network.api.ImagesInterface
import com.example.nasaenterpriseapi.network.api.NasaImageClient
import com.example.nasaenterpriseapi.network.api.NasaImageClient.retrofit
import com.example.nasaenterpriseapi.view.adapter.ImageAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_image_display.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*
import java.util.*


class MainActivity : AppCompatActivity() {

    var image_data: MutableList<ImagesModel>? = null
    var hrefLinks: MutableList<HrefLinks>? = null

    var recyclerView: RecyclerView? = null
    private var nasa_images_base: Nasa_Images_Base? = null
    lateinit var searchView: SearchView
    var adapter: ImageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_display)

        recyclerView = findViewById(R.id.image_list_recycler_view)
        image_data = ArrayList()

        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setQueryHint("Search")
        searchView.setSubmitButtonEnabled(true)
        searchView.onActionViewExpanded()
        search(searchView)
        return true
    }

    private fun search(searchView: SearchView) {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                var query = query
                query = query.toLowerCase()
                getSearch(query)
                searchView.onActionViewCollapsed()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun getSearch(searchString: String) {
        val apiInterface = retrofit!!.create(ImagesInterface::class.java)
        val call = apiInterface.getSearch(searchString)

        call.enqueue(object : Callback<Nasa_Images_Base> {
            override fun onResponse(
                call: Call<Nasa_Images_Base>,
                response: Response<Nasa_Images_Base>
            ) {
                if (response.isSuccessful) {
                    Log.i("===DEBUG DATA RESPONSE HERE===", Gson().toJson(response.body()))
                    nasa_images_base = response.body()!!

                    var items: List<com.example.nasaenterpriseapi.model.NasaImages.Items>
                    items = nasa_images_base!!.collection!!.items

                    image_data?.clear()

                    // This loop takes the json data return data and creates a list in the ImagesModel
                    for (x in 0 until items.size) {
                        val imagesFragment = ImagesModel()
                        imagesFragment.mMediaType = items[x].data[0].media_type
                        imagesFragment.mCenter = items[x].data[0].center
                        imagesFragment.mNasaId = items[x].data[0].nasa_id
                        imagesFragment.mDescription = items[x].data[0].description
                        imagesFragment.mPhotographer = items[x].data[0].photographer
                        // Check to see if by creating this into a list of keywords I can have individual search buttons
                        imagesFragment.mKeywords = items[x].data[0].keywordswords.toString()
                        imagesFragment.mTitle = items[x].data[0].title
                        imagesFragment.mDate = items[x].data[0].date_created
                        imagesFragment.mThumbnailImage = items[x].links[0].href

                        imagesFragment.mHref = items[x].href

//                        val href = items[x].href
//                        hrefLinks
////                        val json = readJsonFromUrl(href)
//                        val response = Gson().toJson(href)


                        Log.i("=== Json Return? === ", "$response")

                        image_data!!.add(imagesFragment)
                    }
                    recyclerView!!.layoutManager = LinearLayoutManager(applicationContext)
                    adapter = image_data?.let { ImageAdapter(applicationContext, it) }
                    recyclerView!!.adapter = adapter
                }
            }

            override fun onFailure(call: Call<Nasa_Images_Base>, t: Throwable) {
                Log.e("TAG", "==failure==>" + t.message)
                Toast.makeText(this@MainActivity, "went wrong !", Toast.LENGTH_SHORT).show()
            }
        })
    }

}