package com.example.androidappdemo.utils

import android.util.Log

class Utils {
    companion object {
        fun output(tag: String = AppConstants.TagGeneral, msg: String) {
            if (AppConstants.tagVisibleMapping[tag] == true) {
                Log.d(tag, "[${Thread.currentThread()}] $msg")
            }
        }
    }
}