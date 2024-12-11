package com.example.androidappdemo.ui

import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.androidappdemo.R

class UIControlsDemoActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_controls_demo)
        findViewById<RadioGroup>(R.id.radioGroup)?.setOnCheckedChangeListener { group, checkedId ->
            val rb = findViewById<RadioButton>(group.checkedRadioButtonId)
            Log.d("DEBUG", "rb: ${rb.text}")
            Toast.makeText(this, "rb: ${rb.text}", Toast.LENGTH_SHORT).show()
        }
        findViewById<CheckBox>(R.id.checkBox)?.setOnClickListener {
            Log.d("DEBUG", "checked: ${findViewById<CheckBox>(R.id.checkBox).isChecked}")
            Toast.makeText(this, "checked: ${findViewById<CheckBox>(R.id.checkBox).isChecked}", Toast.LENGTH_SHORT).show()
        }
        findViewById<ImageView>(R.id.imageView)?.setImageDrawable(getDrawable(R.drawable.liusisi1))
    }
}