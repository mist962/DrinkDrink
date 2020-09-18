package com.example.drinkdrink.Tabs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.drinkdrink.DataBase.DataBase;
import com.example.drinkdrink.FragmentsSettings.FragmentSettingsGender;
import com.example.drinkdrink.FragmentsSettings.FragmentSettingsHeight;
import com.example.drinkdrink.FragmentsSettings.FragmentSettingsSleep;
import com.example.drinkdrink.FragmentsSettings.FragmentSettingsWakeUp;
import com.example.drinkdrink.FragmentsSettings.FragmentSettingsWeight;
import com.example.drinkdrink.R;

import java.text.DecimalFormat;

public class TabSettingsFragment extends Fragment implements View.OnClickListener, FragmentResultListener {

    TextView textGender, textWeight, textHeight, textWakeUp, textSleep;
    DataBase dataBase;

    // Конструктор класса
    public TabSettingsFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataBase = new DataBase(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("keyGender", this, this);
        getParentFragmentManager().setFragmentResultListener("keyWeight", this, this);
        getParentFragmentManager().setFragmentResultListener("keyHeight", this, this);
        getParentFragmentManager().setFragmentResultListener("keyWakeUp", this, this);
        getParentFragmentManager().setFragmentResultListener("keySleep", this, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.tab_fragment_settings, container, false);

        textGender = v.findViewById(R.id.textGender);
        textWeight = v.findViewById(R.id.textWeight);
        textHeight = v.findViewById(R.id.textHeight);
        textWakeUp = v.findViewById(R.id.textWakeUp);
        textSleep = v.findViewById(R.id.textSleep);
        v.findViewById(R.id.linerGender).setOnClickListener(this);
        v.findViewById(R.id.linerWeight).setOnClickListener(this);
        v.findViewById(R.id.linerHeight).setOnClickListener(this);
        v.findViewById(R.id.linerWakeUp).setOnClickListener(this);
        v.findViewById(R.id.linerSleep).setOnClickListener(this);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        getGender(dataBase.getGender());
        getWeight(dataBase.getWeight());
        getHeight(dataBase.getHeight());
        getTimeWakeUp(dataBase.getWakeUpTime_hours(), dataBase.getWakeUpTime_minutes());
        getTimeSleep(dataBase.getSleepTime_hours(), dataBase.getSleepTime_minutes());
    }

    @Override
    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

        switch (requestKey) {
            case ("keyGender"):
                float gender = result.getFloat("bundleKeyGender");
                getGender(gender);
                break;
            case ("keyWeight"):
                int weight = result.getInt("bundleKeyWeight");
                getWeight(weight);
                break;
            case ("keyHeight"):
                int height = result.getInt("bundleKeyHeight");
                getHeight(height);
                break;
            case ("keyWakeUp"):
                int wakeUpHours = result.getInt("bundleKeyWakeUp_hours");
                int wakeUpMinutes = result.getInt("bundleKeyWakeUp_minutes");
                getTimeWakeUp(wakeUpHours, wakeUpMinutes);
                break;
            case ("keySleep"):
                int sleepHours = result.getInt("bundleKeySleep_hours");
                int sleepMinutes = result.getInt("bundleKeySleep_minutes");
                getTimeSleep(sleepHours, sleepMinutes);
                break;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.linerGender):
                DialogFragment fragmentGender = new FragmentSettingsGender();
                fragmentGender.show(getParentFragmentManager(), fragmentGender.getClass().getName());
                break;
            case (R.id.linerWeight):
                DialogFragment fragmentWeight = new FragmentSettingsWeight();
                fragmentWeight.show(getParentFragmentManager(), fragmentWeight.getClass().getName());
                break;
            case (R.id.linerHeight):
                DialogFragment fragmentHeight = new FragmentSettingsHeight();
                fragmentHeight.show(getParentFragmentManager(), fragmentHeight.getClass().getName());
                break;
            case (R.id.linerWakeUp):
                FragmentSettingsWakeUp fragmentSettingsWakeUp = new FragmentSettingsWakeUp();
                fragmentSettingsWakeUp.show(getParentFragmentManager(), fragmentSettingsWakeUp.getClass().getName());
                break;
            case (R.id.linerSleep):
                FragmentSettingsSleep fragmentSettingsSleep = new FragmentSettingsSleep();
                fragmentSettingsSleep.show(getParentFragmentManager(), fragmentSettingsSleep.getClass().getName());
                break;
        }
    }

    private void getGender(float gender) {
        DecimalFormat df_norm = new DecimalFormat("#.##");
        String a = df_norm.format(gender).replaceAll(",", ".");
        double b = Double.parseDouble(a);
        if (b == 0.04) {
            String gender_s = getResources().getString(R.string.male);
            textGender.setText(gender_s);
        }
        if (b == 0.03) {
            String gender_s = getResources().getString(R.string.female);
            textGender.setText(gender_s);
        }
    }

    private void getWeight(int weight) {
        String weight_s = weight + " " + getResources().getString(R.string.kg);
        textWeight.setText(weight_s);
    }

    private void getHeight(int height) {
        String height_s = height + " " + getResources().getString(R.string.sm);
        textHeight.setText(height_s);
    }

    public void getTimeWakeUp(int hours, int minutes) {
        String hoursString;
        if (hours < 10) {
            hoursString = "0" + hours;
        } else {
            hoursString = String.valueOf(hours);
        }

        String minutesString;
        if (minutes < 10) {
            minutesString = "0" + minutes;
        } else {
            minutesString = String.valueOf(minutes);
        }

        String text_WakeUp = hoursString + ":" + minutesString;
        textWakeUp.setText(text_WakeUp);
    }

    private void getTimeSleep(int hours, int minutes) {
        String hoursString;
        if (hours < 10) {
            hoursString = "0" + hours;
        } else {
            hoursString = String.valueOf(hours);
        }

        String minutesString;
        if (minutes < 10) {
            minutesString = "0" + minutes;
        } else {
            minutesString = String.valueOf(minutes);
        }

        String text_Sleep = hoursString + ":" + minutesString;
        textSleep.setText(text_Sleep);
    }
}
