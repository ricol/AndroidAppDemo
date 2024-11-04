package com.example.androidappdemo

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.androidappdemo.base.DefaultBaseFragment
import com.example.androidappdemo.base.DefaultFragmentBaseActivity

class FragmentDemoMainActivity: DefaultFragmentBaseActivity(), CallBackFragment.Callbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_main)

        findViewById<Button>(R.id.btnAddFragment)?.setOnClickListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
            Log.d(TAG, "$this current fragment: $currentFragment...")
            val fragment = MainFragment()
            Log.d(TAG, "$this add new fragment: $fragment...")
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit()
        }

        findViewById<Button>(R.id.btnReplaceFragment)?.setOnClickListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
            Log.d(TAG, "$this current fragment: $currentFragment...")
            val fragment = DetailsFragment()
            Log.d(TAG, "$this replace with fragment: $fragment...")
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
        }

        findViewById<Button>(R.id.btnClear)?.setOnClickListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
            currentFragment?.let {
                Log.d(TAG, "$this clear fragment: $it...")
                supportFragmentManager.beginTransaction().remove(it).commit()
            }
        }

        val callback = intent.getBooleanExtra("callback", false)
        if (callback) {
            val args = Bundle().apply {
                putString("message", "this is message from the activity")
            }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer,
                    CallBackFragment()
                        .apply { title = "Call callback"; arguments = args }
                ).commit()
        }
    }

    override fun fragmentOnReturn() {
        Toast.makeText(this, "This is callback method defined in the activity and called from the child fragment!", Toast.LENGTH_SHORT).show()
    }
}

class DetailsFragment: DefaultBaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        Log.d(TAG, "$this -> view: $view")
        view.findViewById<ImageView>(R.id.imageView)?.setImageResource(R.drawable.sun)
        view.findViewById<TextView>(R.id.textView).text = (1..100).random().toString()
        return view
    }
}

class MainFragment: DefaultBaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        Log.d(TAG, "$this -> view: $view")
        view.findViewById<ImageView>(R.id.imageView)?.setImageResource(R.drawable.ic_launcher_background)
        view.findViewById<TextView>(R.id.textView).text = (1..100).random().toString()
        return view
    }
}

class CallBackFragment: DefaultBaseFragment() {
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