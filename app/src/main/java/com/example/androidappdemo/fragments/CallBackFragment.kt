package com.example.androidappdemo.fragments

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class CallBackFragment: Fragment() {
    interface Callbacks {
        fun fragmentOnReturn()
    }
    var callBack: Callbacks? = null
    var title: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = context as? Callbacks
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val layout = LinearLayout(this.context)
        layout.orientation = LinearLayout.VERTICAL
        val textView = TextView(this.context)
        textView.text = arguments?.getString("message", "no message from activity")
        layout.addView(textView)
        val btn = Button(this.context)
        btn.text = title ?: "Callback"
        btn.setOnClickListener {
            Log.d(TAG, "calling callback...$callBack")
            callBack?.fragmentOnReturn()
        }
        layout.addView(btn)
        return layout
    }
}