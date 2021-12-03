package com.example.nasaenterpriseapi.view.UI

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaenterpriseapi.R
import com.example.nasaenterpriseapi.model.NasaQueryResponse.ImagesModel
import com.example.nasaenterpriseapi.model.NasaQueryResponse.Nasa_Images_Base
import com.example.nasaenterpriseapi.network.interfaces.ImagesInterface
import com.example.nasaenterpriseapi.network.retrofitClients.NasaImageClient.retrofit
import com.example.nasaenterpriseapi.view.adapter.RecyclerViewSearchAdapter
import kotlinx.android.synthetic.main.activity_image_display.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*
import java.util.*


class MainActivity : AppCompatActivity() {

    var image_data: MutableList<ImagesModel>? = null

    var recyclerView: RecyclerView? = null
    private var nasa_images_base: Nasa_Images_Base? = null
    lateinit var searchView: SearchView
    var adapter: RecyclerViewSearchAdapter? = null

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
                    nasa_images_base = response.body()!!
                    var items: List<com.example.nasaenterpriseapi.model.NasaQueryResponse.Items>
                    items = nasa_images_base!!.collection!!.items

                    image_data?.clear()

                    // This loop takes the json data return data and creates a list in the ImagesModel
                    for (x in 0 until items.size) {
                        val imagesModel = ImagesModel()

                        imagesModel.mMediaType = items[x].data[0].media_type
                        imagesModel.mCenter = items[x].data[0].center
                        imagesModel.mNasaId = items[x].data[0].nasa_id
                        imagesModel.mDescription = items[x].data[0].description
                        imagesModel.mPhotographer = items[x].data[0].photographer


                        // Check to see if by creating this into a list of keywords I can have individual search buttons
                        imagesModel.mKeywords = items[x].data[0].keywordswords.toString()
                        imagesModel.mTitle = items[x].data[0].title
                        imagesModel.mDate = items[x].data[0].date_created
                        imagesModel.mThumbnailImage = items[x].links[0].href
                        imagesModel.mHrefLink = items[x].href

                        val nasa_id = items[x].data[0].nasa_id
                        Log.i("=== Nasa Id ===", "Nasa ID is $nasa_id")

                        image_data!!.add(imagesModel)
                    }
                    recyclerView!!.layoutManager = LinearLayoutManager(applicationContext)
                    adapter = image_data?.let { RecyclerViewSearchAdapter(applicationContext, it) }
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