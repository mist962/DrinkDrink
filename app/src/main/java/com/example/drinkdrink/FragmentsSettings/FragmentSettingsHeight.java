package com.example.drinkdrink.FragmentsSettings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.drinkdrink.R;

public class FragmentSettingsHeight extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragmentSettingsHeightView = inflater.inflate(R.layout.settings_fragment_height, container, false);

        return fragmentSettingsHeightView;
    }
}
