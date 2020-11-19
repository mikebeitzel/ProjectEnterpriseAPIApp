package com.example.nasaenterpriseapi

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nasaenterpriseapi.Model.Data
import com.example.nasaenterpriseapi.Model.Items
import com.example.nasaenterpriseapi.Model.NasaImageAndVideoSearch
import com.example.nasaenterpriseapi.Retrofit.ApiInterface
import com.example.nasaenterpriseapi.Retrofit.RetrofitClient.retrofit
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    var responseBody = NasaImageAndVideoSearch()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //loading JSON
        val apiInterface = retrofit!!.create(ApiInterface::class.java)
        val call = apiInterface.data
            call.enqueue(object : Callback<NasaImageAndVideoSearch> {
                override fun onResponse(
                    call: Call<NasaImageAndVideoSearch>,
                    nasaImageAndVideoSearch: retrofit2.Response<NasaImageAndVideoSearch>
                ) {
                    if (nasaImageAndVideoSearch.isSuccessful) {
//                        Log.i("TAG", "==success==>" + nasaImageAndVideoSearch.body())
                        responseBody = nasaImageAndVideoSearch.body()!!
                        var items: List<Items?> = ArrayList()
                        items = responseBody.collection?.items!!
                        var data: List<Data?> = ArrayList()

                        Log.i("===Debug Response===", "$items")
                    }
                }

                override fun onFailure(call: Call<NasaImageAndVideoSearch>, t: Throwable) {
                    Log.e("TAG", "==failure==>" + t.message)
//                progressDialog!!.dismiss()
                    Toast.makeText(this@MainActivity, "went wrong !", Toast.LENGTH_SHORT).show()
                }
            })

        }
}