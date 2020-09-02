package com.example.drinkdrink.FragmnetsLogin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.drinkdrink.R;

import java.util.Objects;

public class FragmentHello extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentHello = inflater.inflate(R.layout.login_fragment_hello, container, false);

        Button btn = fragmentHello.findViewById(R.id.button_hello_next);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentStartManager) Objects.requireNonNull(getActivity())).onNavigationItemSelected(2);
            }
        });

        return fragmentHello;
    }
}
