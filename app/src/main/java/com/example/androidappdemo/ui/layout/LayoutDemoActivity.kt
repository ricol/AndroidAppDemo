package com.example.androidappdemo.ui.layout

import android.content.Intent
import com.example.androidappdemo.ui.base.BaseListActivity
import com.example.androidappdemo.ui.base.ListCommand

class LayoutDemoActivity : BaseListActivity() {
    override fun getCommand(): Array<ListCommand> {
        return arrayOf(
            ListCommand("Frame Layout") {

            },
            ListCommand("Horizonal Linear Layout") {

            },
            ListCommand("Vertical Linear Layout") {

            },
            ListCommand("Table Layout") {

            },
            ListCommand("Constraint Layout") {

            },

            ListCommand("Custom Layout") {
                startActivity(Intent(this, CustomLayoutActivity::class.java))
            },
        )
    }
}