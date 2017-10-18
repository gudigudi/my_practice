package com.gudigudigudi.mdtemplate.activity.materialdesign;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.adapter.pagerAdapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<View> viewList;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = (ViewPager) findViewById(R.id.vp);

        initPagers();

        viewPagerAdapter = new ViewPagerAdapter(viewList);
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void initPagers() {
        viewList = new ArrayList<>();
        View view1 = LayoutInflater.from(this).inflate(R.layout.view_pager_layout1, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.view_pager_layout1, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.view_pager_layout1, null);

        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
    }
}
