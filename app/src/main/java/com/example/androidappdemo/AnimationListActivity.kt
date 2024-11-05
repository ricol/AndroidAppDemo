package com.example.androidappdemo

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.example.androidappdemo.base.DefaultComponentBaseActivity

class AnimationListActivity: DefaultComponentBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this)
        val btnPropertyAnimation = Button(this)
        btnPropertyAnimation.text = "Property Animation"
        btnPropertyAnimation.setOnClickListener {
            startActivity(Intent(this, PropertyAnimationActivity::class.java))
        }
        layout.addView(btnPropertyAnimation)
        val btnShakeAnimation = Button(this)
        btnShakeAnimation.text = "Shake Animation"
        layout.addView(btnShakeAnimation)
        btnShakeAnimation.setOnClickListener {
            startActivity(Intent(this, LoadAnimationDemoActivity::class.java))
        }
        setContentView(layout)
    }
}

class PropertyAnimationActivity: ComponentActivity() {
    private lateinit var sceneView: View
    private lateinit var sunView: View
    private lateinit var skyView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_animation)
        sceneView = findViewById(R.id.scene)
        skyView = findViewById(R.id.sky)
        sunView = findViewById(R.id.sun)
        sceneView.setOnClickListener {
            startAnimation()
        }
    }

    private fun startAnimation() {
        val sunYStart = sunView.top.toFloat()
        val sunYEnd = skyView.height.toFloat()
        val heightAnimator = ObjectAnimator.ofFloat(sunView, "y", sunYStart, sunYEnd).setDuration(3000)
        heightAnimator.interpolator = AccelerateInterpolator()
        val sunsetSkyAnimator = ObjectAnimator.ofInt(skyView, "backgroundColor", blueSkyColor, sunsetSkyColor).setDuration(3000)
        sunsetSkyAnimator.setEvaluator(ArgbEvaluator())
        val nightSkyAnimator = ObjectAnimator.ofInt(skyView, "backgroundColor", sunsetSkyColor, nightskyColor).setDuration(1500)
        nightSkyAnimator.setEvaluator(ArgbEvaluator())
        val animatorSet = AnimatorSet()
        animatorSet.play(heightAnimator).with(sunsetSkyAnimator).before(nightSkyAnimator)
        animatorSet.start()
    }

    private val blueSkyColor: Int by lazy { ContextCompat.getColor(this, R.color.blue_sky) }
    private val sunsetSkyColor: Int by lazy { ContextCompat.getColor(this, R.color.sunset_sky) }
    private val nightskyColor: Int by lazy { ContextCompat.getColor(this, R.color.night_sky) }
}

class LoadAnimationDemoActivity: DefaultComponentBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_animation)
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