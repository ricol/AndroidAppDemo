package com.example.androidappdemo

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat

class AnimationActivity: ComponentActivity() {
    private lateinit var sceneView: View
    private lateinit var sunView: View
    private lateinit var skyView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
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