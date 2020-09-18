package com.example.drinkdrink.FragmentsSettings;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.drinkdrink.BusStation;
import com.example.drinkdrink.DataBase.DataBase;
import com.example.drinkdrink.POJO.WaterNormPOJO;
import com.example.drinkdrink.R;

import java.text.DecimalFormat;

public class FragmentSettingsGender extends DialogFragment implements View.OnClickListener {

    DataBase dataBase;

    float gender;
    RadioGroup radioGroup;
    RadioButton radioMale, radioFemale;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataBase = new DataBase(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.settings_fragment_gender, container, false);

        radioGroup = v.findViewById(R.id.radioGroup_gender_settings);
        radioMale = v.findViewById(R.id.radioButtonMale_settings);
        radioFemale = v.findViewById(R.id.radioButtonFemale_settings);
        v.findViewById(R.id.btnOkGender).setOnClickListener(this);

        DecimalFormat df_norm = new DecimalFormat("#.##");
        String gender_value_s = df_norm.format(dataBase.getGender()).replaceAll(",", ".");
        double gender_value_d = Double.parseDouble(gender_value_s);

        if (gender_value_d == 0.04) {
            radioMale.setChecked(true);
        }
        if (gender_value_d == 0.03) {
            radioFemale.setChecked(true);
        }

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonMale_settings) {
                    gender = 0.04f;
                }
                if (checkedId == R.id.radioButtonFemale_settings) {
                    gender = 0.03f;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (gender == 0) {
            gender = dataBase.getGender();
        }

        dataBase.setGender(gender);

        Bundle result = new Bundle();
        result.putFloat("bundleKeyGender", gender);
        getParentFragmentManager().setFragmentResult("keyGender", result);

        BusStation.getBus().post(new WaterNormPOJO(gender, dataBase.getWeight()));

        dismiss();
    }
}
