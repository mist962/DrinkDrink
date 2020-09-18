package com.example.drinkdrink.FragmentsSettings;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.drinkdrink.Alarms;
import com.example.drinkdrink.DataBase.DataBase;
import com.example.drinkdrink.R;

public class FragmentSettingsSleep extends DialogFragment implements View.OnClickListener {

    DataBase dataBase;
    TimePicker timePickerSleep;
    Alarms alarms;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataBase = new DataBase(context);
        alarms = new Alarms(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.settings_fragment_sleep, container, false);

        Button btn = v.findViewById(R.id.btnOkSleep);
        btn.setOnClickListener(this);
        timePickerSleep = v.findViewById(R.id.timePickerSleep_settings);
        timePickerSleep.setIs24HourView(true);

        timePickerSleep.setHour(dataBase.getSleepTime_hours());
        timePickerSleep.setMinute(dataBase.getSleepTime_minutes());

        return v;
    }
    @Override
    public void onClick(View v) {

        int hours = timePickerSleep.getHour();
        int minutes = timePickerSleep.getMinute();

        dataBase.setSleepTime(hours, minutes);

        Bundle result = new Bundle();
        result.putInt("bundleKeySleep_hours", hours);
        getParentFragmentManager().setFragmentResult("keySleep", result);
        result.putInt("bundleKeySleep_minutes", minutes);
        getParentFragmentManager().setFragmentResult("keySleep", result);

        alarms.setAlarms();

        dismiss();
    }
}
