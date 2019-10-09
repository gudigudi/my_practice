package com.gudigudigudi.mdtemplate.activity.materialdesign;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import com.gudigudigudi.commonlib.base.BaseActivity;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.fragment.AlertDialogFragment;
import com.gudigudigudi.mdtemplate.fragment.AnimationFragment;
import com.gudigudigudi.mdtemplate.fragment.AnimatorFragment;
import com.gudigudigudi.mdtemplate.fragment.BroadcastFragment;
import com.gudigudigudi.mdtemplate.fragment.DataStoreFragment;
import com.gudigudigudi.mdtemplate.fragment.IMFragment;
import com.gudigudigudi.mdtemplate.fragment.TransitionFragment;
import com.gudigudigudi.mdtemplate.fragment.ViewAnimationFragment;
import com.gudigudigudi.mdtemplate.fragment.adapter.CustomFragmentPagerAdapter;
import com.gudigudigudi.mdtemplate.fragment.adapter.CustomFragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentPagerAdapterActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private PagerTabStrip pagerTabStrip;
    private List<Fragment> fragments;
    private List<String> titles;
    private CustomFragmentPagerAdapter fragmentPagerAdapter;
    private CustomFragmentStatePagerAdapter fragmentStatePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pager_adapter);

        viewPager = findViewById(R.id.view_pager);
        pagerTabStrip = findViewById(R.id.pager_tab_strip);

        init();
        addFragmentStatePagerAdapter();
//        addFragmentPagerAdapter();

        viewPager.addOnPageChangeListener(this);
    }

    private void addFragmentPagerAdapter() {
        fragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(),  FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments, titles);
        viewPager.setAdapter(fragmentPagerAdapter);
    }

    private void addFragmentStatePagerAdapter() {
        fragmentStatePagerAdapter = new CustomFragmentStatePagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments, titles);
        viewPager.setAdapter(fragmentStatePagerAdapter);
    }

    public void init() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        fragments.add(new TransitionFragment());
        fragments.add(new AnimatorFragment());
        fragments.add(new ViewAnimationFragment());
        fragments.add(new AnimationFragment());
        fragments.add(new AlertDialogFragment());
        fragments.add(new BroadcastFragment());
        fragments.add(new DataStoreFragment());
        fragments.add(new IMFragment());
        for (int i = 0; i < fragments.size(); i++) {
            titles.add("page" + i);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //TODO
    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(this, "page " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //TODO
    }
}
