package com.example.nasaenterpriseapi

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
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
    var nasaResponseBodyJava = Nasa_Images_Base()
    private var textViewResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewResult = findViewById(R.id.txt_search_result)

        //retrieve JSON
        val apiInterface = retrofit!!.create(ImagesInterface::class.java)
        val call = apiInterface.data
            call.enqueue(object : Callback<Nasa_Images_Base> {
                override fun onResponse(
                    call: Call<Nasa_Images_Base>,
                    response: Response<Nasa_Images_Base>
                ) {
                    if (response.isSuccessful) {
//                        Log.i("TAG", "==success==>" + nasaImageAndVideoSearch.body())
                        nasaResponseBodyJava = response.body()!!
                        var items: List<com.example.nasaenterpriseapi.model.NasaImages.Items>
                        items = nasaResponseBodyJava.collection!!.items

                        for (x in 0 until items.size) {
//                            txt_search_result.movementMethod = ScrollingMovementMethod()
                            txt_search_result2.append(items[x].data[0].title + "\n")
                            Log.i(
                                "===DEBUG DATA RESPONSE HERE===", items[x].data[0].title,
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

                override fun onFailure(call: Call<Nasa_Images_Base>, t: Throwable) {
                    Log.e("TAG", "==failure==>" + t.message)
//                progressDialog!!.dismiss()
                    Toast.makeText(this@MainActivity, "went wrong !", Toast.LENGTH_SHORT).show()
                }
            })

        }
}