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
        findViewById<Button>(R.id.btnAnimation)?.setOnClickListener {
            startActivity(Intent(this, AnimationActivity::class.java))
        }
        findViewById<Button>(R.id.btnCustomDrawing)?.setOnClickListener {
            startActivity(Intent(this, CustomDrawingActivity::class.java))
        }
        findViewById<Button>(R.id.btnCustomDrawingWithParameters)?.setOnClickListener {
            startActivity(Intent(this, CustomDrawingActivity::class.java).apply { putExtra("param", "Welcome!") })
        }
        findViewById<Button>(R.id.btnCustomDrawingForResult)?.setOnClickListener {
            startActivityForResult(Intent(this, CustomDrawingActivity::class.java), 0)
        }
        findViewById<Button>(R.id.btnFragment)?.setOnClickListener {
            startActivity(Intent(this, FragmentDemoMainActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "resultCode: $resultCode, requestCode: $requestCode, data: $data, more: ${data?.getStringExtra("result")}")
    }
}