package com.example.androidappdemo

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.androidappdemo.concurrency.ThreadDemoActivity
import com.example.androidappdemo.network.NetworkActivity
import com.example.androidappdemo.notification.NotificationDemoActivity
import com.example.androidappdemo.storage.StorageDemoActivity
import com.example.androidappdemo.ui.UIDemoActivity
import com.google.gson.Gson
import org.json.JSONObject
import java.util.Dictionary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnNotification)?.setOnClickListener {
            startActivity(Intent(this, NotificationDemoActivity::class.java))
        }
        findViewById<Button>(R.id.btnUI)?.setOnClickListener {
            startActivity(Intent(this, UIDemoActivity::class.java))
        }
        findViewById<Button>(R.id.btnNetwork)?.setOnClickListener {
            startActivity(Intent(this, NetworkActivity::class.java))
        }
        findViewById<Button>(R.id.btnStorage)?.setOnClickListener {
            startActivity(Intent(this, StorageDemoActivity::class.java))
        }
        findViewById<Button>(R.id.btnMultiThreading)?.setOnClickListener {
            startActivity(Intent(this, ThreadDemoActivity::class.java))
        }
        findViewById<Button>(R.id.btnTest)?.setOnClickListener {
            try {
                val str = """
                    {
                    "name": "wangxinghe",
                    "age": 43,
                    "male": true
                    }
                """.trimIndent()
                val data = JSONObject(str)
                Log.i("JSON", "load string as JSONObject: $data")
            } catch (e: Exception) {
                Log.e("JSON", "failed to parse. error: ${e}")
            }

            for (file in arrayOf(R.raw.lang_en, R.raw.lang_es, R.raw.lang_fr)) {
                try {
                    val jsonString = readJsonFromRaw(file)
                    val jsonObject = JSONObject(jsonString)
                    Log.i("JSON", "loaded json file $file as JSONObject and result: ${jsonObject.length()}")
                } catch (e: Exception) {
                    Log.e("JSON", "failed to parse. error: ${e}")
                }
            }
        }
    }
}

fun Context.readJsonFromRaw(resId: Int): String {
    return resources.openRawResource(resId).bufferedReader().use { it.readText() }
}