package com.gudigudigudi.appdemojetpack.viewpager2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.gudigudigudi.appdemojetpack.R;

public class ViewPager2Activity extends AppCompatActivity {

    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);

        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new DemoViewPagerAdapter());
    }
}
