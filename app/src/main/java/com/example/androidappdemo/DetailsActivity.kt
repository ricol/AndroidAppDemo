package com.example.androidappdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity

class DetailsActivity: ComponentActivity() {
    lateinit var textView: TextView
    lateinit var btnSetResult: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this)
        textView = TextView(this)
        layout.addView(textView)
        btnSetResult = Button(this)
        btnSetResult.text = "Set Result and Return"
        btnSetResult.setOnClickListener {
            setResult(RESULT_OK, Intent().also {
                it.putExtra("result", "this is message from details activity.")
            })
            finish()
        }
        layout.addView(btnSetResult)
        setContentView(layout)
        intent.getStringExtra("param")?.also {
            textView.text = "param: $it"
        }
    }
}