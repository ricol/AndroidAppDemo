package com.example.androidappdemo.storage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.edit
import com.example.androidappdemo.R

class StorageDemoActivity: ComponentActivity() {
    private val sharedPreferenceName = "MySharedPreference"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage_demo)
        findViewById<Button>(R.id.btnSetSharedPreference)?.setOnClickListener {
            val sharedPreferences = this.getSharedPreferences(sharedPreferenceName, 0)
            sharedPreferences.edit {
                this.putString("name", "Ricol Wang")
                this.putInt("age", 40)
            }
        }
        findViewById<Button>(R.id.btnGetSharedPreference)?.setOnClickListener {
            val name = getSharedPreferences(sharedPreferenceName, 0).getString("name", "no value")
            val age = getSharedPreferences(sharedPreferenceName, 0).getInt("age", -1)
            Toast.makeText(this, "name: ${name}, age: ${age}", Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.btnClearSharedPreference)?.setOnClickListener {
            getSharedPreferences(sharedPreferenceName, 0).edit {
                clear()
            }
        }
    }
}