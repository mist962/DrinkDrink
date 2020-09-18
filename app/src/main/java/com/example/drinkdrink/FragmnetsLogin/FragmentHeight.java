package com.example.drinkdrink.FragmnetsLogin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.fragment.app.Fragment;

import com.example.drinkdrink.R;

public class FragmentHeight extends Fragment {

    NumberPicker numPic, numPic2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_fragment_height, container, false);

        Button btn = v.findViewById(R.id.button_height_next);
        numPic = v.findViewById(R.id.numberPicker_height);
        numPic2 = v.findViewById(R.id.numberPicker_height_sm);

        numPic.setMinValue(140);
        numPic.setMaxValue(220);
        numPic.setWrapSelectorWheel(false);

        String[] sm = {getResources().getString(R.string.sm)};
        numPic2.setDisplayedValues(sm);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentStartManager) requireActivity()).setHeight(numPic.getValue());
                ((FragmentStartManager) requireActivity()).onNavigationItemSelected(5);
            }
        });

        return v;
    }
}
