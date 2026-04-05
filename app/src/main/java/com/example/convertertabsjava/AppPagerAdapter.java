package com.example.convertertabsjava;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AppPagerAdapter extends FragmentStateAdapter {

    public AppPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Retourne le fragment correspondant à la position
        if (position == 0) {
            return new TemperatureFragment();
        } else {
            return new DistanceUnitFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Nombre total d'onglets (Température et Distance)
    }
}
