package com.gudigudigudi.mdtemplate.activity.materialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gudigudigudi.mdtemplate.R;
import com.orhanobut.logger.Logger;

/**
 * Created by gu on 10/22/17.
 */

public class PageFragment3 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page3, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.d("onDestroyView");
    }
}
