package com.example.nasaenterpriseapi

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaenterpriseapi.view.adapter.ImageAdapter
import com.example.nasaenterpriseapi.model.NasaImages.ImagesModel
import com.example.nasaenterpriseapi.model.NasaImages.Nasa_Images_Base
import com.example.nasaenterpriseapi.network.api.ImagesInterface
import com.example.nasaenterpriseapi.network.api.NasaImageClient.retrofit
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_image_display.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    var images: MutableList<ImagesModel>? = null
    var recyclerView: RecyclerView? = null
    private var nasa_images_base: Nasa_Images_Base? = null
    lateinit var searchView: SearchView
    var adapter: ImageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_display)

        recyclerView = findViewById(R.id.image_list_recycler_view)
        images = ArrayList()

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
        val searchValue = searchString
        val apiInterface = retrofit!!.create(ImagesInterface::class.java)
        val call = apiInterface.getSearch(searchValue)

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
                    images?.clear()

                    // This loop takes the json data and attaches it to the cardview
                    for (x in 0 until items.size) {
                        val imagesFragment = ImagesModel()
                        imagesFragment.mTitle = items[x].data[0].title
                        imagesFragment.mDescription = items[x].data[0].description
                        imagesFragment.mPhotographer = items[x].data[0].photographer
                        imagesFragment.mDate = items[x].data[0].date_created
                        imagesFragment.mImageURL = items[x].links[0].href
                        images!!.add(imagesFragment)
                    }
                    recyclerView!!.layoutManager = LinearLayoutManager(applicationContext)
                    adapter = images?.let { ImageAdapter(applicationContext, it) }
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