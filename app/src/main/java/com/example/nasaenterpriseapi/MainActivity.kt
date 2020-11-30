package com.example.nasaenterpriseapi

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nasaenterpriseapi.model.Items
import com.example.nasaenterpriseapi.model.NasaJsonResponse
import com.example.nasaenterpriseapi.network.ApiInterface
import com.example.nasaenterpriseapi.network.RetrofitClient.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    var nasaResponseBodyJava = NasaJsonResponse()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideKeyboard()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

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
                        var items: List<Items>
                        items = nasaResponseBodyJava.collection.items

                        for(x in 0 until items.size){
                            Log.i("===DEBUG DATA RESPONSE HERE===", items[x].data[0].title,
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