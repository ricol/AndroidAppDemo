package com.example.androidappdemo.ui.base

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.activity.ComponentActivity

data class ListCommand(val title: String, val action: () -> Unit)

open class BaseListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildUI()
        afterView()
    }

    open fun getCommand(): Array<ListCommand> {
        return arrayOf()
    }

    open fun afterView() {

    }

    private fun buildUI() {
        val scrollView = ScrollView(this)
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        for (cmd in getCommand()) {
            val btn = Button(this)
            btn.text = cmd.title
            btn.setOnClickListener { cmd.action() }
            layout.addView(btn)
        }

        scrollView.addView(layout)
        setContentView(scrollView)
    }
}
