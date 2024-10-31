package com.example.androidappdemo

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import com.example.androidappdemo.fragments.DetailsFragment
import com.example.androidappdemo.fragments.MainFragment

class FragmentDemoMainActivity: FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_main)

        findViewById<Button>(R.id.btnAddFragment)?.setOnClickListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
            Log.d(TAG, "$this current fragment: $currentFragment...")
            val fragment = MainFragment()
            Log.d(TAG, "$this add new fragment: $fragment...")
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit()
        }

        findViewById<Button>(R.id.btnReplaceFragment)?.setOnClickListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
            Log.d(TAG, "$this current fragment: $currentFragment...")
            val fragment = DetailsFragment()
            Log.d(TAG, "$this replace with fragment: $fragment...")
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
        }

        findViewById<Button>(R.id.btnClear)?.setOnClickListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
            currentFragment?.let {
                Log.d(TAG, "$this clear fragment: $it...")
                supportFragmentManager.beginTransaction().remove(it).commit()
            }
        }
    }
}