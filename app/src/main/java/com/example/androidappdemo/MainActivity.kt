package com.example.androidappdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
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
        findViewById<Button>(R.id.btnLaunchActivityWithParams)?.setOnClickListener {
            startActivity(Intent(this, DetailsActivity::class.java).apply { putExtra("param", "Welcome!") })
        }
        findViewById<Button>(R.id.btnLaunchActivityForResult)?.setOnClickListener {
            startActivityForResult(Intent(this, DetailsActivity::class.java), (1..10).random())
        }
        findViewById<Button>(R.id.btnFragment)?.setOnClickListener {
            startActivity(Intent(this, FragmentDemoMainActivity::class.java))
        }
        findViewById<Button>(R.id.btnRecycleView)?.setOnClickListener {
            startActivity(Intent(this, RecycleViewActivity::class.java))
        }
        findViewById<Button>(R.id.btnFragmentCallback)?.setOnClickListener {
            startActivity(Intent(this, FragmentDemoMainActivity::class.java).apply {
                putExtra("callback", true)
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Toast.makeText(this,
            "resultCode: $resultCode, requestCode: $requestCode, data: $data, more: ${data?.getStringExtra("result")}",
            Toast.LENGTH_LONG).show()
    }
}