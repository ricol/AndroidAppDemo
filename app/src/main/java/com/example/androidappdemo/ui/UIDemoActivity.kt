package com.example.androidappdemo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.androidappdemo.ui.animations.AnimationListActivity
import com.example.androidappdemo.ui.base.BaseListActivity
import com.example.androidappdemo.ui.base.ListCommand
import com.example.androidappdemo.ui.compose.ComposeDemoActivity
import com.example.androidappdemo.ui.controls.ComponentsDemoActivity
import com.example.androidappdemo.ui.controls.UIControlsDemoActivity
import com.example.androidappdemo.ui.fragments.FragmentDemoMainActivity
import com.example.androidappdemo.ui.graphics.CustomDrawingActivity
import com.example.androidappdemo.ui.layout.LayoutDemoActivity
import com.example.androidappdemo.ui.listview.ListViewDemoActivity
import com.example.androidappdemo.ui.menu.MenuDemoActivity
import com.example.androidappdemo.ui.recycleview.RecycleViewActivity
import com.example.androidappdemo.ui.style.StyleDemoActivity
import com.example.androidappdemo.ui.tabbar.TabBarListDemoActivity
import com.example.androidappdemo.ui.toolbar.ToolbarDemoActivity
import com.example.androidappdemo.ui.viewPager.ViewPagerListViewActivity

class UIDemoActivity : BaseListActivity() {
    override fun getCommand(): Array<ListCommand> {
        return arrayOf(
            ListCommand("Jetpack Compose") {
                startActivity(Intent(this, ComposeDemoActivity::class.java))
            },
            ListCommand("Style") {
                startActivity(Intent(this, StyleDemoActivity::class.java))
            },
            ListCommand("Animation") {
                startActivity(Intent(this, AnimationListActivity::class.java))
            },
            ListCommand("Custom Drawing") {
                startActivity(Intent(this, CustomDrawingActivity::class.java))
            },
            ListCommand("Launch Activity with params") {
                startActivity(Intent(this, DetailsActivity::class.java).apply { putExtra("param", "Welcome!") })
            },
            ListCommand("Launch Activity for result") {
                startActivityForResult(Intent(this, DetailsActivity::class.java), (1..10).random())
            },
            ListCommand("Fragment") {
                startActivity(Intent(this, FragmentDemoMainActivity::class.java))
            },
            ListCommand("Recycle View") {
                startActivity(Intent(this, RecycleViewActivity::class.java))
            },
            ListCommand("Fragment with call back") {
                startActivity(Intent(this, FragmentDemoMainActivity::class.java).apply {
                    putExtra("callback", true)
                })
            },
            ListCommand("Components") {
                startActivity(Intent(this, ComponentsDemoActivity::class.java))
            },
            ListCommand("Menu") {
                startActivity(Intent(this, MenuDemoActivity::class.java))
            },
            ListCommand("Toolbar") {
                startActivity(Intent(this, ToolbarDemoActivity::class.java))
            },
            ListCommand("List View") {
                startActivity(Intent(this, ListViewDemoActivity::class.java))
            },
            ListCommand("Android UI Controls") {
                startActivity(Intent(this, UIControlsDemoActivity::class.java))
            },
            ListCommand("Layout") {
                startActivity(Intent(this, LayoutDemoActivity::class.java))
            },
            ListCommand("ViewPager2") {
                startActivity(Intent(this, ViewPagerListViewActivity::class.java))
            },
            ListCommand("TabBar") {
                startActivity(Intent(this, TabBarListDemoActivity::class.java))
            },
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Toast.makeText(this, "resultCode: $resultCode, requestCode: $requestCode, data: $data, more: ${data?.getStringExtra("result")}", Toast.LENGTH_LONG)
            .show()
    }
}

class DetailsActivity : ComponentActivity() {
    lateinit var textView: TextView
    lateinit var btnSetResult: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this)
        textView = TextView(this)
        layout.addView(textView)
        btnSetResult = Button(this)
        btnSetResult.text = "Set Result and Return"
        btnSetResult.setOnClickListener {
            setResult(RESULT_OK, Intent().also {
                it.putExtra("result", "this is message from details activity.")
            })
            finish()
        }
        layout.addView(btnSetResult)
        setContentView(layout)
        intent.getStringExtra("param")?.also {
            textView.text = "param: $it"
        }
    }
}