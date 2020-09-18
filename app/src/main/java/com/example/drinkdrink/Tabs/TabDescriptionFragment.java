package com.example.drinkdrink.Tabs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.drinkdrink.BusStation;
import com.example.drinkdrink.DataBase.DataBase;
import com.example.drinkdrink.POJO.WaterNormPOJO;
import com.example.drinkdrink.R;
import com.squareup.otto.Subscribe;

import java.text.DecimalFormat;

import static com.example.drinkdrink.R.drawable.water_blue;
import static com.example.drinkdrink.R.drawable.water_green;
import static com.example.drinkdrink.R.drawable.water_orange;
import static com.example.drinkdrink.R.drawable.water_yellow;

public class TabDescriptionFragment extends Fragment {

    Double bmi;

    TextView textBMIDescription, textBMINum, textWaterNormNum;

    DataBase dataBase;

    // Конструктор класса
    public TabDescriptionFragment() {
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
        final View v = inflater.inflate(R.layout.tab_fragment_description, container, false);

        textBMINum = v.findViewById(R.id.textBMINum);
        textWaterNormNum = v.findViewById(R.id.textWaterNormNum);
        textBMIDescription = v.findViewById(R.id.textBMIDescription);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        setWaterNormal();

        BusStation.getBus().register(this);
    }

    @Subscribe
    public void getWeight(WaterNormPOJO massage) {
        float waterNorm = massage.getWeight() * massage.getGender();
        DecimalFormat df_norm = new DecimalFormat("#.#");
        String waterNormString = df_norm.format(waterNorm);
        textWaterNormNum.setText(String.format("%s%s%s%s", "≈ ", waterNormString, " ", getString(R.string.L)));
        setBMI(dataBase.getWeight(), dataBase.getHeight());
        setTextDescriptionAboutBMI();
    }

    @Override
    public void onResume() {
        super.onResume();

        // Считаем, форматируем и устанавливаем индекс массы тела
        DecimalFormat df_bmi = new DecimalFormat("##.##"); // Форматируем результат

        bmi = Double.parseDouble(String.valueOf(dataBase.getWeight()))                         // Вес делим на
                / ((Double.parseDouble(String.valueOf(dataBase.getHeight())) / 100)              // Рост в квадрате
                * (Double.parseDouble(String.valueOf(dataBase.getHeight())) / 100));             // и получаем BMI
        textBMINum.setText(df_bmi.format(bmi));

        setTextDescriptionAboutBMI();
    }

    public void setWaterNormal() {
        // Устанавливаем норму потребления воды
        float waterNorm = ((float) dataBase.getWeight()) * dataBase.getGender(); // Вес умножаем на 0.03 или 0.04 в зависимости от гендера
        DecimalFormat df_norm = new DecimalFormat("#.#");
        String waterNormString = df_norm.format(waterNorm);
        textWaterNormNum.setText(String.format("%s%s%s%s", "≈ ", waterNormString, " ", getString(R.string.L)));
    }

    public void setBMI(int weight, int height) {
        // Считаем, форматируем и устанавливаем индекс массы тела
        DecimalFormat df_bmi = new DecimalFormat("##.##"); // Форматируем результат

        bmi = Double.parseDouble(String.valueOf(weight))                         // Вес делим на
                / ((Double.parseDouble(String.valueOf(height)) / 100)              // Рост в квадрате
                * (Double.parseDouble(String.valueOf(height)) / 100));             // и получаем BMI
        textBMINum.setText(df_bmi.format(bmi));
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

    @Override
    public void onPause() {
        super.onPause();
        BusStation.getBus().unregister(this);
    }
}
