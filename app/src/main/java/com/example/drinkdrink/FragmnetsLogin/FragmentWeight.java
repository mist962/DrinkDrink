package com.example.drinkdrink.FragmnetsLogin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.fragment.app.Fragment;

import com.example.drinkdrink.R;

public class FragmentWeight extends Fragment {

    NumberPicker numPic, numPic2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_fragment_weight, container, false);

        Button btn = (Button) v.findViewById(R.id.button_weight_next);
        numPic = (NumberPicker) v.findViewById(R.id.numberPicker_weight);
        numPic2 = (NumberPicker) v.findViewById(R.id.numberPicker_weight_kg);

        numPic.setMinValue(40);
        numPic.setMaxValue(220);
        numPic.setWrapSelectorWheel(false);

        String[] kg = {getResources().getString(R.string.kg)};
        numPic2.setDisplayedValues(kg);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentStartManager) requireActivity()).setWeight(numPic.getValue());
                ((FragmentStartManager) requireActivity()).onNavigationItemSelected(4);
            }
        });

        return v;
    }
}
