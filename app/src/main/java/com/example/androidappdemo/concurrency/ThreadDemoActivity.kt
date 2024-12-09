package com.example.androidappdemo.concurrency

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.androidappdemo.R
import com.example.androidappdemo.utils.Utils
import java.util.concurrent.Executors

class ThreadDemoActivity: ComponentActivity() {
    private lateinit var tvLogs: TextView
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_demo)
        tvLogs = findViewById(R.id.tvLogs)

        findViewById<Button>(R.id.btnHandler)?.setOnClickListener {
            startActivity(Intent(this, HandlerDemoActivity::class.java))
        }

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
            val task = MyAsyncTask(tvLogs, handler)
            task.execute("https://www.baidu.com")
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

    class MyAsyncTask(val tvOutput: TextView, val handle: Handler): AsyncTask<String, Int, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            Utils.output(msg = "onPreExecute...")
            tvOutput.text = "${tvOutput.text}\n[${Thread.currentThread()}] onPreExecute..."
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            Utils.output(msg = "onProgressupdate...${values.first()}")
            tvOutput.text = "${tvOutput.text}\n[${Thread.currentThread()}] onProgressupdate...${values.first()}"
        }

        override fun doInBackground(vararg params: String?): String {
            Utils.output(msg = "params: ${params}")
            var sum: Int = 0
            for (i in 1..100) {
                sum += i
                Thread.sleep(100)
                if (i % 10 == 0) {
                    publishProgress(i)
                    handle.post {
                        tvOutput.text = "${tvOutput.text}\n[${Thread.currentThread()}] doInBackground...i: ${i}, sum: ${sum}"
                    }
                }
            }
            return "${sum}"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Utils.output(msg = "onPostExecute...${result}")
            tvOutput.text = "${tvOutput.text}\n[${Thread.currentThread()}] onPostExecute...${result}"
        }
    }
}