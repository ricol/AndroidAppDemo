package com.example.androidappdemo

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.util.concurrent.Executors

class ThreadDemoActivity: ComponentActivity() {
    private lateinit var tvLogs: TextView
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_demo)
        tvLogs = findViewById(R.id.tvLogs)

        findViewById<Button>(R.id.btnThread)?.setOnClickListener {
            val thread1 = Thread() {
                sleepAndWakeup(3)
            }
            thread1.start()
            val thread2 = Thread() {
                sleepAndWakeup(2)
            }
            thread2.start()
        }
        findViewById<Button>(R.id.btnSingleThreadPool)?.setOnClickListener {
            val pool = Executors.newSingleThreadExecutor()
            pool.submit {
                sleepAndWakeup(3)
            }
            pool.submit {
                sleepAndWakeup(2)
            }
        }
        findViewById<Button>(R.id.btnFixedThreadPool)?.setOnClickListener {
            val pool = Executors.newFixedThreadPool(10)
            pool.submit {
                sleepAndWakeup(3)
            }
            pool.submit {
                sleepAndWakeup(2)
            }
        }
        findViewById<Button>(R.id.btnCachedThreadPool)?.setOnClickListener {
            val pool = Executors.newCachedThreadPool()
            pool.submit {
                sleepAndWakeup(3)
            }
            pool.submit {
                sleepAndWakeup(2)
            }
        }
        findViewById<Button>(R.id.btnAsyncTask)?.setOnClickListener {
        }
        findViewById<Button>(R.id.btnClear)?.setOnClickListener {
            tvLogs.text = ""
        }
    }

    private fun sleepAndWakeup(n: Int) {
        fun gotoSleep(n: Int): Int {
            val txt = "[${Thread.currentThread()}] go to sleep for ${n} seconds..."
            Log.d("THREAD", txt)
            handler.post {
                tvLogs.text = "${tvLogs.text}\n${txt}"
            }
            Thread.sleep((n * 1000).toLong())
            return n
        }

        fun wakeup(n: Int) {
            val txt = "[${Thread.currentThread()}] wake up after ${n} seconds."
            Log.d("THREAD", txt)
            handler.post {
                tvLogs.text = "${tvLogs.text}\n${txt}"
            }
        }
        wakeup(gotoSleep(n))
    }
}