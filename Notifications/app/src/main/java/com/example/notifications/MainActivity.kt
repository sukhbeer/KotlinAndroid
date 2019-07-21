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
    private val PRIMARY_CHANNEL_ID: String = "primary_notification_channel"
    private var notificationManager: NotificationManager? = null
    private val NOTIFICATION_ID: Int = 0

    val mReciver = NotificationReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        registerReceiver(mReciver, IntentFilter(updateNOTIFICATION))

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

        setNotificationButtonState(true, false, false)

    }

    //Complete
    override fun onDestroy() {
        unregisterReceiver(mReciver)
        super.onDestroy()
    }

    //Complete
    private fun sendNotification() {
        val updateIntent = Intent(updateNOTIFICATION)
        val updatePendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID,
            updateIntent, PendingIntent.FLAG_ONE_SHOT)


        val notifyBuilder: NotificationCompat.Builder = getNotification()

        notifyBuilder.addAction(R.drawable.ic_update,"Update",updatePendingIntent)

        notificationManager?.notify(NOTIFICATION_ID, notifyBuilder.build())

        setNotificationButtonState(isNotifyEnabled = false, isUpdateEnabled = true, isCancelEnabled = true)

    }

    //Code complete
    private fun createNotificationChannel() {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >=
            android.os.Build.VERSION_CODES.O
        ){
            //Create NotificationChannel
            val channel =
                NotificationChannel(PRIMARY_CHANNEL_ID, 
                    "Mascot Notification",
                    NotificationManager.IMPORTANCE_HIGH)

            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.description = "Notification from Mascot"
            notificationManager?.createNotificationChannel(channel)

        }
    }

    //Complete
    private fun getNotification(): NotificationCompat.Builder {

        val intent = Intent(this, MainActivity::class.java)

        val notificationPendingIntent = PendingIntent.getActivity(
            this, NOTIFICATION_ID,
            intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        return NotificationCompat.Builder(this@MainActivity,
            NOTIFICATION_ID.toString())
            .setContentTitle("You've been notified")
            .setContentText("This is you notification text.")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentIntent(notificationPendingIntent)
    }

    //Code Complete
    fun updateNotification() {
        val androidImage = BitmapFactory.decodeResource(resources,
            R.drawable.mascot_1)

        val notifyBuilder: NotificationCompat.Builder = getNotification()

        notifyBuilder.setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(androidImage)
                .setBigContentTitle("Notification Update")
        )

        notificationManager?.notify(NOTIFICATION_ID, notifyBuilder.build())

        setNotificationButtonState(false, false, true);
    }

    //Complete
    private fun cancelNotification() {
        notificationManager?.cancel(NOTIFICATION_ID)

        setNotificationButtonState(true, false, false)
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
