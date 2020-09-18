package com.example.drinkdrink.FragmnetsLogin;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.drinkdrink.AlarmReceiver;
import com.example.drinkdrink.Alarms;
import com.example.drinkdrink.DataBase.DataBase;
import com.example.drinkdrink.R;
import com.example.drinkdrink.Tabs.TabActivity;

import java.util.Calendar;

public class FragmentStartManager extends AppCompatActivity {

    FragmentHello fragHello = new FragmentHello();
    FragmentGender fragGender = new FragmentGender();
    FragmentWeight fragWeight = new FragmentWeight();
    FragmentHeight fragHeight = new FragmentHeight();
    FragmentWakeUp fragWakeUp = new FragmentWakeUp();
    FragmentSleep fragSleep = new FragmentSleep();

    FragmentTransaction fTrans;
    AlarmManager alarmManager;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6;

    Alarms alarms;

    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_start);

        dataBase = new DataBase(getApplicationContext());

        alarms = new Alarms(getApplicationContext());

        imageView1 = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView5);
        imageView3 = findViewById(R.id.imageView9);
        imageView4 = findViewById(R.id.imageView13);
        imageView5 = findViewById(R.id.imageView17);
        imageView6 = findViewById(R.id.imageView21);

        //Устанавливаем фрагмент 1
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.frgmCont, fragHello);
        fTrans.commit();

    }

    //Верхняя строка прогресса по фрагментам для первого запуска
    public void onNavigationItemSelected(int item) {
        if (item == 2) {
            //Смена иконнки прогресса
            imageView1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.kubhellowithout, null));
            imageView2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.kubgenderwith, null));

            //Устанавливаем фрагмент 2
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
            fTrans.replace(R.id.frgmCont, fragGender);
            fTrans.commit();

        }
        if (item == 3) {
            //Смена иконнки прогресса
            imageView2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.kubgenderwithout, null));
            imageView3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.kubweightwith, null));

            //Устанавливаем фрагмент 3
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
            fTrans.replace(R.id.frgmCont, fragWeight);
            fTrans.commit();
        }
        if (item == 4) {
            //Смена иконнки прогресса
            imageView3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.kubweightwithout, null));
            imageView4.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.kubheightwith, null));

            //Устанавливаем фрагмент 4
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
            fTrans.replace(R.id.frgmCont, fragHeight);
            fTrans.commit();
        }
        if (item == 5) {
            //Смена иконнки прогресса
            imageView4.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.kubheightwithout, null));
            imageView5.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.kubwakewith, null));

            //Устанавливаем фрагмент 5
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
            fTrans.replace(R.id.frgmCont, fragWakeUp);
            fTrans.commit();
        }
        if (item == 6) {
            //Смена иконнки прогресса
            imageView5.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.kubwakewithout, null));
            imageView6.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.kubsleepwith, null));

            //Устанавливаем фрагмент 6
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
            fTrans.replace(R.id.frgmCont, fragSleep);
            fTrans.commit();
        }
        if (item == 7) {

            dataBase.setIsInit(false);
            dataBase.setCurrentDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

            alarms.setAlarms();

            Intent intent = new Intent(FragmentStartManager.this, TabActivity.class);
            startActivity(intent);
        }
    }

    //Запрещаем возврат назад
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
            dataBase.setGender(0.04f);
        }
        if (gender == 2) {
            dataBase.setGender(0.03f);
        }
    }

    // Метод для сохранения веса
    public void setWeight(int weight) {
        dataBase.setWeight(weight);
    }

    // Метод для сохранения роста
    public void setHeight(int height) {
        dataBase.setHeight(height);
    }

    // Метод для сохранения времени пробуждения
    public void setWakeUp(int hours, int minutes) {
        dataBase.setWakeUpTime(hours, minutes);
    }

    // Метод для сохранения времени сна
    public void setSleep(int hours, int minutes) {
        dataBase.setSleepTime(hours, minutes);
    }

    public void timerMorning(int hourOfDay, int minute) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);

        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

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
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 101, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        int interval = 86400000;

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), interval, broadcast);
    }
}