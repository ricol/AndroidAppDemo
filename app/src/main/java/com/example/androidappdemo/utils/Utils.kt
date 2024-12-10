package com.example.androidappdemo.utils

import android.content.Context
import android.media.Image
import android.util.Log
import kotlin.random.Random
import kotlin.random.nextInt

class Utils {
    companion object {
        fun output(tag: String = AppConstants.TagGeneral, msg: String) {
            if (AppConstants.tagVisibleMapping[tag] == true) {
                Log.d(tag, "[${Thread.currentThread()}] $msg")
            }
        }
        fun getImageLiusisi(ctx: Context): MutableList<Int> {
            val image = mutableListOf<Int>()
            for (i in 1..6) {
                image.add(ctx.resources.getIdentifier("liusisi${i}", "drawable", ctx.packageName))
            }
            return image
        }
    }
}

fun Int.getRandomString(space: Boolean = false): String {
    val str = "abcdefghijklmnopqrstuvwxyz"
    val chars = str.chars().toArray() + str.uppercase().chars().toArray()
    val characters = mutableListOf<Char>()
    chars.forEach {
        characters.add(0, it.toChar())
    }
    val result = mutableListOf<Char>()
    for (i in 0..this) {
        val r = Random.nextInt(1..characters.count())
        result.add(0, characters[r - 1])
        if (space) {
            if (Random.nextInt() % 10 > 7) {
                result.add(0, ' ')
            }
        }
    }
    return result.joinToString(separator = "")
}