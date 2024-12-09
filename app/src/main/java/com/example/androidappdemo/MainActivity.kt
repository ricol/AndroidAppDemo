package com.example.androidappdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.androidappdemo.network.NetworkActivity
import com.example.androidappdemo.storage.StorageDemoActivity
import com.example.androidappdemo.ui.UIDemoActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnNotification)?.setOnClickListener {
            startActivity(Intent(this, NotificationDemoActivity::class.java))
        }
        findViewById<Button>(R.id.btnHandler)?.setOnClickListener {
            startActivity(Intent(this, HandlerDemoActivity::class.java))
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
    }
}