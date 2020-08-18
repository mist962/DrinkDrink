package com.example.drinkdrink.Tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.drinkdrink.R;

import me.itangqi.waveloadingview.WaveLoadingView;

public class TabChangeFragment extends Fragment {

    WaveLoadingView mWaveLoadingView;
    int value = 0;

    // Конструктор класса
    public TabChangeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentTabChange = inflater.inflate(R.layout.fragment_tab_change, container, false);

        mWaveLoadingView = fragmentTabChange.findViewById(R.id.waveLoadingView);

        mWaveLoadingView.setProgressValue(0);
        mWaveLoadingView.setAmplitudeRatio(40);
        mWaveLoadingView.setAnimDuration(3000);
        mWaveLoadingView.startAnimation();

        mWaveLoadingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < 10; i++) {
                    mWaveLoadingView.setProgressValue(value++);
                }

            }
        });

        return fragmentTabChange;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
