package com.example.drinkdrink.FragmnetsLogin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.fragment.app.Fragment;

import com.example.drinkdrink.R;

import java.util.Objects;

public class FragmentHeight extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentHeight = inflater.inflate(R.layout.login_fragment_height, container, false);

        Button btn = fragmentHeight.findViewById(R.id.button_height_next);
        NumberPicker numPic = fragmentHeight.findViewById(R.id.numberPicker_height);
        NumberPicker numPic2 = fragmentHeight.findViewById(R.id.numberPicker_height_sm);

        numPic.setMinValue(140);
        numPic.setMaxValue(220);
        numPic.setWrapSelectorWheel(false);

        String[] nfg = {"СМ"};
        numPic2.setDisplayedValues(nfg);
        numPic.setWrapSelectorWheel(false);

        numPic.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                ((FragmentStartManager) Objects.requireNonNull(getActivity())).setHeight(newVal);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentStartManager) Objects.requireNonNull(getActivity())).onNavigationItemSelected(5);
            }
        });

        return fragmentHeight;
    }
}
