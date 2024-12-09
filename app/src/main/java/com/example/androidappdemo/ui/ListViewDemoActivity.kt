package com.example.androidappdemo.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.androidappdemo.R
import com.example.androidappdemo.utils.getRandomString

class ListViewDemoActivity: ComponentActivity() {
    private lateinit var listView: ListView
    private var data = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview_demo)
        listView = findViewById(R.id.listview)
        findViewById<Button>(R.id.btnAdd)?.setOnClickListener {
            data.add(0, 10.getRandomString())
        }
        findViewById<Button>(R.id.btnRemove)?.setOnClickListener {
            data.removeAt(data.lastIndex)
        }
        findViewById<Button>(R.id.btnClear)?.setOnClickListener {
            data.clear()
        }

        for (i in 0..100) {
            data.add(0, 10.getRandomString())
        }

        listView.adapter = object : BaseAdapter() {
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
                var textView: TextView?
                if (convertView is TextView) {
                    textView = convertView
                }else {
                    textView = TextView(this@ListViewDemoActivity)
                    Log.d("DEBUG", "creating textview...")
                }
                textView.text = data[position]
                return textView
            }
        }
    }
}