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

public class FragmentSettingsWakeUp extends DialogFragment implements View.OnClickListener {

    DataBase dataBase;
    TimePicker timePickerWakeUp;
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
        final View v = inflater.inflate(R.layout.settings_fragment_wake_up, container, false);

        Button btn = v.findViewById(R.id.btnOkWakeUp);
        btn.setOnClickListener(this);
        timePickerWakeUp = v.findViewById(R.id.timePickerWakeUp_settings);
        timePickerWakeUp.setIs24HourView(true);

        timePickerWakeUp.setHour(dataBase.getWakeUpTime_hours());
        timePickerWakeUp.setMinute(dataBase.getWakeUpTime_minutes());

        return v;
    }

    @Override
    public void onClick(View v) {

        int hours = timePickerWakeUp.getHour();
        int minutes = timePickerWakeUp.getMinute();

        dataBase.setWakeUpTime(hours, minutes);

        Bundle result = new Bundle();
        result.putInt("bundleKeyWakeUp_hours", hours);
        getParentFragmentManager().setFragmentResult("keyWakeUp", result);
        result.putInt("bundleKeyWakeUp_minutes", minutes);
        getParentFragmentManager().setFragmentResult("keyWakeUp", result);

        alarms.setAlarms();

        dismiss();
    }
}
