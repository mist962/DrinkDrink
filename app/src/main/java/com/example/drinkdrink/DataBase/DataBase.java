package com.example.drinkdrink.DataBase;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;

public class DataBase {

    Context context;

    public static final String DataBase = "DataBase";

    public static final String isInitKey = "isInitKey";
    public static final String currentDayKey = "currentDayKey";

    public static final String genderKey = "genderKey";
    public static final String weightKey = "weightKey";
    public static final String heightKey = "heightKey";
    public static final String sleepHoursKey = "sleepHoursKey";
    public static final String sleepMinutesKey = "sleepMinutesKey";
    public static final String wakeUpHoursKey = "wakeUpHoursKey";
    public static final String wakeUpMinutesKey = "wakeUpMinutesKey";

    public static final String drunkWaterKey = "drunkWaterKey";

    public static final String bottleSizeKey = "bottleSizeKey";

    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;

    public DataBase (Context context){
        this.context = context;
        sharedPrefs = context.getSharedPreferences(DataBase, Context.MODE_PRIVATE);
    }

    public void setIsInit(boolean isInit){
        editor = sharedPrefs.edit();
        editor.putBoolean(isInitKey, isInit);
        editor.apply();
    }

    public boolean getIsInit(){
        return sharedPrefs.getBoolean(isInitKey, true);
    }

    public void setCurrentDay(int currentDay){
        editor = sharedPrefs.edit();
        editor.putInt(currentDayKey, currentDay);
        editor.apply();
    }

    public int getCurrentDay(){
        return sharedPrefs.getInt(currentDayKey, Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }

    public void setGender(float gender){
        editor = sharedPrefs.edit();
        editor.putFloat(genderKey, gender);
        editor.apply();
    }

    public float getGender(){
        return sharedPrefs.getFloat(genderKey, 0.04f);
    }

    public void setWeight(int weight){
        editor = sharedPrefs.edit();
        editor.putInt(weightKey, weight);
        editor.apply();
    }

    public int getWeight(){
        return sharedPrefs.getInt(weightKey, 40);
    }

    public void setHeight(int height){
        editor = sharedPrefs.edit();
        editor.putInt(heightKey, height);
        editor.apply();
    }

    public int getHeight(){
        return sharedPrefs.getInt(heightKey, 140);
    }

    public void setWakeUpTime(int hours, int minutes){
        editor = sharedPrefs.edit();
        editor.putInt(wakeUpHoursKey, hours);
        editor.putInt(wakeUpMinutesKey, minutes);
        editor.apply();
    }

    public int getWakeUpTime_hours(){
        return sharedPrefs.getInt(wakeUpHoursKey, 7);
    }

    public int getWakeUpTime_minutes(){
        return sharedPrefs.getInt(wakeUpMinutesKey, 0);
    }

    public void setSleepTime(int hours, int minutes){
        editor = sharedPrefs.edit();
        editor.putInt(sleepHoursKey, hours);
        editor.putInt(sleepMinutesKey, minutes);
        editor.apply();
    }

    public int getSleepTime_hours(){
        return sharedPrefs.getInt(sleepHoursKey, 0);
    }

    public int getSleepTime_minutes(){
        return sharedPrefs.getInt(sleepMinutesKey, 0);
    }

    public void setDrunkWater(int drunkWater){
        editor = sharedPrefs.edit();
        editor.putInt(drunkWaterKey, drunkWater);
        editor.apply();
    }

    public int getDrunkWater(){
        return sharedPrefs.getInt(drunkWaterKey, 0);
    }

    public void setBottleSize(double bottleSize_d){
        float bottleSize_f = (float)bottleSize_d;
        editor = sharedPrefs.edit();
        editor.putFloat(bottleSizeKey, bottleSize_f);
        editor.apply();
    }

    public float getBottleSize(){
        return sharedPrefs.getFloat(bottleSizeKey, 250.0f);
    }
}
