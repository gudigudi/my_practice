package com.gudigudigudi.mdtemplate.fragment.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;


/**
 * Created by gu on 10/21/17.
 */

public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private List<String> tities;

    public CustomFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> fragments, List<String> tities) {
        super(fm, behavior);

        this.fragments = fragments;
        this.tities = tities;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tities.get(position);
    }
}
