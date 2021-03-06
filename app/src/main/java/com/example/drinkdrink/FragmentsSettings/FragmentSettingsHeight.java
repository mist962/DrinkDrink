package com.example.drinkdrink.FragmentsSettings;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.drinkdrink.BusStation;
import com.example.drinkdrink.DataBase.DataBase;
import com.example.drinkdrink.POJO.WaterNormPOJO;
import com.example.drinkdrink.R;

public class FragmentSettingsHeight extends DialogFragment implements View.OnClickListener {

    DataBase dataBase;
    NumberPicker numPic, numPic2;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataBase = new DataBase(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.settings_fragment_height, container, false);

        numPic = v.findViewById(R.id.numberPicker_height_settings);
        numPic2 = v.findViewById(R.id.numberPicker_height_sm_settings);
        v.findViewById(R.id.btnOkHeight).setOnClickListener(this);

        numPic.setMinValue(140);
        numPic.setMaxValue(220);
        numPic.setValue(dataBase.getHeight());
        numPic.setWrapSelectorWheel(false);

        String[] sm = {getResources().getString(R.string.sm)};
        numPic2.setDisplayedValues(sm);

        return v;
    }

    @Override
    public void onClick(View v) {

        int height = numPic.getValue();

        dataBase.setHeight(height);

        Bundle result = new Bundle();
        result.putInt("bundleKeyHeight", height);
        getParentFragmentManager().setFragmentResult("keyHeight", result);

        BusStation.getBus().post(new WaterNormPOJO(dataBase.getWeight(), dataBase.getGender()));

        dismiss();
    }
}
