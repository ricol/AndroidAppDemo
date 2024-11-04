package com.example.androidappdemo.utils

import android.util.Log

class Utils {
    companion object {
        fun output(tag: String, msg: String) {
            if (AppConstants.tagVisibleMapping[tag] ?: false) {
                Log.d(tag, msg)
            }
        }
    }
}