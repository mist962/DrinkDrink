package com.example.drinkdrink.Tabs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.drinkdrink.FragmentsSettings.FragmentSettingsGender;
import com.example.drinkdrink.FragmentsSettings.FragmentSettingsHeight;
import com.example.drinkdrink.FragmentsSettings.FragmentSettingsSleep;
import com.example.drinkdrink.FragmentsSettings.FragmentSettingsWakeUp;
import com.example.drinkdrink.FragmentsSettings.FragmentSettingsWeight;
import com.example.drinkdrink.R;

public class TabSettingsFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "MyApp";

    FragmentSettingsGender fragmentSettingsGender = new FragmentSettingsGender();
    FragmentSettingsHeight fragmentSettingsHeight = new FragmentSettingsHeight();
    FragmentSettingsSleep fragmentSettingsSleep = new FragmentSettingsSleep();
    FragmentSettingsWakeUp fragmentSettingsWakeUp = new FragmentSettingsWakeUp();
    FragmentSettingsWeight fragmentSettingsWeight = new FragmentSettingsWeight();

    // Конструктор класса
    public TabSettingsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentTabChange = inflater.inflate(R.layout.tab_fragment_settings, container, false);

        LinearLayout linearGender = fragmentTabChange.findViewById(R.id.linerGender);
        LinearLayout linearWeight = fragmentTabChange.findViewById(R.id.linerWeight);
        LinearLayout linearHeight = fragmentTabChange.findViewById(R.id.linerHeight);
        LinearLayout linearWakeUp = fragmentTabChange.findViewById(R.id.linerWakeUp);
        LinearLayout linearSleep = fragmentTabChange.findViewById(R.id.linerSleep);

        linearGender.setOnClickListener(this);
        linearWeight.setOnClickListener(this);
        linearHeight.setOnClickListener(this);
        linearWakeUp.setOnClickListener(this);
        linearSleep.setOnClickListener(this);

        return fragmentTabChange;
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.linerGender):
                Log.d(TAG, "onClick: Гендер");
                assert getFragmentManager() != null;
                fragmentSettingsGender.show(getFragmentManager(), "fragmentSettingsGender");
                break;
            case (R.id.linerWeight):
                Log.d(TAG, "onClick: Вес");
                assert getFragmentManager() != null;
                fragmentSettingsWeight.show(getFragmentManager(), "fragmentSettingsWeight");
                break;
            case (R.id.linerHeight):
                Log.d(TAG, "onClick: Рост");
                assert getFragmentManager() != null;
                fragmentSettingsHeight.show(getFragmentManager(), "fragmentSettingsHeight");
                break;
            case (R.id.linerWakeUp):
                Log.d(TAG, "onClick: Встал");
                assert getFragmentManager() != null;
                fragmentSettingsWakeUp.show(getFragmentManager(), "fragmentSettingsWakeUp");
                break;
            case (R.id.linerSleep):
                Log.d(TAG, "onClick: Лег");
                assert getFragmentManager() != null;
                fragmentSettingsSleep.show(getFragmentManager(), "fragmentSettingsSleep");
                break;
        }

    }
}
