package com.gudigudigudi.appdemojetpack.viewpager2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.gudigudigudi.appdemojetpack.databinding.ActivityScreenSlideBinding;

public class ScreenSlidePagerActivity extends FragmentActivity {

    private static final int NUM_PAGES = 5;

    private ActivityScreenSlideBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScreenSlideBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.viewPager.setAdapter(new ScreenSlidePagerAdapter(this));
    }

    @Override
    public void onBackPressed() {
        if (binding.viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {

        public ScreenSlidePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return new ScreenSlidePageFragment();
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}
