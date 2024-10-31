package com.example.androidappdemo.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.androidappdemo.R

class DetailsFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        Log.d(TAG, "$this -> view: $view")
        view.findViewById<ImageView>(R.id.imageView)?.setImageResource(R.drawable.sun)
        view.findViewById<TextView>(R.id.textView).text = (1..100).random().toString()
        return view
    }
}