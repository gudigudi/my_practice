package com.gudigudigudi.mdtemplate.activity.materialdesign;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ToastUtils;
import com.gudigudigudi.commonlib.base.BaseActivity;
import com.gudigudigudi.mdtemplate.databinding.ActivityFragmentPagerAdapterBinding;
import com.gudigudigudi.mdtemplate.fragment.AlertDialogFragment;
import com.gudigudigudi.mdtemplate.fragment.AnimationFragment;
import com.gudigudigudi.mdtemplate.fragment.AnimatorFragment;
import com.gudigudigudi.mdtemplate.fragment.BroadcastFragment;
import com.gudigudigudi.mdtemplate.fragment.IMFragment;
import com.gudigudigudi.mdtemplate.fragment.TransitionFragment;
import com.gudigudigudi.mdtemplate.fragment.ViewAnimationFragment;
import com.gudigudigudi.mdtemplate.fragment.adapter.CustomFragmentPagerAdapter;
import com.gudigudigudi.mdtemplate.fragment.adapter.CustomFragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentPagerAdapterActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private List<Fragment> fragments;
    private List<String> titles;
    private CustomFragmentPagerAdapter fragmentPagerAdapter;
    private CustomFragmentStatePagerAdapter fragmentStatePagerAdapter;

    private ActivityFragmentPagerAdapterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFragmentPagerAdapterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        addFragmentStatePagerAdapter();
//        addFragmentPagerAdapter();

        binding.viewPager.addOnPageChangeListener(this);
    }

    private void addFragmentPagerAdapter() {
        fragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments, titles);
        binding.viewPager.setAdapter(fragmentPagerAdapter);
    }

    private void addFragmentStatePagerAdapter() {
        fragmentStatePagerAdapter = new CustomFragmentStatePagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments, titles);
        binding.viewPager.setAdapter(fragmentStatePagerAdapter);
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
        ToastUtils.showShort("page " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //TODO
    }
}
