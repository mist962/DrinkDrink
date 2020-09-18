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

public class FragmentSettingsWeight extends DialogFragment implements View.OnClickListener {

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
        final View v = inflater.inflate(R.layout.settings_fragment_weight, container, false);

        numPic = v.findViewById(R.id.numberPicker_weight_settings);
        numPic2 = v.findViewById(R.id.numberPicker_weight_kg_settings);
        v.findViewById(R.id.btnOkWeight).setOnClickListener(this);

        numPic.setMinValue(40);
        numPic.setMaxValue(220);
        numPic.setValue(dataBase.getWeight());
        numPic.setWrapSelectorWheel(false);

        String[] kg = {getResources().getString(R.string.kg)};
        numPic2.setDisplayedValues(kg);

        return v;
    }

    @Override
    public void onClick(View v) {

        int weight = numPic.getValue();

        dataBase.setWeight(weight);

        Bundle result = new Bundle();
        result.putInt("bundleKeyWeight", weight);
        getParentFragmentManager().setFragmentResult("keyWeight", result);

        BusStation.getBus().post(new WaterNormPOJO(weight, dataBase.getGender()));

        dismiss();
    }
}

