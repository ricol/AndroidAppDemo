package com.example.androidappdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class RecycleViewActivity: ComponentActivity() {
    lateinit var recycleView: RecyclerView
    lateinit var btnAdd: Button
    lateinit var btnDelete: Button
    lateinit var btnClear: Button
    lateinit var adapter: CustomRecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        recycleView = findViewById(R.id.recycleView)
        btnAdd = findViewById(R.id.btnAdd)
        btnDelete = findViewById(R.id.btnDelete)
        btnClear = findViewById(R.id.btnClear)
        btnAdd.setOnClickListener {
            val strings = arrayOf("wang xing he", "wang mei", "ricol wang")
            val images = arrayOf(R.drawable.sun, R.drawable.moon)
            adapter.data.add(Pair(strings.random(), images.random()))
            adapter.notifyItemInserted(adapter.data.count() - 1)
        }
        btnDelete.setOnClickListener {
            if (adapter.data.isNotEmpty()) adapter.data.removeAt(0)
            adapter.notifyItemRemoved(0)
        }
        btnClear.setOnClickListener {
            val total = adapter.data.count()
            adapter.data.clear()
            adapter.notifyItemRangeChanged(0, total)
        }
        adapter = CustomRecycleViewAdapter()
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this)
    }

    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvView: TextView = view.findViewById(R.id.textView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    class CustomRecycleViewAdapter: RecyclerView.Adapter<CustomViewHolder>() {
        var data = mutableListOf<Pair<String, Int>>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_view, parent, false)
            return CustomViewHolder(view)
        }

        override fun getItemCount(): Int {
            return data.count()
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            val value = data[position]
            holder.tvView.text = value.first.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
            holder.imageView.setImageResource(value.second)
        }
    }
}