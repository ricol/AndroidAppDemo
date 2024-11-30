package com.example.androidappdemo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.androidappdemo.R

class UIDemoActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_demo)
        findViewById<Button>(R.id.btnStyle)?.setOnClickListener {
            startActivity(Intent(this, StyleDemoActivity::class.java))
        }
    }
}