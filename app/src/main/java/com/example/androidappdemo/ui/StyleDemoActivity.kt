package com.example.androidappdemo.ui

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.androidappdemo.R

class StyleDemoActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_style_demo)
        findViewById<Button>(R.id.btnDemo1)?.setOnClickListener {

        }
    }
}