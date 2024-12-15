package com.example.androidappdemo.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.androidappdemo.R
import com.example.androidappdemo.utils.getRandomString
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ScreenSlidePageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_screen_slide_page, container, false)
}

class ViewPageDemoActivity : FragmentActivity() {
    lateinit var viewPager2: ViewPager2
    lateinit var callback: OnPageChangeCallback
    lateinit var tabLayout: TabLayout
    var data = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpage_demo)
        viewPager2 = findViewById(R.id.viewPage2)
        tabLayout = findViewById(R.id.tab_layout)

        for (i in 0..3) {
            data.add(0, 10.getRandomString())
        }

        val isFragment = intent.getBooleanExtra("fragment", false)
        val isTabbar = intent.getBooleanExtra("tabbar", false)
        if (isFragment) {
            class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
                override fun getItemCount(): Int = data.count()
                override fun createFragment(position: Int): Fragment = ScreenSlidePageFragment()
            }
            viewPager2.adapter = ScreenSlidePagerAdapter(this)
        } else {
            viewPager2.adapter = object : RecyclerView.Adapter<MyViewHolder>() {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.fragment_row_item, parent, false)
                    return MyViewHolder(view)
                }

                override fun getItemCount(): Int {
                    return data.count()
                }

                override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                    holder.lblText.text = data[position]
                }
            }
        }

        if (isTabbar) {
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                tab.text = "OBJECT ${(position + 1)}"
                Log.d("DEBUG", "OBJECT ${(position + 1)}")
            }.attach()
        }else {
            tabLayout.visibility = View.GONE
        }

        callback = object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("DEBUG", "onPageSelected: $position")
                findViewById<Button>(R.id.btnPrevious)?.isEnabled = viewPager2.currentItem > 0
                findViewById<Button>(R.id.btnNext)?.isEnabled = viewPager2.currentItem < data.count() - 1
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                Log.d("DEBUG", "onPageScrollStateChanged: $state")
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                Log.d("DEBUG", "onPageScrolled: $position, positionOffset: $positionOffset, positionOffsetPixels: $positionOffsetPixels")
            }
        }
        viewPager2.registerOnPageChangeCallback(callback)

        findViewById<Button>(R.id.btnPrevious)?.isEnabled = viewPager2.currentItem > 0
        findViewById<Button>(R.id.btnNext)?.isEnabled = viewPager2.currentItem < data.count() - 1
        findViewById<Button>(R.id.btnNext)?.setOnClickListener {
            if (viewPager2.currentItem < data.count() - 1) {
                viewPager2.currentItem += 1
                findViewById<Button>(R.id.btnPrevious)?.isEnabled = true
                findViewById<Button>(R.id.btnNext)?.isEnabled = viewPager2.currentItem < data.count() - 1
            }else {
                Toast.makeText(this, "last item", Toast.LENGTH_SHORT).show()
                findViewById<Button>(R.id.btnNext)?.isEnabled = false
            }
        }
        findViewById<Button>(R.id.btnPrevious)?.setOnClickListener {
            if (viewPager2.currentItem > 0) {
                viewPager2.currentItem -= 1
                findViewById<Button>(R.id.btnNext)?.isEnabled = true
                findViewById<Button>(R.id.btnPrevious)?.isEnabled = viewPager2.currentItem > 0
            }else {
                Toast.makeText(this, "first item", Toast.LENGTH_SHORT).show()
                findViewById<Button>(R.id.btnPrevious)?.isEnabled = false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewPager2.unregisterOnPageChangeCallback(callback)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lblText: TextView = itemView.findViewById(R.id.textView)
    }
}