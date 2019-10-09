package com.gudigudigudi.commonlib.base;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    public static BaseFragment newInstance(Bundle bundle) {
        BaseFragment fragment = new BaseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
    }
}
