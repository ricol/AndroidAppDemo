package com.example.androidappdemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.androidappdemo.R

class CustomLayoutActivity: ComponentActivity() {
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_demo)
    }
}