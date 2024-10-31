package com.example.androidappdemo.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.androidappdemo.R

class MainFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        Log.d(TAG, "$this -> view: $view")
        view.findViewById<ImageView>(R.id.imageView)?.setImageResource(R.drawable.ic_launcher_background)
        view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue_sky))
        return view
    }
}