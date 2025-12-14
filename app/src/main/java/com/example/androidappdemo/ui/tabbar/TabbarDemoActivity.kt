package com.example.androidappdemo.ui.tabbar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.example.androidappdemo.R
import com.example.androidappdemo.ui.base.BaseListActivity
import com.example.androidappdemo.ui.base.ListCommand
import com.example.androidappdemo.ui.layout.CustomLayoutActivity
import com.google.android.material.tabs.TabLayout

class TabbarDemoActivity : ComponentActivity() {
    lateinit var tablayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val autoTabItem = intent.getBooleanExtra("autoTabItem", true)
        if (autoTabItem) {
            setContentView(R.layout.activity_tabbar_with_tabitem_demo)
        } else {
            setContentView(R.layout.activity_tabbar_demo)
            tablayout = findViewById(R.id.tab_layout)
            tablayout.addTab(tablayout.newTab().setText("Tab-1"))
            tablayout.addTab(tablayout.newTab().setText("Tab-2"))
            tablayout.addTab(tablayout.newTab().setText("Tab-3"))
        }

        tablayout = findViewById(R.id.tab_layout)
        tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d("DEBUG", "onTabSelected: ${tab}")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d("DEBUG", "onTabUnselected: ${tab}")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d("DEBUG", "onTabReselected: ${tab}")
            }
        })
    }
}

class TabBarListDemoActivity : BaseListActivity() {
    override fun getCommand(): Array<ListCommand> {
        return arrayOf(
            ListCommand("Tabbar Layout") {
                startActivity(Intent(this, TabbarDemoActivity::class.java).apply {
                    putExtra("autoTabItem", false)
                })
            },

            ListCommand("Tabbar Layout With TabItem") {
                startActivity(Intent(this, TabbarDemoActivity::class.java))
            },
        )
    }
}