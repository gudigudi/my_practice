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
import android.widget.TextView;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.util.LogUtil;
import com.orhanobut.logger.Logger;


public class AnimationActivity extends AppCompatActivity implements Button.OnClickListener {

    private ImageView mImageView;
    private Button mBtnStart, mBtnCancel;

    private Button mBtnRotate;
    private ImageView mIvRotate;

    private Button mBtnScale;
    private ImageView mIvScale;

    private Button mBtnTranslate;
    private ImageView mIvTranslate;

    private Animation mAlphaAnimation, mRotateAnimation, mScaleAnimation, mTranslateAnimation;
    private Button mBtnAccelerateDecelerateInterpolator, mBtnAccelerateInterpolator,
            mBtnDecelerateInterpolator, mBtnBounceInterpolator, mBtnCycleInterpolator,
            mBtnLinearInterpolator;

    private Button mBtnListener;
    private TextView mTvListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        mImageView = findViewById(R.id.image_view);
        mBtnStart = findViewById(R.id.btn_start);
        mBtnCancel = findViewById(R.id.btn_cancel);
        mBtnRotate = findViewById(R.id.btn_rotate);
        mIvRotate = findViewById(R.id.iv_rotate);
        mBtnScale = findViewById(R.id.btn_scale);
        mIvScale = findViewById(R.id.iv_scale);
        mBtnTranslate = findViewById(R.id.btn_translate);
        mIvTranslate = findViewById(R.id.iv_translate);
        mBtnAccelerateDecelerateInterpolator = findViewById(R.id.btn_accelerate_decelerate_interpolator);
        mBtnAccelerateInterpolator = findViewById(R.id.btn_accelerate_interpolator);
        mBtnDecelerateInterpolator = findViewById(R.id.btn_decelerate_interpolator);
        mBtnBounceInterpolator = findViewById(R.id.btn_bounce_interpolator);
        mBtnCycleInterpolator = findViewById(R.id.btn_cycle_interpolator);
        mBtnLinearInterpolator = findViewById(R.id.btn_linear_interpolator);
        mBtnListener = findViewById(R.id.btn_listener);
        mTvListener = findViewById(R.id.tv_listener);

        mImageView.setBackgroundResource(R.mipmap.ic_launcher);

        mAlphaAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.alpha);
        mRotateAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.rotate);
        mScaleAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.scale);
        mTranslateAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.translate);

        mBtnStart.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        mBtnRotate.setOnClickListener(this);
        mBtnScale.setOnClickListener(this);
        mBtnTranslate.setOnClickListener(this);
        mBtnAccelerateDecelerateInterpolator.setOnClickListener(this);
        mBtnAccelerateInterpolator.setOnClickListener(this);
        mBtnDecelerateInterpolator.setOnClickListener(this);
        mBtnBounceInterpolator.setOnClickListener(this);
        mBtnCycleInterpolator.setOnClickListener(this);
        mBtnLinearInterpolator.setOnClickListener(this);
        mBtnListener.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                mImageView.startAnimation(mAlphaAnimation);
                break;
            case R.id.btn_cancel:
                mAlphaAnimation.cancel();
                break;
            case R.id.btn_rotate:
                mIvRotate.startAnimation(mRotateAnimation);
                break;
            case R.id.btn_scale:
                mIvScale.startAnimation(mScaleAnimation);
                break;
            case R.id.btn_translate:
                mIvTranslate.startAnimation(mTranslateAnimation);
                break;
            case R.id.btn_accelerate_decelerate_interpolator:
                mRotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                mIvRotate.startAnimation(mRotateAnimation);
                break;
            case R.id.btn_accelerate_interpolator:
                mRotateAnimation.setInterpolator(new AccelerateInterpolator());
                mIvRotate.startAnimation(mRotateAnimation);
                break;
            case R.id.btn_decelerate_interpolator:
                mRotateAnimation.setInterpolator(new DecelerateInterpolator());
                mIvRotate.startAnimation(mRotateAnimation);
                break;
            case R.id.btn_bounce_interpolator:
                mRotateAnimation.setInterpolator(new BounceInterpolator());
                mIvRotate.startAnimation(mRotateAnimation);
                break;
            case R.id.btn_cycle_interpolator:
                mRotateAnimation.setInterpolator(new CycleInterpolator(3.0f));
                mIvRotate.startAnimation(mRotateAnimation);
                break;
            case R.id.btn_linear_interpolator:
                mRotateAnimation.setInterpolator(new LinearInterpolator());
                mIvRotate.startAnimation(mRotateAnimation);
                break;
            case R.id.btn_listener:
                mScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        mTvListener.setText("animation start...");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mTvListener.setText("animation end...");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        mTvListener.setText("animation repeat...");
                    }
                });
                mIvScale.startAnimation(mScaleAnimation);
                break;
            default:
                Logger.d(LogUtil.LOG_UNKNOWN_VIEW_IS_CLICKED);
        }
    }
}
