package com.example.androidappdemo.ui

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.androidappdemo.R
import com.example.androidappdemo.utils.getRandomString

class ViewPageDemoActivity: ComponentActivity() {
    lateinit var viewPager2: ViewPager2
    lateinit var callback: OnPageChangeCallback
    var data = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpage_demo)
        for (i in 0..10) {
            data.add(0, 10.getRandomString())
        }
        viewPager2 = findViewById(R.id.viewPage2)
        viewPager2.adapter = object: RecyclerView.Adapter<MyViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                TODO("Not yet implemented")
            }

            override fun getItemCount(): Int {
                return  data.count()
            }

            override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                TODO("Not yet implemented")
            }
        }
        callback = object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

            }
        }
        viewPager2.registerOnPageChangeCallback(callback)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewPager2.unregisterOnPageChangeCallback(callback)
    }

    inner class MyViewHolder: RecyclerView.ViewHolder(this.viewPager2.rootView) {
        lateinit var lblText: TextView
    }
}