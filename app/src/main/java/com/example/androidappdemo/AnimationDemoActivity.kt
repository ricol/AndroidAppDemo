package com.example.androidappdemo

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.core.view.children
import com.example.androidappdemo.base.DefaultComponentBaseActivity

class AnimationDemoActivity: DefaultComponentBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_demo)
        val layout = findViewById<LinearLayout>(R.id.linearLayout)
        for (v in layout.children) {
            Log.d(TAG, "view: $v")
            v.setOnClickListener {
                val animationHorizontal = AnimationUtils.loadAnimation(this, R.anim.horizontal)
                v.startAnimation(animationHorizontal)
                val animationVertical = AnimationUtils.loadAnimation(this, R.anim.vertical)
                v.startAnimation(animationVertical)
            }
        }
    }
}