package com.example.androidappdemo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.androidappdemo.base.DefaultComponentBaseActivity

class NotificationDemoActivity: DefaultComponentBaseActivity() {
    val channel_name = "mynotificationchannel"
    val channel_desc = "this is my local notification channel"
    val channel_id = "mynotificationchannel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_demo)
        createNotificationChannel()
        findViewById<Button>(R.id.btnSend)?.setOnClickListener {
            val notification = Notification.Builder(this, channel_id)
                .setContentTitle("Notif Title")
                .setContentText("this is notification")
                .setSmallIcon(R.drawable.moon)
                .build()
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)
            notification.contentIntent = pendingIntent
            val mgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mgr.notify(1, notification)
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channel_id, channel_name, importance).apply {
            description = channel_desc
        }
        // Register the channel with the system.
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}