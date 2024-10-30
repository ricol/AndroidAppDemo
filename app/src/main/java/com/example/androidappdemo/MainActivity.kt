package com.example.androidappdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnAnimation = findViewById<Button>(R.id.btnAnimation)
        btnAnimation.setOnClickListener {
            startActivity(Intent(this, AnimationActivity::class.java))
        }
        val btnCustomDrawing = findViewById<Button>(R.id.btnCustomDrawing)
        btnCustomDrawing.setOnClickListener {
            startActivity(Intent(this, CustomDrawingActivity::class.java))
        }
    }
}