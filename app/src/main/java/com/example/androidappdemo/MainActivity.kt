package com.example.androidappdemo

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnAnimation = findViewById<Button>(R.id.btnAnimation)
        btnAnimation?.setOnClickListener {
            startActivity(Intent(this, AnimationActivity::class.java))
        }
        val btnCustomDrawing = findViewById<Button>(R.id.btnCustomDrawing)
        btnCustomDrawing?.setOnClickListener {
            startActivity(Intent(this, CustomDrawingActivity::class.java))
        }
        val btnCustomDrawingWithParameters = findViewById<Button>(R.id.btnCustomDrawingWithParameters)
        btnCustomDrawingWithParameters?.setOnClickListener {
            startActivity(Intent(this, CustomDrawingActivity::class.java).apply { putExtra("param", "Welcome!") })
        }
        val btnCustomDrawingForResult = findViewById<Button>(R.id.btnCustomDrawingForResult)
        btnCustomDrawingForResult?.setOnClickListener {
            startActivityForResult(Intent(this, CustomDrawingActivity::class.java), 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "resultCode: $resultCode, requestCode: $requestCode, data: $data, more: ${data?.getStringExtra("result")}")
    }
}