package com.example.androidappdemo

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import com.example.androidappdemo.base.DefaultComponentBaseActivity
import java.lang.Thread.sleep

class HandlerDemoActivity: DefaultComponentBaseActivity() {
    private val handler = MyHandler()
    private var otherThread: MyThreadWithLooper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_demo)
        findViewById<Button>(R.id.btnSendMessageToMainHandler)?.setOnClickListener {
            MyThread1(handler).start()
        }
        findViewById<Button>(R.id.btnExecRunnableOnMainHandler)?.setOnClickListener {
            MyThread2(handler).start()
        }
        findViewById<Button>(R.id.btnCreateThreadWithLooper)?.setOnClickListener {
            val thread = MyThreadWithLooper()
            thread.start()
            otherThread = thread
        }
        findViewById<Button>(R.id.btnSendMsgToThreadWithLooper)?.setOnClickListener {
            Log.d(TAG, "[${Thread.currentThread()}]${this}...send from main thread to thread with looper...")
            otherThread?.myThreadHandler?.sendMessage(Message.obtain().apply {
                data = Bundle().apply {
                    putString("message", "this is hidden message sent from the main thread to the new thread with looper")
                }
            })
            Log.d(TAG, "[${Thread.currentThread()}]${this}...send from main thread to thread with looper...done")
        }
        findViewById<Button>(R.id.btnSendQuitMsgToThreadWithLooper)?.setOnClickListener {
            Log.d(TAG, "[${Thread.currentThread()}]${this}...send from main thread to thread with looper...")
            otherThread?.myThreadHandler?.sendMessage(Message.obtain().apply {
                data = Bundle().apply {
                    putString("message", "quit")
                }
            })
            Log.d(TAG, "[${Thread.currentThread()}]${this}...send from main thread to thread with looper...done")
        }
        findViewById<Button>(R.id.btnPostRunnableToThreadWithLooper)?.setOnClickListener {
            Log.d(TAG, "[${Thread.currentThread()}]${this}...post runnable from main thread to thread with looper...")
            otherThread?.myThreadHandler?.post {
                sleep(1000)
                Log.d(TAG, "[${Thread.currentThread()}]${this}...runnable...")
            }
            Log.d(TAG, "[${Thread.currentThread()}]${this}...post runnable from main thread to thread with looper...done")
        }
    }

    class MyHandler: Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            Log.d(TAG, "[${Thread.currentThread()}] ${this} handle message: ${msg.data.getString("message")}")
        }
    }
}

class MyThread1(private val handler: Handler): Thread() {
    override fun run() {
        super.run()
        Log.d(TAG, "[${currentThread()}]${this}...and sleeping...")
        sleep(1000)
        Log.d(TAG, "[${currentThread()}]${this}...and wake up...sending message...")
        handler.sendMessage(Message.obtain().apply {
            val bundle = Bundle()
            bundle.putString("message", "this is message in the bundle")
            this.data = bundle
        })
        Log.d(TAG, "[${currentThread()}]${this}...message sent to the handler")
    }
}

class MyThread2(private val handler: Handler): Thread() {
    override fun run() {
        super.run()
        Log.d(TAG, "[${currentThread()}]${this}...and sleeping...")
        sleep(1000)
        handler.post {
            Log.d(TAG, "[${currentThread()}]${this}...runnable...")
        }
        Log.d(TAG, "[${currentThread()}]${this}...runnable sent to the handler")
    }
}

class MyThreadWithLooper: Thread() {
    var myThreadHandler: Handler? = null
    override fun run() {
        super.run()
        Looper.prepare()
        Log.d(TAG, "[${currentThread()}]${this}...and sleeping...")
        sleep(1000)
        Log.d(TAG, "[${currentThread()}]${this}...wake up and create thread handler...")
        myThreadHandler = MyHandler()
        Log.d(TAG, "[${currentThread()}]${this}...start the loop...")
        Looper.loop()
        Log.d(TAG, "[${currentThread()}]${this}...after looper.loop.")
    }

    class MyHandler: Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            Log.d(TAG, "[${currentThread()}]${this}...handle message: ${msg.data.getString("message")}")
            if (msg.data.getString("message") == "quit") {
                Looper.myLooper()?.quitSafely()
            }
        }
    }
}
