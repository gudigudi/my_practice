package com.gudigudigudi.mdtemplate.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
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
    private Button btn_accelerate_decelerate_interpolator, btn_accelerate_interpolator, btn_decelerate_interpolator, btn_bounce_interpolator, btn_cycle_interpolator, btn_linear_interpolator;

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
        btn_accelerate_decelerate_interpolator = findViewById(R.id.btn_accelerate_decelerate_interpolator);
        btn_accelerate_interpolator = findViewById(R.id.btn_accelerate_interpolator);
        btn_decelerate_interpolator = findViewById(R.id.btn_decelerate_interpolator);
        btn_bounce_interpolator = findViewById(R.id.btn_bounce_interpolator);
        btn_cycle_interpolator = findViewById(R.id.btn_cycle_interpolator);
        btn_linear_interpolator = findViewById(R.id.btn_linear_interpolator);

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
        btn_accelerate_decelerate_interpolator.setOnClickListener(this);
        btn_accelerate_interpolator.setOnClickListener(this);
        btn_decelerate_interpolator.setOnClickListener(this);
        btn_bounce_interpolator.setOnClickListener(this);
        btn_cycle_interpolator.setOnClickListener(this);
        btn_linear_interpolator.setOnClickListener(this);
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
            case R.id.btn_accelerate_decelerate_interpolator:
                rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                iv_rotate.startAnimation(rotateAnimation);
                break;
            case R.id.btn_accelerate_interpolator:
                rotateAnimation.setInterpolator(new AccelerateInterpolator());
                iv_rotate.startAnimation(rotateAnimation);
                break;
            case R.id.btn_decelerate_interpolator:
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                iv_rotate.startAnimation(rotateAnimation);
                break;
            case R.id.btn_bounce_interpolator:
                rotateAnimation.setInterpolator(new BounceInterpolator());
                iv_rotate.startAnimation(rotateAnimation);
                break;
            case R.id.btn_cycle_interpolator:
                rotateAnimation.setInterpolator(new CycleInterpolator(3.0f));
                iv_rotate.startAnimation(rotateAnimation);
                break;
            case R.id.btn_linear_interpolator:
                rotateAnimation.setInterpolator(new LinearInterpolator());
                iv_rotate.startAnimation(rotateAnimation);
                break;
            default:
                Logger.d(LogUtil.LOG_UNKNOWN_VIEW_IS_CLICKED);
        }
    }
}
