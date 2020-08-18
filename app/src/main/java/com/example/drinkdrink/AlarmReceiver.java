package com.example.drinkdrink;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.drinkdrink.Tabs.TabActivity;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "com.singhajit.notificationDemo.channelId";

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent notificationIntent = new Intent(context, TabActivity.class); // Создаем интент, при нажатии на нотификацию, запускаем DrinkActivity

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(TabActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Создаем уведомление и описываем его
        Notification.Builder builder = new Notification.Builder(context);

        Notification notification = builder.setContentTitle("Пора пить")
                .setContentText("Не пропусти очередной прием водички..")
                .setTicker("ПИТЬ ПИТЬ!")
                .setShowWhen(true)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent).build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID);
        }

        // Показываем уведомление
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "NotificationDemo",
                    IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notification);
    }
}