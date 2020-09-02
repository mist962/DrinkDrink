package com.example.drinkdrink.Tabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.drinkdrink.R;

import java.text.DecimalFormat;
import java.util.Objects;

import static com.example.drinkdrink.R.drawable.water_blue;
import static com.example.drinkdrink.R.drawable.water_green;
import static com.example.drinkdrink.R.drawable.water_orange;
import static com.example.drinkdrink.R.drawable.water_yellow;

public class TabDescriptionFragment extends Fragment {

    public static final String DataBase = "DataBase";
    public static final String weightKey = "weightKey";
    public static final String heightKey = "heightKey";
    public static final String waterKey = "waterKey";

    Double bmi;

    TextView textBMIDescription;
    TextView textBMINum;
    TextView textWaterNormNum;

    // Конструктор класса
    public TabDescriptionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentTabDescription = inflater.inflate(R.layout.tab_fragment_description, container, false);

        textBMINum = fragmentTabDescription.findViewById(R.id.textBMINum);
        textWaterNormNum = fragmentTabDescription.findViewById(R.id.textWaterNormNum);
        textBMIDescription = fragmentTabDescription.findViewById(R.id.textBMIDescription);

        return fragmentTabDescription;
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sharedPrefs = Objects.requireNonNull(this.getActivity()).getSharedPreferences(DataBase, Context.MODE_PRIVATE);

        // Считаем, форматируем и устанавливаем индекс массы тела
        DecimalFormat df_bmi = new DecimalFormat("##.##"); // Форматируем результат

        bmi = Double.parseDouble(String.valueOf(sharedPrefs.getInt(weightKey, 40)))                         // Вес делим на
                / ((Double.parseDouble(String.valueOf(sharedPrefs.getInt(heightKey, 140))) / 100)              // Рост в квадрате
                * (Double.parseDouble(String.valueOf(sharedPrefs.getInt(heightKey, 140))) / 100));             // и получаем BMI
        textBMINum.setText(df_bmi.format(bmi));

        setTextDescriptionAboutBMI();

        // Устанавливаем норму потребления воды
        DecimalFormat df_norm = new DecimalFormat("#.#");
        String waterNormString = df_norm.format(sharedPrefs.getFloat(waterKey, 0));
        textWaterNormNum.setText(String.format("%s%s%s", waterNormString, " ", getString(R.string.L)));

    }

    public void setTextDescriptionAboutBMI() {
        if (bmi < 18.5) {
            textBMIDescription.setText(R.string.Underweight);
            textBMIDescription.setBackgroundResource(water_blue);
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            textBMIDescription.setText(R.string.Normal);
            textBMIDescription.setBackgroundResource(water_green);
        } else if (bmi >= 25 && bmi <= 29.9) {
            textBMIDescription.setText(R.string.Overweight);
            textBMIDescription.setBackgroundResource(water_yellow);
        } else {
            textBMIDescription.setText(R.string.Obese);
            textBMIDescription.setBackgroundResource(water_orange);
        }
    }
}
