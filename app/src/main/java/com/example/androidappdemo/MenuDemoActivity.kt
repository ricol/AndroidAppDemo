package com.example.androidappdemo

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class ChildMenuFragment: Fragment(), PopupMenu.OnMenuItemClickListener {
    var menuToLoad: Int? = null
        set(value) {
            Log.d(TAG, "$this setMenuToLoad...$value")
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        Log.d(TAG, "$this onCreateOptionsMenu...")
        menuToLoad?.let {
            inflater.inflate(it, menu)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d(TAG, "$this onCreateView...")
        val layout = LinearLayout(this.context)
        layout.orientation = LinearLayout.VERTICAL
        val textView = TextView(this.context)
        textView.text = "Fragment 1 with menu: $menuToLoad"
        layout.addView(textView)
        val btnPopup = Button(this.context)
        btnPopup.text = "Popup Menu"
        btnPopup.setOnClickListener {
            val popup = PopupMenu(this.context, btnPopup)
            popup.menuInflater.inflate(R.menu.fragment_menu_details, popup.menu)
            popup.setOnMenuItemClickListener(this)
            popup.show()
        }
        layout.addView(btnPopup)
        return layout
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        Toast.makeText(this.context, "tapped popup menu: ${item?.title}", Toast.LENGTH_SHORT).show()
        return true
    }
}

class MenuDemoActivity: FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_demo)
        findViewById<FrameLayout>(R.id.fragmentContainer)?.setBackgroundColor(getColor(R.color.bright_sun))
        findViewById<Button>(R.id.btnFragment1)?.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, ChildMenuFragment().apply { menuToLoad = R.menu.fragment_menu }, "fragment1").commit()
        }
        findViewById<Button>(R.id.btnFragment2)?.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, ChildMenuFragment().apply { menuToLoad = R.menu.fragment_menu_details }, "fragment2").commit()
        }
        findViewById<Button>(R.id.btnClear)?.setOnClickListener {
            supportFragmentManager.findFragmentById(R.id.fragmentContainer)?.apply {
                supportFragmentManager.beginTransaction().remove(this).commit()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.activity_menu_main, menu)
        return true
    }

    override fun onMenuItemSelected(featureId: Int, item: MenuItem): Boolean {
        Toast.makeText(this, "menu selected (featureId: $featureId) (item: ${item.title})", Toast.LENGTH_SHORT).show()
        return super.onMenuItemSelected(featureId, item)
    }
}