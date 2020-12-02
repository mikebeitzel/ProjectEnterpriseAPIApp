package com.example.nasaenterpriseapi

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nasaenterpriseapi.model.NasaImages.Nasa_Images_Base
import com.example.nasaenterpriseapi.network.api.ImagesInterface
import com.example.nasaenterpriseapi.network.api.NasaImageClient.retrofit
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var textViewResult: TextView? = null
    private var nasa_images_base: Nasa_Images_Base? = null
    lateinit var searchView: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewResult = findViewById(R.id.text_view_result)

//        getSearch()
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
        var searchValue = searchString
        val apiInterface = retrofit!!.create(ImagesInterface::class.java)
        val call = apiInterface.getSearch(searchValue)

        call.enqueue(object : Callback<Nasa_Images_Base> {
            override fun onResponse(
                call: Call<Nasa_Images_Base>,
                response: Response<Nasa_Images_Base>
                ) {
                if (response.isSuccessful) {

                    nasa_images_base = response.body()!!
                    var items: List<com.example.nasaenterpriseapi.model.NasaImages.Items>
                    items = nasa_images_base!!.collection!!.items

                    for (x in 0 until items.size) {
                        var content = ""
                        content += "ID:" + items[x].data[0].nasa_id + "\n"
                        content += "Description:" + items[x].data[0].description + "\n"
                        content += "Photographer:" + items[x].data[0].photographer + "\n"
                        content += "Date Create:" + items[x].data[0].date_created + "\n"
                        content += "Title:" + items[x].data[0].title + "\n\n\n"
                        textViewResult!!.append(content)
                        Log.i("===DEBUG DATA RESPONSE HERE===", items[x].data[0].title)
                    }
                }
            }
            override fun onFailure(call: Call<Nasa_Images_Base>, t: Throwable) {
                Log.e("TAG", "==failure==>" + t.message)
                Toast.makeText(this@MainActivity, "went wrong !", Toast.LENGTH_SHORT).show()
            }
        })
    }
}