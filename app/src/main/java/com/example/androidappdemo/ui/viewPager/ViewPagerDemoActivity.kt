package com.example.androidappdemo.ui.viewPager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.androidappdemo.R
import com.example.androidappdemo.ui.base.BaseListActivity
import com.example.androidappdemo.ui.base.ListCommand
import com.example.androidappdemo.utils.getRandomString
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.abs

class ViewPagerDemo2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpagerdemo_layout)

        // Create sample data
        val pageItems =
            listOf(PageItem("Page 1", "This is the first page with a sample description.", R.drawable.liusisi1, // Add your images to drawable folder
                android.R.color.holo_blue_light),
                PageItem("Page 2", "This is the second page with different content.", R.drawable.liusisi2, android.R.color.holo_green_light),
                PageItem("Page 3", "This is the third and final page example.", R.drawable.liusisi3, android.R.color.holo_orange_light))

        // Setup ViewPager2
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val adapter = ViewPagerAdapter(pageItems)
        viewPager.adapter = adapter

        // Optional: Connect TabLayout with ViewPager2
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()

        // Optional: Add page change callback
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position) // Handle page change events
                println("Page changed to: $position")
            }
        })

        // Optional: Set orientation (default is horizontal)
        // viewPager.orientation = androidx.viewpager2.widget.ViewPager2.ORIENTATION_VERTICAL

        // Optional: Disable user swiping
        // viewPager.isUserInputEnabled = false

        // Optional: Set current item programmatically
        // viewPager.setCurrentItem(1, true) // with smooth scroll

        //         Optional: Set page transformer for animations
        viewPager.setPageTransformer { page, position -> // Example: Zoom animation
            val scale = if (position < 0) 1 - 0.3f * -position else 1 - 0.3f * position
            page.scaleX = scale
            page.scaleY = scale

            // Example: Fade animation
            page.alpha = 0.25f + (1 - abs(position))
        }
    }

    data class PageItem(val title: String, val description: String, val imageResId: Int, val colorResId: Int)

    class ViewPagerAdapter(private val items: List<PageItem>) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

        class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
            private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
            private val imageView: ImageView = itemView.findViewById(R.id.imageView)

            fun bind(item: PageItem) {
                titleTextView.text = item.title
                descriptionTextView.text = item.description
                imageView.setImageResource(item.imageResId)
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, item.colorResId))
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
            return ViewPagerViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int = items.size
    }
}

class ScreenSlidePageFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_screen_slide_page, container, false)
}

class ViewPagerDemo1Activity : FragmentActivity() {
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
                    val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_row_item, parent, false)
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
        } else {
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
            } else {
                Toast.makeText(this, "last item", Toast.LENGTH_SHORT).show()
                findViewById<Button>(R.id.btnNext)?.isEnabled = false
            }
        }
        findViewById<Button>(R.id.btnPrevious)?.setOnClickListener {
            if (viewPager2.currentItem > 0) {
                viewPager2.currentItem -= 1
                findViewById<Button>(R.id.btnNext)?.isEnabled = true
                findViewById<Button>(R.id.btnPrevious)?.isEnabled = viewPager2.currentItem > 0
            } else {
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

class ViewPagerListViewActivity : BaseListActivity() {
    override fun getCommand(): Array<ListCommand> {
        return arrayOf(ListCommand("View Pager2 For Custom Data") {
            startActivity(Intent(this, ViewPagerDemo1Activity::class.java))
        }, ListCommand("View Pager2 For Fragment") {
            startActivity(Intent(this, ViewPagerDemo1Activity::class.java).apply {
                putExtra("fragment", true)
            })
        }, ListCommand("View Pager2 With Tabbar") {
            startActivity(Intent(this, ViewPagerDemo1Activity::class.java).apply {
                putExtra("tabbar", true)
                putExtra("fragment", true)
            })
        }, ListCommand("View Pager2") {
            startActivity(Intent(this, ViewPagerDemo2Activity::class.java))
        })
    }
}