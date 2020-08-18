package com.example.drinkdrink.fragmnetsLogin;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.example.drinkdrink.R;

import java.util.Objects;

public class FragmentSleep extends Fragment {

    TimePicker timePickerSleep;
    int hourOfDay, minute = 0;
    Button btn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentSleep = inflater.inflate(R.layout.fragment_sleep, container, false);

        btn = fragmentSleep.findViewById(R.id.button_sleep_next);
        timePickerSleep = fragmentSleep.findViewById(R.id.timePickerSleep);
        timePickerSleep.setIs24HourView(true);

        btn.setBackgroundColor(Color.GRAY);
        btn.setEnabled(false);

        timePickerSleep.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDayA, int minuteA) {
                hourOfDay = hourOfDayA;
                minute = minuteA;
                btn.setBackgroundColor(Objects.requireNonNull(getActivity()).getColor(R.color.colorBackground));
                btn.setEnabled(true);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentStartManager) Objects.requireNonNull(getActivity())).setSleep(hourOfDay, minute);
                ((FragmentStartManager) Objects.requireNonNull(getActivity())).timerEvening(hourOfDay, minute);
                ((FragmentStartManager) Objects.requireNonNull(getActivity())).onNavigationItemSelected(7);
            }
        });

        return fragmentSleep;
    }
}