package com.gudigudigudi.mdtemplate.activity.materialdesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import com.gudigudigudi.commonlib.base.BaseActivity;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.view.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerActivity extends BaseActivity {

    private List<View> viewList;
    private List<String> titleList;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private PagerTabStrip pagerTabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = findViewById(R.id.vp);
        pagerTabStrip = findViewById(R.id.pager_tab_strip);
        pagerTabStrip.setTextSize(1, 24);

        initPagers();

        viewPagerAdapter = new ViewPagerAdapter(viewList, titleList);
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void initPagers() {
        viewList = new ArrayList<>();
        titleList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            View view1 = LayoutInflater.from(this).inflate(R.layout.view_pager_layout1, null);
            viewList.add(view1);
            titleList.add("page " + i);
        }
    }
}
