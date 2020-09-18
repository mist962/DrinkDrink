package com.example.drinkdrink.Tabs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.drinkdrink.DataBase.DataBase;
import com.example.drinkdrink.R;

import java.text.DecimalFormat;
import java.util.Calendar;

import me.itangqi.waveloadingview.WaveLoadingView;

import static java.lang.String.valueOf;

public class TabDrinkFragment extends Fragment implements View.OnClickListener {

    WaveLoadingView mWaveLoadingView;
    Button buttonReset;
    int currentDay, anotherDay, drunkWater;
    DecimalFormat df_bmi = new DecimalFormat("##.##");
    double a = 0;
    TextView textBottle, textMinus, textPlus;
    double bottleSize;

    double waterNorm;
    double value;

    DataBase dataBase;

    // Конструктор класса
    public TabDrinkFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataBase = new DataBase(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.tab_fragment_drink, container, false);

        mWaveLoadingView = v.findViewById(R.id.waveLoadingView);
        buttonReset = v.findViewById(R.id.button4);
        textBottle = v.findViewById(R.id.textBottle);
        textMinus = v.findViewById(R.id.textMinus);
        textMinus.setOnClickListener(this);
        textPlus = v.findViewById(R.id.textPlus);
        textPlus.setOnClickListener(this);

        mWaveLoadingView.setAmplitudeRatio(60);
        mWaveLoadingView.setAnimDuration(4000);
        mWaveLoadingView.startAnimation();

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        waterNorm = ((dataBase.getWeight() * dataBase.getGender())*1000);
        drunkWater = dataBase.getDrunkWater();

        value = (int) ((drunkWater / waterNorm) * 100);

        checkDate();

        bottleSize = dataBase.getBottleSize();
        textBottle.setText(valueOf(bottleSize));

        changeTextColorInRound();

        mWaveLoadingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drunkWater = (int) (dataBase.getDrunkWater() + bottleSize);
                dataBase.setDrunkWater(drunkWater);

                value = (int) ((drunkWater / waterNorm) * 100);

                a = (int) (bottleSize / waterNorm) * 100;

                for (int i = 0; i < a; i++) {
                    value++;
                }

                changeTextColorInRound();

                mWaveLoadingView.setCenterTitle(dataBase.getDrunkWater() + " / " + df_bmi.format((waterNorm)));
                mWaveLoadingView.setProgressValue((int) value);

            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueWaterRoundReset();
            }
        });
    }

    public void checkDate() {

        currentDay = dataBase.getCurrentDay();
        anotherDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        if (currentDay == anotherDay) {

            //Устанавливаем значения - надпись и %-выпитой воды внутри круга
            mWaveLoadingView.setCenterTitle(drunkWater + " / " + df_bmi.format((waterNorm)));
            mWaveLoadingView.setProgressValue((int) value);

        } else {
            dataBase.setCurrentDay(anotherDay);
            valueWaterRoundReset();
        }
    }

    public void valueWaterRoundReset() {

        int a = (int) value;

        for (int i = 0; i < a; i++) {

            mWaveLoadingView.setProgressValue((int) value--);
            if (value < 0) value = 0;
        }

        dataBase.setDrunkWater(0);

        changeTextColorInRound();

        mWaveLoadingView.setCenterTitle(dataBase.getDrunkWater() + " / " + df_bmi.format((waterNorm)));
        mWaveLoadingView.setProgressValue((int) value);
    }

    public void changeTextColorInRound(){
        if (value > 50) {
            mWaveLoadingView.setCenterTitleColor(requireActivity().getColor(R.color.white));
            mWaveLoadingView.setCenterTitleStrokeColor(requireActivity().getColor(R.color.colorBackground));
        } else {
            mWaveLoadingView.setCenterTitleColor(requireActivity().getColor(R.color.colorBackground));
            mWaveLoadingView.setCenterTitleStrokeColor(requireActivity().getColor(R.color.white));
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case (R.id.textMinus):
                if (bottleSize > 0) {
                    bottleSize -= 50.0;
                    textBottle.setText(valueOf(bottleSize));
                    dataBase.setBottleSize(bottleSize);
                }
                if (bottleSize < 0) {
                    bottleSize = 0;
                    dataBase.setBottleSize(bottleSize);
                }
                break;
            case (R.id.textPlus):
                bottleSize += 50.0;
                textBottle.setText(valueOf(bottleSize));
                dataBase.setBottleSize(bottleSize);
                break;
        }
    }
}
