package com.gudigudigudi.appdemojetpack.viewpager2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gudigudigudi.appdemojetpack.databinding.ActivityViewPager2Binding;

public class ViewPager2Activity extends AppCompatActivity {

    private ActivityViewPager2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewPager2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.viewpager.setAdapter(new DemoViewPagerAdapter());
    }
}
