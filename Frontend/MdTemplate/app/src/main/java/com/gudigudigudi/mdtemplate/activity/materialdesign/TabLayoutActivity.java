package com.gudigudigudi.mdtemplate.activity.materialdesign;

import android.os.Bundle;
import android.view.Menu;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.ListFragment;

import com.blankj.utilcode.util.ToastUtils;
import com.gudigudigudi.commonlib.base.BaseActivity;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.databinding.ActivityTabLayoutBinding;
import com.gudigudigudi.mdtemplate.fragment.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TabLayoutActivity extends BaseActivity {

    private ActivityTabLayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTabLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setTitle("Toolbar");
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_create);

        binding.toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_settings:
                    ToastUtils.showShort("menu settings");
                    break;
                case R.id.menu_share:
                    ToastUtils.showShort("menu share");
                    break;
                default:
                    break;
            }
            return true;
        });

        final List<Fragment> fragmentList = new ArrayList<>();

        final String[] titles = {"精选", "体育", "巴萨", "购物", "明星", "视频", "健康", "励志", "图文", "本地", "动漫", "搞笑", "精选"};
        for (int i = 0; i < titles.length; i++) {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(titles[i]));
            fragmentList.add(new ListFragment());
        }

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragmentList, Arrays.asList(titles));
        binding.viewPager.setAdapter(fragmentAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar2, menu);
        return true;
    }
}
