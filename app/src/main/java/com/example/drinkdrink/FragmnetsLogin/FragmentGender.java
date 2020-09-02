package com.example.drinkdrink.FragmnetsLogin;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.example.drinkdrink.R;

import java.util.Objects;

public class FragmentGender extends Fragment {

    RadioGroup radioGroup;
    RadioButton radioMale, radioFemale;
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentGender = inflater.inflate(R.layout.login_fragment_gender, container, false);

        btn = fragmentGender.findViewById(R.id.button_gender_next);
        radioGroup = fragmentGender.findViewById(R.id.radioGroup_gender);
        radioMale = fragmentGender.findViewById(R.id.radioMale);
        radioFemale = fragmentGender.findViewById(R.id.radioFemale);

        btn.setBackgroundColor(Color.GRAY);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioMale) {
                    btn.setBackgroundColor(Objects.requireNonNull(getActivity()).getColor(R.color.colorBackground));
                    radioMale.setTextColor(getActivity().getColor(R.color.colorBackground));
                    radioFemale.setTextColor(getActivity().getColor(R.color.silver));
                }
                if (checkedId == R.id.radioFemale) {
                    btn.setBackgroundColor(Objects.requireNonNull(getActivity()).getColor(R.color.colorBackground));
                    radioFemale.setTextColor(getActivity().getColor(R.color.colorBackground));
                    radioMale.setTextColor(getActivity().getColor(R.color.silver));
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioMale.isChecked()) {
                    int gender = 1;
                    ((FragmentStartManager) Objects.requireNonNull(getActivity())).setGender(gender);
                    ((FragmentStartManager) Objects.requireNonNull(getActivity())).onNavigationItemSelected(3);
                } else if (radioFemale.isChecked()) {
                    int gender = 2;
                    ((FragmentStartManager) Objects.requireNonNull(getActivity())).setGender(gender);
                    ((FragmentStartManager) Objects.requireNonNull(getActivity())).onNavigationItemSelected(3);
                }
            }
        });

        return fragmentGender;
    }


}
