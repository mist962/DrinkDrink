package com.example.drinkdrink.fragmnetsLogin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.fragment.app.Fragment;

import com.example.drinkdrink.R;

import java.util.Objects;

public class FragmentWeight extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentWeight = inflater.inflate(R.layout.fragment_weight, container, false);

        Button btn = (Button) fragmentWeight.findViewById(R.id.button_weight_next);
        NumberPicker numPic = (NumberPicker) fragmentWeight.findViewById(R.id.numberPicker_weight);
        NumberPicker numPic2 = (NumberPicker) fragmentWeight.findViewById(R.id.numberPicker_weight_kg);

        numPic.setMinValue(40);
        numPic.setMaxValue(220);
        numPic.setWrapSelectorWheel(false);

        String[] nfg = {"КГ"};
        numPic2.setDisplayedValues(nfg);
        numPic.setWrapSelectorWheel(false);

        numPic.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                ((FragmentStartManager) Objects.requireNonNull(getActivity())).setWeight(newVal);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentStartManager) Objects.requireNonNull(getActivity())).onNavigationItemSelected(4);
            }
        });

        return fragmentWeight;
    }
}
