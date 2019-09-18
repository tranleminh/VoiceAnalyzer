package com.functionality.voiceanalyzer;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class NotificationService extends Service {

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    ParametersCollection.CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent mIntent = new Intent(getApplicationContext(), NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        /** DECOMMENT THIS PART TO NOTIFY EVERY 15 MIN SINCE LAUNCH
         Calendar calendar = Calendar.getInstance();
         alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);
         **/

        /**THIS PART IS TO NOTIFY EVERYDAY AT 7 PM - defined in DEFAULT_NOTIFY_TIME**/

//        Date notifyDate = null;
//        try {
//            notifyDate = ParametersCollection.dayTime.parse(ParametersCollection.DEFAULT_NOTIFY_TIME);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        /**Start with instantiate a Calendar set to 7:00 PM**/
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 19);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        /**If service called after 19h then a day is added in order to make sure about time**/
        if (Calendar.getInstance().after(cal)) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        /**Then set a repeating alarm manager**/
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        /***Create notification channel here, for Foreground Service purpose***/
        String input = intent.getStringExtra("inputExtra");
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, ParametersCollection.CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText(input)
                .setContentIntent(pendingIntent2)
                .build();

        /***Foreground service started here, with created notification channel***/
        startForeground(1, notification);

        return START_STICKY;
    }

    /**
     * Overridden method that tell the Service to start AppCloseReceiver when destroyed
     */
    @Override
    public void onDestroy() {
        Log.d("service", "destroy");
        super.onDestroy();

        /*****Create intent for AppCloseReceiver then send it*****/
        Intent broadcastIntent = new Intent(this, AppCloseReceiver.class);
        sendBroadcast(broadcastIntent);
    }
}
