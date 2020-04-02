package com.gudigudigudi.mdtemplate.activity.materialdesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.gudigudigudi.commonlib.base.BaseActivity;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.databinding.ActivityViewPagerBinding;
import com.gudigudigudi.mdtemplate.view.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerActivity extends BaseActivity {

    private List<View> viewList;
    private List<String> titleList;

    private ActivityViewPagerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewPagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.pagerTabStrip.setTextSize(1, 24);

        initPagers();
        binding.viewpager.setAdapter(new ViewPagerAdapter(viewList, titleList));
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
