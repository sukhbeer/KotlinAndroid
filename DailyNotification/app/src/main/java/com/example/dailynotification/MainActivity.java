package com.example.dailynotification;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        findViewById(R.id.btnSetNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();

                c.set(Calendar.HOUR_OF_DAY,23);
                c.set(Calendar.MINUTE,49);
                c.set(Calendar.SECOND,10);

                Intent i = new Intent(getApplicationContext(),NotificationReceiver.class);

               PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(),100,
                        i,PendingIntent.FLAG_UPDATE_CURRENT);

                 AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY,pi);
            }
        });
    }

    public void createNotificationChannel() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(
                    NotificationReceiver.NOTIFICATION_ID,"Notification",
                    NotificationManager.IMPORTANCE_HIGH
            );

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification Description");

            notificationManager.createNotificationChannel(notificationChannel);
        }

    }
}
