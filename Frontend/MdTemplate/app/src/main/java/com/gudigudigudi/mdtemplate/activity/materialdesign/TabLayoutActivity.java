package com.gudigudigudi.mdtemplate.activity.materialdesign;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.gudigudigudi.mdtemplate.R;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        viewPager = findViewById(R.id.view_pager);
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tab_layout);

        setSupportActionBar(toolbar);

        final List<String> titleList = new ArrayList<>();
        final List<Fragment> fragmentList = new ArrayList<>();

        titleList.add("精选");
        titleList.add("体育");
        titleList.add("巴萨");
        titleList.add("购物");
        titleList.add("明星");
        titleList.add("视频");
        titleList.add("健康");
        titleList.add("励志");
        titleList.add("图文");
        titleList.add("本地");
        titleList.add("动漫");
        titleList.add("搞笑");
        titleList.add("精选");

        for (int i = 0; i < titleList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titleList.get(i)));
            fragmentList.add(new ListFragment());
        }

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabsFromPagerAdapter(fragmentAdapter);
    }
}
