package com.example.androidappdemo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.androidappdemo.R
import org.json.JSONObject

class UIDemoActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_demo)
        findViewById<Button>(R.id.btnStyle)?.setOnClickListener {
            startActivity(Intent(this, StyleDemoActivity::class.java))
        }
        findViewById<Button>(R.id.btnAnimation)?.setOnClickListener {
            startActivity(Intent(this, AnimationListActivity::class.java))
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
        findViewById<Button>(R.id.btnComponents)?.setOnClickListener {
            startActivity(Intent(this, ComponentsDemoActivity::class.java))
        }
        findViewById<Button>(R.id.btnMenu)?.setOnClickListener {
            startActivity(Intent(this, MenuDemoActivity::class.java))
        }
        findViewById<Button>(R.id.btnToolbar)?.setOnClickListener {
            startActivity(Intent(this, ToolbarDemoActivity::class.java))
        }
        findViewById<Button>(R.id.btnListView)?.setOnClickListener {
            startActivity(Intent(this, ListViewDemoActivity::class.java))
        }
        findViewById<Button>(R.id.btnUIControls)?.setOnClickListener {
            startActivity(Intent(this, UIControlsDemoActivity::class.java))
        }
        findViewById<Button>(R.id.btnLayout)?.setOnClickListener {
            startActivity(Intent(this, LayoutDemoActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Toast.makeText(this,
            "resultCode: $resultCode, requestCode: $requestCode, data: $data, more: ${data?.getStringExtra("result")}",
            Toast.LENGTH_LONG).show()
    }
}