package com.example.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val updateNOTIFICATION: String = "com.example.notifications.updateNOTIFICATION"
    private val primaryChannelId: String = "primary_notification_channel"
    private lateinit var notificationManager: NotificationManager
    private val notificationId: Int = 0

    private val mReceiver = NotificationReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        registerReceiver(mReceiver, IntentFilter(updateNOTIFICATION))

        //notify_button
        notify.setOnClickListener {
            sendNotification()
        }

        //update_button
        update.setOnClickListener {
            updateNotification()
        }

        //cancel_button
        cancel.setOnClickListener {
            cancelNotification()
        }

        setNotificationButtonState(isNotifyEnabled = true, isUpdateEnabled = false, isCancelEnabled = false)

    }

    //Complete
    override fun onDestroy() {
        unregisterReceiver(mReceiver)
        super.onDestroy()
    }

    //Complete
    private fun sendNotification() {
        val updateIntent = Intent(updateNOTIFICATION)
        val updatePendingIntent = PendingIntent.getBroadcast(
            this, notificationId,
            updateIntent, PendingIntent.FLAG_ONE_SHOT
        )


        val notifyBuilder: NotificationCompat.Builder = getNotification()

        notifyBuilder.addAction(R.drawable.ic_update, "Update", updatePendingIntent)

        notificationManager.notify(notificationId, notifyBuilder.build())

        setNotificationButtonState(isNotifyEnabled = false, isUpdateEnabled = true, isCancelEnabled = true)

    }

    //Code complete
    private fun createNotificationChannel() {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >=
            android.os.Build.VERSION_CODES.O
        ) {
            //Create NotificationChannel
            val channel =
                NotificationChannel(
                    primaryChannelId,
                    "Mascot Notification",
                    NotificationManager.IMPORTANCE_HIGH
                )

            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.description = "Notification from Mascot"
            notificationManager.createNotificationChannel(channel)

        }
    }

    //Complete
    private fun getNotification(): NotificationCompat.Builder {

        val intent = Intent(this, MainActivity::class.java)

        val notificationPendingIntent = PendingIntent.getActivity(
            this, notificationId,
            intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        return NotificationCompat
            .Builder(this, primaryChannelId)
            .setContentTitle("Notification Title")
            .setContentText("Notification Description")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true).setContentIntent(notificationPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
    }

    //Code Complete
    fun updateNotification() {
        val androidImage = BitmapFactory.decodeResource(
            resources,
            R.drawable.mascot_1
        )

        val notifyBuilder: NotificationCompat.Builder = getNotification()

        notifyBuilder.setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(androidImage)
                .setBigContentTitle("Notification Update")
        )

        notificationManager.notify(notificationId, notifyBuilder.build())

        setNotificationButtonState(isNotifyEnabled = false, isUpdateEnabled = false, isCancelEnabled = true)
    }

    //Complete
    private fun cancelNotification() {
        notificationManager.cancel(notificationId)

        setNotificationButtonState(isNotifyEnabled = true, isUpdateEnabled = false, isCancelEnabled = false)
    }

    //Complete
    private fun setNotificationButtonState(
        isNotifyEnabled: Boolean,
        isUpdateEnabled: Boolean,
        isCancelEnabled: Boolean
    ) {
        notify.isEnabled = isNotifyEnabled
        cancel.isEnabled = isCancelEnabled
        update.isEnabled = isUpdateEnabled
    }

    //Code Complete
    class NotificationReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val main = MainActivity()
            main.updateNotification()
        }
    }
}
