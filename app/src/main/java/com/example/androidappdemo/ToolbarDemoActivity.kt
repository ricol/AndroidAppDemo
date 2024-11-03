package com.example.androidappdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView

class ToolbarDemoActivity: FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        val container = FragmentContainerView(this)
        container.id = R.id.fragmentContainer
        layout.addView(container)
        val bottomLayout = LinearLayout(this)
        bottomLayout.orientation = LinearLayout.HORIZONTAL
        val btnFragment1 = Button(this)
        btnFragment1.text = "Fragment 1"
        btnFragment1.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, ToolbarFragment().apply { message = "Fragment 1" }).commit()
        }
        bottomLayout.addView(btnFragment1)
        val btnFragment2 = Button(this)
        btnFragment2.text = "Fragment 2"
        btnFragment2.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, ToolbarFragment().apply { message = "Fragment 2" }).commit()
        }
        bottomLayout.addView(btnFragment2)
        layout.addView(bottomLayout)
        setContentView(layout)

        val toolBar = Toolbar(this)
    }
}

class ToolbarFragment: Fragment() {
    var message: String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val textView = TextView(this.context)
        textView.text = message
        return textView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}