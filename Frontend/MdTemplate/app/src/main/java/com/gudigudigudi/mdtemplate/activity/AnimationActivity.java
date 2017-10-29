package com.gudigudigudi.mdtemplate.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.util.LogUtil;
import com.orhanobut.logger.Logger;


public class AnimationActivity extends AppCompatActivity implements Button.OnClickListener {

    private ImageView imageView;
    private Button btn_start, btn_cancel;

    private Button btn_rotate;
    private ImageView iv_rotate;

    private Button btn_scale;
    private ImageView iv_scale;

    private Button btn_translate;
    private ImageView iv_translate;

    private Animation alphaAnimation, rotateAnimation, scaleAnimation, translateAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        imageView = findViewById(R.id.image_view);
        btn_start = findViewById(R.id.btn_start);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_rotate = findViewById(R.id.btn_rotate);
        iv_rotate = findViewById(R.id.iv_rotate);
        btn_scale = findViewById(R.id.btn_scale);
        iv_scale = findViewById(R.id.iv_scale);
        btn_translate = findViewById(R.id.btn_translate);
        iv_translate = findViewById(R.id.iv_translate);

        imageView.setBackgroundResource(R.mipmap.ic_launcher);

        alphaAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.alpha);
        rotateAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.rotate);
        scaleAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.scale);
        translateAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.translate);

        btn_start.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        btn_rotate.setOnClickListener(this);
        btn_scale.setOnClickListener(this);
        btn_translate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                imageView.startAnimation(alphaAnimation);
                break;
            case R.id.btn_cancel:
                alphaAnimation.cancel();
                break;
            case R.id.btn_rotate:
                iv_rotate.startAnimation(rotateAnimation);
                break;
            case R.id.btn_scale:
                iv_scale.startAnimation(scaleAnimation);
                break;
            case R.id.btn_translate:
                iv_translate.startAnimation(translateAnimation);
                break;
            default:
                Logger.d(LogUtil.LOG_UNKNOWN_VIEW_IS_CLICKED);
        }
    }
}
