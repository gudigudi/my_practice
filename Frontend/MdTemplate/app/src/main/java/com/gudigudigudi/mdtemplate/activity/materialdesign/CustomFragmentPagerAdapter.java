package com.gudigudigudi.mdtemplate.activity.materialdesign;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by gu on 10/21/17.
 */

public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private List<String> tities;

    public CustomFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> tities) {
        super(fm);
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
