package com.example.drinkdrink.FragmnetsLogin;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.drinkdrink.AlarmReceiver;
import com.example.drinkdrink.R;
import com.example.drinkdrink.Tabs.TabActivity;

import java.util.Calendar;

public class FragmentStartManager extends AppCompatActivity {

    public static final String DataBase = "DataBase";
    public static final String weightKey = "weightKey";
    public static final String heightKey = "heightKey";

    public static final String currentDayOfMonthKey = "currentDayOfMonthKey";

    public static final String genderKey = "genderKey";
    public static final String waterKey = "waterKey";
    public static final String sleepHoursKey = "sleepHoursKey";
    public static final String sleepMinutesKey = "sleepMinutesKey";
    public static final String wakeUpHoursKey = "wakeUpHoursKey";
    public static final String wakeUpMinutesKey = "wakeUpMinutesKey";
    public static final String isInitKey = "isInitKey";

    FragmentHello fragHello = new FragmentHello();
    FragmentGender fragGender = new FragmentGender();
    FragmentWeight fragWeight = new FragmentWeight();
    FragmentHeight fragHeight = new FragmentHeight();
    FragmentWakeUp fragWakeUp = new FragmentWakeUp();
    FragmentSleep fragSleep = new FragmentSleep();

    FragmentTransaction fTrans;
    AlarmManager alarmManager;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6;
    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_start);

        imageView1 = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView5);
        imageView3 = findViewById(R.id.imageView9);
        imageView4 = findViewById(R.id.imageView13);
        imageView5 = findViewById(R.id.imageView17);
        imageView6 = findViewById(R.id.imageView21);

        imageView1.setImageDrawable(this.getDrawable(R.drawable.ic_kubwith));

        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.frgmCont, fragHello);
        fTrans.commit();

        sharedPrefs = getSharedPreferences(DataBase, Context.MODE_PRIVATE);
    }

    public void onNavigationItemSelected(int item) {
        if (item == 2) {
            imageView1.setImageDrawable(this.getDrawable(R.drawable.ic_kubwithout));
            imageView2.setImageDrawable(this.getDrawable(R.drawable.ic_kubwith));
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
            fTrans.replace(R.id.frgmCont, fragGender);
            fTrans.commit();
        }
        if (item == 3) {
            imageView2.setImageDrawable(this.getDrawable(R.drawable.ic_kubwithout));
            imageView3.setImageDrawable(this.getDrawable(R.drawable.kubweightwith));
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
            fTrans.replace(R.id.frgmCont, fragWeight);
            fTrans.commit();
        }
        if (item == 4) {
            imageView3.setImageDrawable(this.getDrawable(R.drawable.kubweightwithout));
            imageView4.setImageDrawable(this.getDrawable(R.drawable.ic_kubwith));
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
            fTrans.replace(R.id.frgmCont, fragHeight);
            fTrans.commit();
        }
        if (item == 5) {
            imageView4.setImageDrawable(this.getDrawable(R.drawable.ic_kubwithout));
            imageView5.setImageDrawable(this.getDrawable(R.drawable.kubwakewith));
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
            fTrans.replace(R.id.frgmCont, fragWakeUp);
            fTrans.commit();
        }
        if (item == 6) {
            imageView5.setImageDrawable(this.getDrawable(R.drawable.kubwakewithout));
            imageView6.setImageDrawable(this.getDrawable(R.drawable.kubsleepwith));
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
            fTrans.replace(R.id.frgmCont, fragSleep);
            fTrans.commit();
        }
        if (item == 7) {
            SharedPreferences.Editor editor = sharedPrefs.edit();

            int currentDayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH); //Сохраняем в переменную текущую дату

            float waterNorm = ((float) (sharedPrefs.getInt(weightKey, 40))) * (sharedPrefs.getFloat(genderKey, 0)); // Вес умножаем на 0.03 или 0.05 и получаем воду

            editor.putFloat(waterKey, waterNorm);
            editor.putInt(currentDayOfMonthKey, currentDayOfMonth);
            editor.putBoolean(isInitKey, false);
            editor.apply();

            someAlarms();

            Intent intent = new Intent(FragmentStartManager.this, TabActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
    }

    // Прячем клавиатуру по нажатию в любое место кроме EditText
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    // Метод для сохранения гендера
    public void setGender(int gender) {

        if (gender == 1) {
            SharedPreferences.Editor editor = sharedPrefs.edit();
            float a = ((float) 0.04);
            editor.putFloat(genderKey, a);
            editor.apply();
        }
        if (gender == 2) {
            SharedPreferences.Editor editor = sharedPrefs.edit();
            float b = (float) 0.03;
            editor.putFloat(genderKey, b);
            editor.apply();
        }
    }

    // Метод для сохранения веса
    public void setWeight(int weight) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(weightKey, weight);
        editor.apply();
    }

    // Метод для сохранения роста
    public void setHeight(int height) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(heightKey, height);
        editor.apply();
    }

    // Метод для сохранения времени сна
    public void setSleep(int hourOfDay, int minute) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(sleepHoursKey, hourOfDay);
        editor.putInt(sleepMinutesKey, minute);
        editor.apply();
    }

    // Метод для сохранения времени пробуждения
    public void setWakeUp(int hourOfDay, int minute) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(wakeUpHoursKey, hourOfDay);
        editor.putInt(wakeUpMinutesKey, minute);
        editor.apply();
    }

    public void timerMorning(int hourOfDay, int minute) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);

        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, 0);

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        int interval = 86400000;

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), interval, broadcast);
    }

    public void timerEvening(int hourOfDay, int minute) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        int hourOfDayNorm = hourOfDay - 1;
        cal.set(Calendar.HOUR_OF_DAY, hourOfDayNorm);
        cal.set(Calendar.MINUTE, minute);

        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 101, notificationIntent, 0);

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        int interval = 86400000;

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), interval, broadcast);
    }

    public void someAlarms() {
        int wake_up = sharedPrefs.getInt(wakeUpHoursKey, 0);
        if (wake_up == 0) {
            wake_up = 24;
        }

        int sleep = sharedPrefs.getInt(sleepHoursKey, 0);
        if (sleep == 0) {
            sleep = 24;
        }

        int superTime = sleep - wake_up;

        if (superTime >= 4 && superTime < 6) {

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis());
            int alarm1 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 2;
            cal.set(Calendar.HOUR_OF_DAY, alarm1);

            Intent notificationIntent = new Intent(this, AlarmReceiver.class);
            PendingIntent broadcast = PendingIntent.getBroadcast(this, 102, notificationIntent, 0);

            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            int interval = 86400000;

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), interval, broadcast);

        }
        if (superTime >= 6 && superTime < 8) {

            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(System.currentTimeMillis());
            int alarm1 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 2;
            cal1.set(Calendar.HOUR_OF_DAY, alarm1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTimeInMillis(System.currentTimeMillis());
            int alarm2 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 4;
            cal2.set(Calendar.HOUR_OF_DAY, alarm2);

            Intent notificationIntent = new Intent(this, AlarmReceiver.class);
            PendingIntent broadcast1 = PendingIntent.getBroadcast(this, 102, notificationIntent, 0);
            PendingIntent broadcast2 = PendingIntent.getBroadcast(this, 103, notificationIntent, 0);

            int interval = 86400000;
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), interval, broadcast1);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal2.getTimeInMillis(), interval, broadcast2);

        }
        if (superTime >= 8 && superTime < 10) {

            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(System.currentTimeMillis());
            int alarm1 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 2;
            cal1.set(Calendar.HOUR_OF_DAY, alarm1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTimeInMillis(System.currentTimeMillis());
            int alarm2 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 4;
            cal2.set(Calendar.HOUR_OF_DAY, alarm2);

            Calendar cal3 = Calendar.getInstance();
            cal3.setTimeInMillis(System.currentTimeMillis());
            int alarm3 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 6;
            cal3.set(Calendar.HOUR_OF_DAY, alarm3);

            Intent notificationIntent = new Intent(this, AlarmReceiver.class);
            PendingIntent broadcast1 = PendingIntent.getBroadcast(this, 102, notificationIntent, 0);
            PendingIntent broadcast2 = PendingIntent.getBroadcast(this, 103, notificationIntent, 0);
            PendingIntent broadcast3 = PendingIntent.getBroadcast(this, 104, notificationIntent, 0);

            int interval = 86400000;
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), interval, broadcast1);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal2.getTimeInMillis(), interval, broadcast2);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal3.getTimeInMillis(), interval, broadcast3);

        }
        if (superTime >= 10 && superTime < 12) {

            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(System.currentTimeMillis());
            int alarm1 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 2;
            cal1.set(Calendar.HOUR_OF_DAY, alarm1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTimeInMillis(System.currentTimeMillis());
            int alarm2 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 4;
            cal2.set(Calendar.HOUR_OF_DAY, alarm2);

            Calendar cal3 = Calendar.getInstance();
            cal3.setTimeInMillis(System.currentTimeMillis());
            int alarm3 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 6;
            cal3.set(Calendar.HOUR_OF_DAY, alarm3);

            Calendar cal4 = Calendar.getInstance();
            cal4.setTimeInMillis(System.currentTimeMillis());
            int alarm4 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 8;
            cal4.set(Calendar.HOUR_OF_DAY, alarm4);

            Intent notificationIntent = new Intent(this, AlarmReceiver.class);
            PendingIntent broadcast1 = PendingIntent.getBroadcast(this, 102, notificationIntent, 0);
            PendingIntent broadcast2 = PendingIntent.getBroadcast(this, 103, notificationIntent, 0);
            PendingIntent broadcast3 = PendingIntent.getBroadcast(this, 104, notificationIntent, 0);
            PendingIntent broadcast4 = PendingIntent.getBroadcast(this, 105, notificationIntent, 0);

            int interval = 86400000;
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), interval, broadcast1);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal2.getTimeInMillis(), interval, broadcast2);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal3.getTimeInMillis(), interval, broadcast3);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal4.getTimeInMillis(), interval, broadcast4);

        }
        if (superTime >= 12 && superTime < 14) {

            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(System.currentTimeMillis());
            int alarm1 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 2;
            cal1.set(Calendar.HOUR_OF_DAY, alarm1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTimeInMillis(System.currentTimeMillis());
            int alarm2 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 4;
            cal2.set(Calendar.HOUR_OF_DAY, alarm2);

            Calendar cal3 = Calendar.getInstance();
            cal3.setTimeInMillis(System.currentTimeMillis());
            int alarm3 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 6;
            cal3.set(Calendar.HOUR_OF_DAY, alarm3);

            Calendar cal4 = Calendar.getInstance();
            cal4.setTimeInMillis(System.currentTimeMillis());
            int alarm4 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 8;
            cal4.set(Calendar.HOUR_OF_DAY, alarm4);

            Calendar cal5 = Calendar.getInstance();
            cal5.setTimeInMillis(System.currentTimeMillis());
            int alarm5 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 10;
            cal5.set(Calendar.HOUR_OF_DAY, alarm5);

            Intent notificationIntent = new Intent(this, AlarmReceiver.class);
            PendingIntent broadcast1 = PendingIntent.getBroadcast(this, 102, notificationIntent, 0);
            PendingIntent broadcast2 = PendingIntent.getBroadcast(this, 103, notificationIntent, 0);
            PendingIntent broadcast3 = PendingIntent.getBroadcast(this, 104, notificationIntent, 0);
            PendingIntent broadcast4 = PendingIntent.getBroadcast(this, 105, notificationIntent, 0);
            PendingIntent broadcast5 = PendingIntent.getBroadcast(this, 106, notificationIntent, 0);

            int interval = 86400000;
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), interval, broadcast1);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal2.getTimeInMillis(), interval, broadcast2);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal3.getTimeInMillis(), interval, broadcast3);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal4.getTimeInMillis(), interval, broadcast4);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal5.getTimeInMillis(), interval, broadcast5);

        }
        if (superTime >= 14 && superTime < 16) {

            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(System.currentTimeMillis());
            int alarm1 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 2;
            cal1.set(Calendar.HOUR_OF_DAY, alarm1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTimeInMillis(System.currentTimeMillis());
            int alarm2 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 4;
            cal2.set(Calendar.HOUR_OF_DAY, alarm2);

            Calendar cal3 = Calendar.getInstance();
            cal3.setTimeInMillis(System.currentTimeMillis());
            int alarm3 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 6;
            cal3.set(Calendar.HOUR_OF_DAY, alarm3);

            Calendar cal4 = Calendar.getInstance();
            cal4.setTimeInMillis(System.currentTimeMillis());
            int alarm4 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 8;
            cal4.set(Calendar.HOUR_OF_DAY, alarm4);

            Calendar cal5 = Calendar.getInstance();
            cal5.setTimeInMillis(System.currentTimeMillis());
            int alarm5 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 10;
            cal5.set(Calendar.HOUR_OF_DAY, alarm5);

            Calendar cal6 = Calendar.getInstance();
            cal6.setTimeInMillis(System.currentTimeMillis());
            int alarm6 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 12;
            cal6.set(Calendar.HOUR_OF_DAY, alarm6);

            Intent notificationIntent = new Intent(this, AlarmReceiver.class);
            PendingIntent broadcast1 = PendingIntent.getBroadcast(this, 102, notificationIntent, 0);
            PendingIntent broadcast2 = PendingIntent.getBroadcast(this, 103, notificationIntent, 0);
            PendingIntent broadcast3 = PendingIntent.getBroadcast(this, 104, notificationIntent, 0);
            PendingIntent broadcast4 = PendingIntent.getBroadcast(this, 105, notificationIntent, 0);
            PendingIntent broadcast5 = PendingIntent.getBroadcast(this, 106, notificationIntent, 0);
            PendingIntent broadcast6 = PendingIntent.getBroadcast(this, 107, notificationIntent, 0);

            int interval = 86400000;
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), interval, broadcast1);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal2.getTimeInMillis(), interval, broadcast2);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal3.getTimeInMillis(), interval, broadcast3);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal4.getTimeInMillis(), interval, broadcast4);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal5.getTimeInMillis(), interval, broadcast5);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal6.getTimeInMillis(), interval, broadcast6);
        }
        if (superTime >= 16 && superTime < 18) {

            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(System.currentTimeMillis());
            int alarm1 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 2;
            cal1.set(Calendar.HOUR_OF_DAY, alarm1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTimeInMillis(System.currentTimeMillis());
            int alarm2 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 4;
            cal2.set(Calendar.HOUR_OF_DAY, alarm2);

            Calendar cal3 = Calendar.getInstance();
            cal3.setTimeInMillis(System.currentTimeMillis());
            int alarm3 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 6;
            cal3.set(Calendar.HOUR_OF_DAY, alarm3);

            Calendar cal4 = Calendar.getInstance();
            cal4.setTimeInMillis(System.currentTimeMillis());
            int alarm4 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 8;
            cal4.set(Calendar.HOUR_OF_DAY, alarm4);

            Calendar cal5 = Calendar.getInstance();
            cal5.setTimeInMillis(System.currentTimeMillis());
            int alarm5 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 10;
            cal5.set(Calendar.HOUR_OF_DAY, alarm5);

            Calendar cal6 = Calendar.getInstance();
            cal6.setTimeInMillis(System.currentTimeMillis());
            int alarm6 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 12;
            cal6.set(Calendar.HOUR_OF_DAY, alarm6);

            Calendar cal7 = Calendar.getInstance();
            cal7.setTimeInMillis(System.currentTimeMillis());
            int alarm7 = sharedPrefs.getInt(wakeUpHoursKey, 0) + 14;
            cal7.set(Calendar.HOUR_OF_DAY, alarm7);

            Intent notificationIntent = new Intent(this, AlarmReceiver.class);
            PendingIntent broadcast1 = PendingIntent.getBroadcast(this, 102, notificationIntent, 0);
            PendingIntent broadcast2 = PendingIntent.getBroadcast(this, 103, notificationIntent, 0);
            PendingIntent broadcast3 = PendingIntent.getBroadcast(this, 104, notificationIntent, 0);
            PendingIntent broadcast4 = PendingIntent.getBroadcast(this, 105, notificationIntent, 0);
            PendingIntent broadcast5 = PendingIntent.getBroadcast(this, 106, notificationIntent, 0);
            PendingIntent broadcast6 = PendingIntent.getBroadcast(this, 107, notificationIntent, 0);
            PendingIntent broadcast7 = PendingIntent.getBroadcast(this, 108, notificationIntent, 0);

            int interval = 86400000;
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), interval, broadcast1);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal2.getTimeInMillis(), interval, broadcast2);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal3.getTimeInMillis(), interval, broadcast3);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal4.getTimeInMillis(), interval, broadcast4);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal5.getTimeInMillis(), interval, broadcast5);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal6.getTimeInMillis(), interval, broadcast6);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal7.getTimeInMillis(), interval, broadcast7);
        }
    }

}