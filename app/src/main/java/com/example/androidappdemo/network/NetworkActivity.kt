package com.example.androidappdemo.network

import android.util.Log
import android.widget.Toast
import com.example.androidappdemo.ui.base.BaseListActivity
import com.example.androidappdemo.ui.base.ListCommand
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

class NetworkActivity : BaseListActivity() {
    override fun getCommand(): Array<ListCommand> {
        return arrayOf(ListCommand("REST Service") {
            val retrofit = Retrofit.Builder().baseUrl("https://www.baidu.com").addConverterFactory(ScalarsConverterFactory.create()).build()
            val api = retrofit.create(FlickrAPI::class.java)
            val request = api.fetchContent()
            request.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.i("Response", "message: ${response.message()}, body: ${response.body()}")
                    Toast.makeText(this@NetworkActivity, "success with body size: ${response.body().toString().count()}", Toast.LENGTH_LONG).show()
                    Log.i("Response", "suscess with body: \n${response.body().toString()}")
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e("ERROR", "failure!")
                    Toast.makeText(this@NetworkActivity, "failure with error: ${t}}", Toast.LENGTH_LONG).show()
                    Log.i("Response", "failure with error: $t")
                }
            })
        })
    }
}