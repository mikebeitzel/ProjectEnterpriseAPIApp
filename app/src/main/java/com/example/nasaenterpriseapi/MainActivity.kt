package com.example.nasaenterpriseapi

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nasaenterpriseapi.Model.Data
import com.example.nasaenterpriseapi.Model.Items
import com.example.nasaenterpriseapi.Model.NasaJsonResponse
import com.example.nasaenterpriseapi.Retrofit.ApiInterface
import com.example.nasaenterpriseapi.Retrofit.RetrofitClient.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
//    var responseBody = NasaImageAndVideoSearch()
    var nasaResponseBodyJava = NasaJsonResponse()
    private val items: ArrayList<Items>? = null
    private val data: ArrayList<Data>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //loading JSON
        val apiInterface = retrofit!!.create(ApiInterface::class.java)
        val call = apiInterface.data
            call.enqueue(object : Callback<NasaJsonResponse> {
                override fun onResponse(
                    call: Call<NasaJsonResponse>,
                    response: Response<NasaJsonResponse>
                ) {
                    if (response.isSuccessful) {
//                        Log.i("TAG", "==success==>" + nasaImageAndVideoSearch.body())
                        nasaResponseBodyJava = response.body()!!
                        var model = response.body()
                        var items: List<Items>
                        items = nasaResponseBodyJava.collection.items

                        for(x in 0..items.size){
//                            Log.i("===Debug Response===", "$items")
                            Log.i("===DEBUG DATA RESPONSE HERE===", items[x].data[x].title,
//                                    + "\n" +
//                                    items[x].data.get(x).description + "\n" +
//                                    items[x].data.get(x).center + "\n" +
//                                    items[x].data.get(x).dateCreated + "\n" +
//                                    items[x].data.get(x).mediaType + "\n" +
//                                    items[x].data.get(x).keywords + "\n" +
//                                    items[x].data.get(x).nasaId + "\n"
                            )

                        }


                    }
                }

                override fun onFailure(call: Call<NasaJsonResponse>, t: Throwable) {
                    Log.e("TAG", "==failure==>" + t.message)
//                progressDialog!!.dismiss()
                    Toast.makeText(this@MainActivity, "went wrong !", Toast.LENGTH_SHORT).show()
                }
            })

        }
}