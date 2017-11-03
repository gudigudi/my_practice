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

    private AnimationDrawable animationDrawable;
    private ImageView iv_loading;
    private String loadingTip;
    private TextView tv_loading;
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
        iv_loading = findViewById(R.id.iv_loading);
        tv_loading = findViewById(R.id.tv_loading);

        // init data.
        iv_loading.setBackgroundResource(resId);
        animationDrawable = (AnimationDrawable) iv_loading.getBackground();
        iv_loading.post(new Runnable() {
            @Override
            public void run() {
                animationDrawable.start();
            }
        });
        tv_loading.setText(loadingTip);
    }
}
