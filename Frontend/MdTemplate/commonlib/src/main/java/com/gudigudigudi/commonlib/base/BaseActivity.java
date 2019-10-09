package com.gudigudigudi.commonlib.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gudigudigudi.commonlib.R;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.hide();
        }

        getLifecycle().addObserver(new BaseLifecycleObserver());
        fragmentManager = getSupportFragmentManager();
    }

    public void push(int containerViewId, Fragment fragment) {
        this.push(containerViewId, fragment, fragment.getClass().getSimpleName());
    }

    public void push(int containerViewId, Fragment fragment, String tag) {
        List<Fragment> currentFragments = fragmentManager.getFragments();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (currentFragments.isEmpty()) {
            // 流程中，第一个步骤的 BaseActivity 进场不需要动画，其余步骤需要
            transaction.setCustomAnimations(
                    R.anim.push_in_left,
                    R.anim.push_out_left,
                    R.anim.push_in_right,
                    R.anim.push_out_right
            );
        }
        transaction.add(containerViewId, fragment, tag);
        if (currentFragments.size() > 1) {
            // 从流程的第二个步骤的 BaseActivity 进场开始，需要同时隐藏上一个 BaseActivity，这样才能看到切换动画
            transaction
                    .hide(currentFragments.get(currentFragments.size() - 1))
                    .addToBackStack(tag);
        }
        transaction.commit();
    }

    /**
     * 如果Fragment不存在，则创建
     *
     * @param fragmentClass
     * @param <T>
     * @return
     */
    public <T extends Fragment> T findOrCreateFragment(@NonNull Class<T> fragmentClass) {
        String tag = fragmentClass.getCanonicalName();
        T fragment = (T) fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                log.error(e.getMessage());
            }
        }
        return fragment;
    }
}
