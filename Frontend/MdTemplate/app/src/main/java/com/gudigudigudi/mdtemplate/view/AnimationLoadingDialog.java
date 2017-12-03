package com.gudigudigudi.mdtemplate.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;
import android.widget.TextView;

import com.gudigudigudi.mdtemplate.R;

/**
 * Created by gu on 11/4/17.
 */

public class AnimationLoadingDialog extends AlertDialog {

    private AnimationDrawable mAnimationDrawable;
    private ImageView mIvLoading;
    private String loadingTip;
    private TextView mTvLoading;
    private int resId;

    public AnimationLoadingDialog(@NonNull Context context, String loadingTip, int resId) {
        super(context);

        this.loadingTip = loadingTip;
        this.resId = resId;
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init view.
        setContentView(R.layout.animation_loading_dialog);
        mIvLoading = findViewById(R.id.iv_loading);
        mTvLoading = findViewById(R.id.tv_loading);

        // init data.
        mIvLoading.setBackgroundResource(resId);
        mAnimationDrawable = (AnimationDrawable) mIvLoading.getBackground();
        mIvLoading.post(new Runnable() {
            @Override
            public void run() {
                mAnimationDrawable.start();
            }
        });
        mTvLoading.setText(loadingTip);
    }
}
