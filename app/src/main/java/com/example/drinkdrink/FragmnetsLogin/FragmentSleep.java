package com.example.drinkdrink.FragmnetsLogin;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.example.drinkdrink.R;

public class FragmentSleep extends Fragment {

    TimePicker timePickerSleep;
    int hourOfDay, minute = 0;
    Button btn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_fragment_sleep, container, false);

        btn = v.findViewById(R.id.button_sleep_next);
        timePickerSleep = v.findViewById(R.id.timePickerSleep);
        timePickerSleep.setIs24HourView(true);

        btn.setBackgroundColor(Color.GRAY);
        btn.setEnabled(false);

        timePickerSleep.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDayA, int minuteA) {
                hourOfDay = hourOfDayA;
                minute = minuteA;
                btn.setBackgroundColor(requireActivity().getColor(R.color.colorBackground));
                btn.setEnabled(true);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentStartManager) requireActivity()).setSleep(hourOfDay, minute);
                ((FragmentStartManager) requireActivity()).timerEvening(hourOfDay, minute);
                ((FragmentStartManager) requireActivity()).onNavigationItemSelected(7);
            }
        });

        return v;
    }
}