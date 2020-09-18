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

public class FragmentWakeUp extends Fragment {

    TimePicker timePickerWakeUp;
    int hourOfDay, minute = 0;
    Button btn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentWakeUp = inflater.inflate(R.layout.login_fragment_wakeup, container, false);

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
                btn.setBackgroundColor(requireActivity().getColor(R.color.colorBackground));
                btn.setEnabled(true);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentStartManager) requireActivity()).setWakeUp(hourOfDay, minute);
                ((FragmentStartManager) requireActivity()).timerMorning(hourOfDay, minute);
                ((FragmentStartManager) requireActivity()).onNavigationItemSelected(6);
            }
        });

        return fragmentWakeUp;
    }
}
