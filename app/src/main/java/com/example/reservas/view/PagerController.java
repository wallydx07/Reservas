package com.example.reservas.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerController extends FragmentPagerAdapter {
    int numoftaps;

    public PagerController(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numoftaps = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
              //  return new reservaDatosClientesFragment();

            case 1:

             //   return new reservaDProductoFragment();

            default:
                return null;





        }
    }

    @Override
    public int getCount() {
        return numoftaps;
    }
}
