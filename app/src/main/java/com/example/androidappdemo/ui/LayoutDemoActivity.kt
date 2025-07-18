package com.example.androidappdemo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.ComponentActivity

class LayoutDemoActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val btnFrame = Button(this)
        btnFrame.text = "Frame Layout"
        layout.addView(btnFrame)

        val btnHorizonalLinearLayout = Button(this)
        btnHorizonalLinearLayout.text = "Horizonal Linear Layout"
        layout.addView(btnHorizonalLinearLayout)

        val btnVerticalLinearLayout = Button(this)
        btnVerticalLinearLayout.text = "Vertical Linear Layout"
        layout.addView(btnVerticalLinearLayout)

        val btnTableLayout = Button(this)
        btnTableLayout.text = "Table Layout"
        layout.addView(btnTableLayout)

        val btnConstraintsLayout = Button(this)
        btnConstraintsLayout.text = "Constraint Layout"
        layout.addView(btnConstraintsLayout)

        val btnTabbarLayout = Button(this)
        btnTabbarLayout.text = "Tabbar Layout"
        btnTabbarLayout.setOnClickListener {
            startActivity(Intent(this, TabbarDemoActivity::class.java).apply {
                putExtra("autoTabItem", false)
            })
        }
        layout.addView(btnTabbarLayout)

        val btnTabbarWithTabItemLayout = Button(this)
        btnTabbarWithTabItemLayout.text = "Tabbar Layout With TabItem"
        btnTabbarWithTabItemLayout.setOnClickListener {
            startActivity(Intent(this, TabbarDemoActivity::class.java))
        }
        layout.addView(btnTabbarWithTabItemLayout)

        val btnViewPage = Button(this)
        btnViewPage.text = "View Page For Custom Data"
        btnViewPage.setOnClickListener {
            startActivity(Intent(this, ViewPageDemoActivity::class.java))
        }
        layout.addView(btnViewPage)

        val btnViewPageForFragment = Button(this)
        btnViewPageForFragment.text = "View Page For Fragment"
        btnViewPageForFragment.setOnClickListener {
            startActivity(Intent(this, ViewPageDemoActivity::class.java).apply {
                putExtra("fragment", true)
            })
        }
        layout.addView(btnViewPageForFragment)

        val btnViewPageWithTabbar = Button(this)
        btnViewPageWithTabbar.text = "View Page With Tabbar"
        btnViewPageWithTabbar.setOnClickListener {
            startActivity(Intent(this, ViewPageDemoActivity::class.java).apply {
                putExtra("tabbar", true)
                putExtra("fragment", true)
            })
        }
        layout.addView(btnViewPageWithTabbar)

        val btnCustomLayout = Button(this)
        btnCustomLayout.text = "Custom Layout"
        btnCustomLayout.setOnClickListener {
            startActivity(Intent(this, CustomLayoutActivity::class.java))
        }
        layout.addView(btnCustomLayout)

        setContentView(layout)
    }
}