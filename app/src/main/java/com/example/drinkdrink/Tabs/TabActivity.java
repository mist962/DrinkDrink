package com.example.drinkdrink.Tabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.drinkdrink.FragmnetsLogin.FragmentStartManager;
import com.example.drinkdrink.R;
import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {

    public static final String DataBase = "DataBase";
    public static final String isInitKey = "isInitKey";
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity);

        sharedPrefs = getSharedPreferences(DataBase, Context.MODE_PRIVATE);

        // При каждом старте проверяем ключ isInitKey (true или false)
        if (sharedPrefs.getBoolean(isInitKey, true)) {
            Intent intentFirstOpen = new Intent(TabActivity.this, FragmentStartManager.class);
            startActivity(intentFirstOpen);
        }

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        // Устанавливаем для toolbar выполнение функций ActionBar.
        setSupportActionBar(toolbar);

        // Устанавливаем ViewPager.
        // ViewPager - менеджер компоновок, который позволяет пользователю листать влево и
        // вправо через страницы данных.
        // Метод setupWithViewPager() связывает TabLayout и ViewPager.
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    // В setupViewPager() создаем объект PagerAdapter, а также
    // объекты наших фрагментов, которые добавляем в объект PagerAdapter.
    // PagerAdapter - базовый класс, обеспечивающий адаптацию для заполнения страниц внутри ViewPager.
    // Через getSupportFragmentManager() получаем FragmentManager, который
    // обеспечивает взаимодействие с фрагментами, связанными с этим активити.
    // Также назначаем титулы (заглавия) для наших вкладок.
    private void setupViewPager(ViewPager viewPager) {
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new TabDrinkFragment(), "Drink");
        pagerAdapter.addFragment(new TabDescriptionFragment(), "BMI/Water");
        pagerAdapter.addFragment(new TabSettingsFragment(), "Profile");
        viewPager.setAdapter(pagerAdapter);
    }
}

