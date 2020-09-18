package com.example.drinkdrink;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.drinkdrink.DataBase.DataBase;

import java.util.Calendar;

public class Alarms extends Application {

    public static final String TAG = "MyAPP";

    Context context;
    DataBase dataBase;
    AlarmManager alarmManager;
    final int INTERVAL = 86400000;

    public Alarms (Context context){
        this.context = context;
        dataBase = new DataBase(context);
    }

    public void setAlarms() {
        int wake_up = dataBase.getWakeUpTime_hours();
        if (wake_up == 0) {
            wake_up = 24;
        }

        int sleep = dataBase.getSleepTime_hours();
        if (sleep == 0) {
            sleep = 24;
        }

        int superTime = sleep - wake_up;

        if (superTime >= 4 && superTime < 6) {

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis());
            int alarm1 = dataBase.getWakeUpTime_hours() + 2;
            cal.set(Calendar.HOUR_OF_DAY, alarm1);

            Intent notificationIntent = new Intent(context, AlarmReceiver.class);
            PendingIntent broadcast = PendingIntent.getBroadcast(context, 102, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), INTERVAL, broadcast);

            Log.d(TAG, "setAlarms: 1");

        }
        if (superTime >= 6 && superTime < 8) {

            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(System.currentTimeMillis());
            int alarm1 = dataBase.getWakeUpTime_hours() + 2;
            cal1.set(Calendar.HOUR_OF_DAY, alarm1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTimeInMillis(System.currentTimeMillis());
            int alarm2 = dataBase.getWakeUpTime_hours() + 4;
            cal2.set(Calendar.HOUR_OF_DAY, alarm2);

            Intent notificationIntent = new Intent(context, AlarmReceiver.class);
            PendingIntent broadcast1 = PendingIntent.getBroadcast(context, 102, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast2 = PendingIntent.getBroadcast(context, 103, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), INTERVAL, broadcast1);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal2.getTimeInMillis(), INTERVAL, broadcast2);

            Log.d(TAG, "setAlarms: 2");
        }
        if (superTime >= 8 && superTime < 10) {

            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(System.currentTimeMillis());
            int alarm1 = dataBase.getWakeUpTime_hours() + 2;
            cal1.set(Calendar.HOUR_OF_DAY, alarm1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTimeInMillis(System.currentTimeMillis());
            int alarm2 = dataBase.getWakeUpTime_hours() + 4;
            cal2.set(Calendar.HOUR_OF_DAY, alarm2);

            Calendar cal3 = Calendar.getInstance();
            cal3.setTimeInMillis(System.currentTimeMillis());
            int alarm3 = dataBase.getWakeUpTime_hours() + 6;
            cal3.set(Calendar.HOUR_OF_DAY, alarm3);

            Intent notificationIntent = new Intent(context, AlarmReceiver.class);
            PendingIntent broadcast1 = PendingIntent.getBroadcast(context, 102, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast2 = PendingIntent.getBroadcast(context, 103, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast3 = PendingIntent.getBroadcast(context, 104, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), INTERVAL, broadcast1);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal2.getTimeInMillis(), INTERVAL, broadcast2);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal3.getTimeInMillis(), INTERVAL, broadcast3);

            Log.d(TAG, "setAlarms: 3");
        }
        if (superTime >= 10 && superTime < 12) {

            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(System.currentTimeMillis());
            int alarm1 = dataBase.getWakeUpTime_hours() + 2;
            cal1.set(Calendar.HOUR_OF_DAY, alarm1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTimeInMillis(System.currentTimeMillis());
            int alarm2 = dataBase.getWakeUpTime_hours() + 4;
            cal2.set(Calendar.HOUR_OF_DAY, alarm2);

            Calendar cal3 = Calendar.getInstance();
            cal3.setTimeInMillis(System.currentTimeMillis());
            int alarm3 = dataBase.getWakeUpTime_hours() + 6;
            cal3.set(Calendar.HOUR_OF_DAY, alarm3);

            Calendar cal4 = Calendar.getInstance();
            cal4.setTimeInMillis(System.currentTimeMillis());
            int alarm4 = dataBase.getWakeUpTime_hours() + 8;
            cal4.set(Calendar.HOUR_OF_DAY, alarm4);

            Intent notificationIntent = new Intent(context, AlarmReceiver.class);
            PendingIntent broadcast1 = PendingIntent.getBroadcast(context, 102, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast2 = PendingIntent.getBroadcast(context, 103, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast3 = PendingIntent.getBroadcast(context, 104, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast4 = PendingIntent.getBroadcast(context, 105, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), INTERVAL, broadcast1);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal2.getTimeInMillis(), INTERVAL, broadcast2);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal3.getTimeInMillis(), INTERVAL, broadcast3);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal4.getTimeInMillis(), INTERVAL, broadcast4);

            Log.d(TAG, "setAlarms: 4");
        }
        if (superTime >= 12 && superTime < 14) {

            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(System.currentTimeMillis());
            int alarm1 = dataBase.getWakeUpTime_hours() + 2;
            cal1.set(Calendar.HOUR_OF_DAY, alarm1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTimeInMillis(System.currentTimeMillis());
            int alarm2 = dataBase.getWakeUpTime_hours() + 4;
            cal2.set(Calendar.HOUR_OF_DAY, alarm2);

            Calendar cal3 = Calendar.getInstance();
            cal3.setTimeInMillis(System.currentTimeMillis());
            int alarm3 = dataBase.getWakeUpTime_hours() + 6;
            cal3.set(Calendar.HOUR_OF_DAY, alarm3);

            Calendar cal4 = Calendar.getInstance();
            cal4.setTimeInMillis(System.currentTimeMillis());
            int alarm4 = dataBase.getWakeUpTime_hours() + 8;
            cal4.set(Calendar.HOUR_OF_DAY, alarm4);

            Calendar cal5 = Calendar.getInstance();
            cal5.setTimeInMillis(System.currentTimeMillis());
            int alarm5 = dataBase.getWakeUpTime_hours() + 10;
            cal5.set(Calendar.HOUR_OF_DAY, alarm5);

            Intent notificationIntent = new Intent(context, AlarmReceiver.class);
            PendingIntent broadcast1 = PendingIntent.getBroadcast(context, 102, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast2 = PendingIntent.getBroadcast(context, 103, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast3 = PendingIntent.getBroadcast(context, 104, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast4 = PendingIntent.getBroadcast(context, 105, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast5 = PendingIntent.getBroadcast(context, 106, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), INTERVAL, broadcast1);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal2.getTimeInMillis(), INTERVAL, broadcast2);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal3.getTimeInMillis(), INTERVAL, broadcast3);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal4.getTimeInMillis(), INTERVAL, broadcast4);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal5.getTimeInMillis(), INTERVAL, broadcast5);

            Log.d(TAG, "setAlarms: 5");
        }
        if (superTime >= 14 && superTime < 16) {

            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(System.currentTimeMillis());
            int alarm1 = dataBase.getWakeUpTime_hours() + 2;
            cal1.set(Calendar.HOUR_OF_DAY, alarm1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTimeInMillis(System.currentTimeMillis());
            int alarm2 = dataBase.getWakeUpTime_hours() + 4;
            cal2.set(Calendar.HOUR_OF_DAY, alarm2);

            Calendar cal3 = Calendar.getInstance();
            cal3.setTimeInMillis(System.currentTimeMillis());
            int alarm3 = dataBase.getWakeUpTime_hours() + 6;
            cal3.set(Calendar.HOUR_OF_DAY, alarm3);

            Calendar cal4 = Calendar.getInstance();
            cal4.setTimeInMillis(System.currentTimeMillis());
            int alarm4 = dataBase.getWakeUpTime_hours() + 8;
            cal4.set(Calendar.HOUR_OF_DAY, alarm4);

            Calendar cal5 = Calendar.getInstance();
            cal5.setTimeInMillis(System.currentTimeMillis());
            int alarm5 = dataBase.getWakeUpTime_hours() + 10;
            cal5.set(Calendar.HOUR_OF_DAY, alarm5);

            Calendar cal6 = Calendar.getInstance();
            cal6.setTimeInMillis(System.currentTimeMillis());
            int alarm6 = dataBase.getWakeUpTime_hours() + 12;
            cal6.set(Calendar.HOUR_OF_DAY, alarm6);

            Intent notificationIntent = new Intent(context, AlarmReceiver.class);
            PendingIntent broadcast1 = PendingIntent.getBroadcast(context, 102, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast2 = PendingIntent.getBroadcast(context, 103, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast3 = PendingIntent.getBroadcast(context, 104, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast4 = PendingIntent.getBroadcast(context, 105, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast5 = PendingIntent.getBroadcast(context, 106, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast6 = PendingIntent.getBroadcast(context, 107, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), INTERVAL, broadcast1);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal2.getTimeInMillis(), INTERVAL, broadcast2);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal3.getTimeInMillis(), INTERVAL, broadcast3);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal4.getTimeInMillis(), INTERVAL, broadcast4);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal5.getTimeInMillis(), INTERVAL, broadcast5);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal6.getTimeInMillis(), INTERVAL, broadcast6);

            Log.d(TAG, "setAlarms: 6");
        }
        if (superTime >= 16 && superTime < 18) {

            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(System.currentTimeMillis());
            int alarm1 = dataBase.getWakeUpTime_hours() + 2;
            cal1.set(Calendar.HOUR_OF_DAY, alarm1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTimeInMillis(System.currentTimeMillis());
            int alarm2 = dataBase.getWakeUpTime_hours() + 4;
            cal2.set(Calendar.HOUR_OF_DAY, alarm2);

            Calendar cal3 = Calendar.getInstance();
            cal3.setTimeInMillis(System.currentTimeMillis());
            int alarm3 = dataBase.getWakeUpTime_hours() + 6;
            cal3.set(Calendar.HOUR_OF_DAY, alarm3);

            Calendar cal4 = Calendar.getInstance();
            cal4.setTimeInMillis(System.currentTimeMillis());
            int alarm4 = dataBase.getWakeUpTime_hours() + 8;
            cal4.set(Calendar.HOUR_OF_DAY, alarm4);

            Calendar cal5 = Calendar.getInstance();
            cal5.setTimeInMillis(System.currentTimeMillis());
            int alarm5 = dataBase.getWakeUpTime_hours() + 10;
            cal5.set(Calendar.HOUR_OF_DAY, alarm5);

            Calendar cal6 = Calendar.getInstance();
            cal6.setTimeInMillis(System.currentTimeMillis());
            int alarm6 = dataBase.getWakeUpTime_hours() + 12;
            cal6.set(Calendar.HOUR_OF_DAY, alarm6);

            Calendar cal7 = Calendar.getInstance();
            cal7.setTimeInMillis(System.currentTimeMillis());
            int alarm7 = dataBase.getWakeUpTime_hours() + 14;
            cal7.set(Calendar.HOUR_OF_DAY, alarm7);

            Intent notificationIntent = new Intent(context, AlarmReceiver.class);
            PendingIntent broadcast1 = PendingIntent.getBroadcast(context, 102, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast2 = PendingIntent.getBroadcast(context, 103, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast3 = PendingIntent.getBroadcast(context, 104, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast4 = PendingIntent.getBroadcast(context, 105, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast5 = PendingIntent.getBroadcast(context, 106, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast6 = PendingIntent.getBroadcast(context, 107, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent broadcast7 = PendingIntent.getBroadcast(context, 108, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), INTERVAL, broadcast1);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal2.getTimeInMillis(), INTERVAL, broadcast2);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal3.getTimeInMillis(), INTERVAL, broadcast3);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal4.getTimeInMillis(), INTERVAL, broadcast4);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal5.getTimeInMillis(), INTERVAL, broadcast5);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal6.getTimeInMillis(), INTERVAL, broadcast6);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal7.getTimeInMillis(), INTERVAL, broadcast7);

            Log.d(TAG, "setAlarms: 7");
        }
    }
}
