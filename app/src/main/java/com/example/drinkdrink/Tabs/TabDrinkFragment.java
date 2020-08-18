package com.example.drinkdrink.Tabs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.drinkdrink.R;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Objects;

import me.itangqi.waveloadingview.WaveLoadingView;

import static java.lang.String.valueOf;

public class TabDrinkFragment extends Fragment implements View.OnClickListener {

    public static final String myDataBase = "DataBase";

    public static final String currentDayOfMonthKey = "currentDayOfMonthKey";

    public static final String waterKey = "waterKey";
    public static final String waterRoundValueKey = "waterRoundValueKey";

    public static final String waterDrinkedKey = "waterDrinkedKey";

    public static final String bottleSizeKey = "bottleSizeKey";

    public static final String sleepHoursKey = "sleepHoursKey";
    public static final String sleepMinutesKey = "sleepMinutesKey";
    public static final String wakeUpHoursKey = "wakeUpHoursKey";
    public static final String wakeUpMinutesKey = "wakeUpMinutesKey";

    WaveLoadingView mWaveLoadingView;
    Button buttonReset;
    int currentDayOfMonth, anotherDayOfMonth, value, drinkedWater;
    TextView textTimeWakeUp, textTimeSleep;
    String timeHoursWakeUp, timeMinutesWakeup, timeHoursSleep, timeMinutesSleep;
    DecimalFormat df_bmi = new DecimalFormat("##.##");
    double a = 0;
    TextView textBottle, textMinus, textPlus;
    double bottleSize;
    private SharedPreferences sharedPrefs;

    // Конструктор класса
    public TabDrinkFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentDrink = inflater.inflate(R.layout.fragment_tab_drink, container, false);

        mWaveLoadingView = fragmentDrink.findViewById(R.id.waveLoadingView);
        buttonReset = fragmentDrink.findViewById(R.id.button4);

        textTimeWakeUp = fragmentDrink.findViewById(R.id.textTimeWakeUp);
        textTimeSleep = fragmentDrink.findViewById(R.id.textTimeSleep);

        textBottle = fragmentDrink.findViewById(R.id.textBottle);
        textMinus = fragmentDrink.findViewById(R.id.textMinus);
        textMinus.setOnClickListener(this);
        textPlus = fragmentDrink.findViewById(R.id.textPlus);
        textPlus.setOnClickListener(this);

        mWaveLoadingView.setAmplitudeRatio(60);

        mWaveLoadingView.setAnimDuration(4000);
        mWaveLoadingView.startAnimation();

        return fragmentDrink;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onStart() {
        super.onStart();

        sharedPrefs = Objects.requireNonNull(this.getActivity()).getSharedPreferences(myDataBase, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPrefs.edit();
        bottleSize = sharedPrefs.getFloat(bottleSizeKey, 250.0f);

        checkDate();

        textBottle.setText(valueOf(bottleSize));

        if (value > 50) {
            mWaveLoadingView.setCenterTitleColor(Objects.requireNonNull(getActivity()).getColor(R.color.white));
            mWaveLoadingView.setCenterTitleStrokeColor(Objects.requireNonNull(getActivity()).getColor(R.color.colorBackground));
        } else {
            mWaveLoadingView.setCenterTitleColor(Objects.requireNonNull(getActivity()).getColor(R.color.colorBackground));
            mWaveLoadingView.setCenterTitleStrokeColor(Objects.requireNonNull(getActivity()).getColor(R.color.white));
        }

        mWaveLoadingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drinkedWater += (int) bottleSize;
                editor.putInt(waterDrinkedKey, drinkedWater);
                value = sharedPrefs.getInt(waterRoundValueKey, 0);
                a = (int) bottleSize / (sharedPrefs.getFloat(waterKey, 2.0f) * 1000.0) * 100.0;
                for (int i = 0; i < a; i++) {
                    mWaveLoadingView.setProgressValue(value++);
                }
                editor.putInt(waterRoundValueKey, value);
                editor.apply();

                if (value > 50) {
                    mWaveLoadingView.setCenterTitleColor(Objects.requireNonNull(getActivity()).getColor(R.color.white));
                    mWaveLoadingView.setCenterTitleStrokeColor(Objects.requireNonNull(getActivity()).getColor(R.color.colorBackground));
                }
                mWaveLoadingView.setCenterTitle(sharedPrefs.getInt(waterDrinkedKey, 0) + " / " + df_bmi.format((sharedPrefs.getFloat(waterKey, 2.0f) * 1000)));
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueWaterRoudReset();
            }
        });

        //Временная мера для вида
        showTime();

    }

    public void checkDate() {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        currentDayOfMonth = sharedPrefs.getInt(currentDayOfMonthKey, 0);
        anotherDayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        if (currentDayOfMonth == anotherDayOfMonth) {
            value = sharedPrefs.getInt(waterRoundValueKey, 0);
            drinkedWater = sharedPrefs.getInt(waterDrinkedKey, 0);
            mWaveLoadingView.setCenterTitle(sharedPrefs.getInt(waterDrinkedKey, 0) + " / " + df_bmi.format((sharedPrefs.getFloat(waterKey, 2.0f) * 1000)));
            mWaveLoadingView.setProgressValue(sharedPrefs.getInt(waterRoundValueKey, 0));
        }
        if (currentDayOfMonth < anotherDayOfMonth) {
            editor.putInt(currentDayOfMonthKey, anotherDayOfMonth);
            valueWaterRoudReset();
            editor.apply();
        }
        if (currentDayOfMonth > anotherDayOfMonth) {
            editor.putInt(currentDayOfMonthKey, anotherDayOfMonth);
            valueWaterRoudReset();
            editor.apply();
        }
    }

    public void valueWaterRoudReset() {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        int a = sharedPrefs.getInt(waterRoundValueKey, 0);
        ;
        for (int i = 0; i < a; i++) {
            mWaveLoadingView.setProgressValue(value--);
            if (value < 0) value = 0;
            editor.putInt(waterRoundValueKey, value);
            drinkedWater = 0;
            editor.putInt(waterDrinkedKey, drinkedWater);
        }
        editor.apply();

        mWaveLoadingView.setCenterTitleColor(Objects.requireNonNull(getActivity()).getColor(R.color.colorBackground));
        mWaveLoadingView.setCenterTitleStrokeColor(Objects.requireNonNull(getActivity()).getColor(R.color.white));
        mWaveLoadingView.setCenterTitle(sharedPrefs.getInt(waterDrinkedKey, 0) + " / " + df_bmi.format((sharedPrefs.getFloat(waterKey, 2.0f) * 1000)));
    }

    @SuppressLint("SetTextI18n")
    private void showTime() {
        if (sharedPrefs.getInt(wakeUpHoursKey, 0) < 10) {
            timeHoursWakeUp = "0" + sharedPrefs.getInt(wakeUpHoursKey, 0);
        } else timeHoursWakeUp = Integer.toString(sharedPrefs.getInt(wakeUpHoursKey, 0));

        if (sharedPrefs.getInt(wakeUpMinutesKey, 0) < 10) {
            timeMinutesWakeup = "0" + sharedPrefs.getInt(wakeUpMinutesKey, 0);
        } else timeMinutesWakeup = Integer.toString(sharedPrefs.getInt(wakeUpMinutesKey, 0));

        textTimeWakeUp.setText("Время вставать " + timeHoursWakeUp + " : " + timeMinutesWakeup);

        if (sharedPrefs.getInt(sleepHoursKey, 0) < 10) {
            timeHoursSleep = "0" + sharedPrefs.getInt(sleepHoursKey, 0);
        } else timeHoursSleep = Integer.toString(sharedPrefs.getInt(sleepHoursKey, 0));

        if (sharedPrefs.getInt(sleepMinutesKey, 0) < 10) {
            timeMinutesSleep = "0" + sharedPrefs.getInt(sleepMinutesKey, 0);
        } else timeMinutesSleep = Integer.toString(sharedPrefs.getInt(sleepMinutesKey, 0));

        textTimeSleep.setText("Время спать " + timeHoursSleep + " : " + timeMinutesSleep);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        switch (v.getId()) {
            case (R.id.textMinus):
                if (bottleSize > 0) {
                    bottleSize -= 50.0;
                    textBottle.setText(valueOf(bottleSize));
                    editor.putFloat(bottleSizeKey, (float) bottleSize);
                }
                if (bottleSize < 0) {
                    bottleSize = 0;
                    editor.putFloat(bottleSizeKey, (float) bottleSize);
                }
                break;
            case (R.id.textPlus):
                bottleSize += 50.0;
                textBottle.setText(valueOf(bottleSize));
                editor.putFloat(bottleSizeKey, (float) bottleSize);
                break;
        }
        editor.apply();
    }
}
