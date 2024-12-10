package com.example.androidappdemo.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.androidappdemo.R
import com.example.androidappdemo.utils.Utils
import com.example.androidappdemo.utils.getRandomString

class ListViewDemoActivity: ComponentActivity() {
    private lateinit var listView: ListView
    private var data = mutableListOf<Pair<String, Int?>>()
    private lateinit var images: List<Int>
    private lateinit var adapter: BaseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview_demo)

        images = Utils.getImageLiusisi(this)
        listView = findViewById(R.id.listview)
        findViewById<Button>(R.id.btnAdd)?.setOnClickListener {
            data.add(0, Pair(10.getRandomString(), images.random()))
            adapter.notifyDataSetChanged()
        }
        findViewById<Button>(R.id.btnRemove)?.setOnClickListener {
            if (data.isEmpty()) return@setOnClickListener
            data.removeAt(data.lastIndex)
            adapter.notifyDataSetChanged()
        }
        findViewById<Button>(R.id.btnClear)?.setOnClickListener {
            data.clear()
            adapter.notifyDataSetChanged()
        }

        for (i in 0..10) {
            data.add(0, Pair(10.getRandomString(space = true), images.random()))
        }

        adapter = object : BaseAdapter() {
            override fun getCount(): Int {
                return data.count()
            }

            override fun getItem(position: Int): Any {
                return data[position]
            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                val v: PlaceHolderView
                if (convertView is PlaceHolderView) {
                    v = convertView
                    Log.d("DEBUG", "reusing convertView...")
                }else {
                    v = PlaceHolderView()
                    Log.d("DEBUG", "creating PlaceHolderView...")
                }
                v.textView.text = data[position].first
                data[position].second?.let { v.imageView.setImageResource(it) }
                return v
            }

            inner class PlaceHolderView: LinearLayout(this@ListViewDemoActivity) {
                var imageView: ImageView
                var textView: TextView

                init {
                    orientation = VERTICAL
                    imageView = ImageView(this@ListViewDemoActivity)
                    imageView.layoutParams = ViewGroup.LayoutParams(300, 300)
                    textView = TextView(this@ListViewDemoActivity)
                    addView(imageView)
                    addView(textView)
                }
            }
        }

        listView.adapter = adapter
    }
}