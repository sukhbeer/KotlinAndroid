package com.example.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val PRIMARY_CHANNEL_ID: String = "primary_notification_channel"
    private var notificationManager : NotificationManager? = null
    private val NOTIFICATION_ID: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notify.setOnClickListener {
            sendNotification()
        }
    }

    private fun sendNotification() {
      val notifyBuilder : NotificationCompat.Builder = getNotification()
        notificationManager?.notify(NOTIFICATION_ID,notifyBuilder.build())
    }

    private fun createNotificationChannel(){
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager



        if(android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) run {
            //Create NotificationChannel
            val channel =
                NotificationChannel(PRIMARY_CHANNEL_ID, "Mascot Notification", NotificationManager.IMPORTANCE_HIGH)

            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.description = "Notification from Mascot"
            notificationManager?.createNotificationChannel(channel)

        }
    }

    private fun getNotification(): NotificationCompat.Builder {

        val intent = Intent(this,MainActivity::class.java)
        val notificationPendingIntent = PendingIntent.getActivity(this,NOTIFICATION_ID,
            intent,PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this@MainActivity, NOTIFICATION_ID.toString())
            .setContentTitle("You've been notified")
            .setContentText("This is you notification text.")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true)
            .setContentIntent(notificationPendingIntent)
        return builder
    }
}
