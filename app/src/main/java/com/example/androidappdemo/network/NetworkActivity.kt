package com.example.androidappdemo.network

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.androidappdemo.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

interface FlickrAPI {
    @GET("/")
    fun fetchContent(): Call<String>
}

class NetworkActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)
        findViewById<Button>(R.id.btnRESTService)?.setOnClickListener {
            val retrofit = Retrofit.Builder().baseUrl("https://www.baidu.com").addConverterFactory(ScalarsConverterFactory.create()).build()
            val api = retrofit.create(FlickrAPI::class.java)
            val request = api.fetchContent()
            request.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.i("Response", "message: ${response.message()}, body: ${response.body()}")
                    Toast.makeText(this@NetworkActivity, "success with body size: ${response.body().toString().count()}", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e("ERROR", "failure!")
                    Toast.makeText(this@NetworkActivity, "failure with error: ${t}}", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}