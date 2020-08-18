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

public class FragmentWakeUp extends Fragment {

    TimePicker timePickerWakeUp;
    int hourOfDay, minute = 0;
    Button btn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentWakeUp = inflater.inflate(R.layout.fragment_wakeup, container, false);

        btn = fragmentWakeUp.findViewById(R.id.button_wakeup_next);
        timePickerWakeUp = fragmentWakeUp.findViewById(R.id.timePickerWakeUp);
        timePickerWakeUp.setIs24HourView(true);

        btn.setBackgroundColor(Color.GRAY);
        btn.setEnabled(false);

        timePickerWakeUp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
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
                ((FragmentStartManager) Objects.requireNonNull(getActivity())).setWakeUp(hourOfDay, minute);
                ((FragmentStartManager) Objects.requireNonNull(getActivity())).timerMorning(hourOfDay, minute);
                ((FragmentStartManager) Objects.requireNonNull(getActivity())).onNavigationItemSelected(6);
            }
        });

        return fragmentWakeUp;
    }
}
