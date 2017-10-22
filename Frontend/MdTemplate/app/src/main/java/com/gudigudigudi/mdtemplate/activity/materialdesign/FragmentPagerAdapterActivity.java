package com.gudigudigudi.mdtemplate.activity.materialdesign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gudigudigudi.mdtemplate.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentPagerAdapterActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private PagerTabStrip pagerTabStrip;
    // data source
    private List<Fragment> fragments;
    private List<String> titles;
    private CustomFragmentPagerAdapter fragmentPagerAdapter;
    private CustomFragmentStatePagerAdapter fragmentStatePagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pager_adapter);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);

        // init.
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        fragments.add(new PageFragment1());
        fragments.add(new PageFragment2());
        fragments.add(new PageFragment3());
        fragments.add(new PageFragment1());
        fragments.add(new PageFragment2());
        fragments.add(new PageFragment3());
        titles.add("page1");
        titles.add("page2");
        titles.add("page3");
        titles.add("page4");
        titles.add("page5");
        titles.add("page6");

        fragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles);
        fragmentStatePagerAdapter = new CustomFragmentStatePagerAdapter(getSupportFragmentManager(), fragments, titles);
//        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setAdapter(fragmentStatePagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(this, "page " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
