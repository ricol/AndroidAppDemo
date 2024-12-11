package com.example.androidappdemo.ui

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.ComponentActivity

class LayoutDemoActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        val btnFrame = Button(this)
        btnFrame.text = "Frame Layout"
        layout.addView(btnFrame)
        val btnHorizonalLinearLayout = Button(this)
        btnHorizonalLinearLayout.text = "Horizonal Linear Layout"
        layout.addView(btnHorizonalLinearLayout)
        val btnVerticalLinearLayout = Button(this)
        btnVerticalLinearLayout.text = "Vertical Linear Layout"
        layout.addView(btnVerticalLinearLayout)
        val btnTableLayout = Button(this)
        btnTableLayout.text = "Table Layout"
        layout.addView(btnTableLayout)
        val btnConstraintsLayout = Button(this)
        btnConstraintsLayout.text = "Constraint Layout"
        layout.addView(btnConstraintsLayout)
        setContentView(layout)
    }
}