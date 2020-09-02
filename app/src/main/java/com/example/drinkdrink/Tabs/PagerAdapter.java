package com.example.drinkdrink.Tabs;

import android.preference.PreferenceFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

// Класс PagerAdapter наследует FragmentStatePagerAdapter.
// Данная версия pager (пейджер) наиболее удобна, когда есть большое
// количество страниц, работающих как список представлений.
// Когда страницы не видны пользователю, их фрагмент может быть полностью уничтожен,
// сохраняя состояние этого фрагмента.
// Это позволяет пейджеру удерживать гораздо меньше памяти, связанной с каждой посещенной страницей.
// Иными словами, решение проблемы утечки памяти.
// Экземпляр фрагмента воссоздается после возврата к существующему элементу, и состояние восстанавливается.

public class PagerAdapter extends FragmentStatePagerAdapter {

    // Инициализируем списочный массив ArrayList
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitles = new ArrayList<>();

    // Конструктор класса.
    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    // Получаем фрагмент определенной позиции
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    // Получаем количество фрагментов
    @Override
    public int getCount() {
        return fragmentList.size();
    }

    // Добавляем фрагмент через метод addFragment()
    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitles.add(title);
    }

    // Добавляем титул странички (фрагмента) согласно позиции
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }
}
