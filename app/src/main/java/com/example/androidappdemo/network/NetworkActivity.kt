package com.example.androidappdemo.network

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
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
    lateinit var tvLogs: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)
        tvLogs = findViewById(R.id.tvLogs)
        findViewById<Button>(R.id.btnRESTService)?.setOnClickListener {
            val retrofit = Retrofit.Builder().baseUrl("https://www.baidu.com").addConverterFactory(ScalarsConverterFactory.create()).build()
            val api = retrofit.create(FlickrAPI::class.java)
            val request = api.fetchContent()
            request.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.i("Response", "message: ${response.message()}, body: ${response.body()}")
                    Toast.makeText(this@NetworkActivity, "success with body size: ${response.body().toString().count()}", Toast.LENGTH_LONG).show()
                    tvLogs.text = "suscess with body: \n${response.body().toString()}"
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e("ERROR", "failure!")
                    Toast.makeText(this@NetworkActivity, "failure with error: ${t}}", Toast.LENGTH_LONG).show()
                    tvLogs.text = "failure with error: \n${t}"
                }
            })
        }
        findViewById<Button>(R.id.btnClear)?.setOnClickListener {
            tvLogs.text = ""
        }
    }
}