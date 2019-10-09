package com.gudigudigudi.mdtemplate.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gudigudigudi.commonlib.base.BaseFragment;
import com.gudigudigudi.commonlib.constants.LogTag;
import com.gudigudigudi.mdtemplate.R;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnimationFragment extends BaseFragment implements Button.OnClickListener {

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_animation, container, false);

        mImageView = view.findViewById(R.id.image_view);
        mBtnStart = view.findViewById(R.id.btn_start);
        mBtnCancel = view.findViewById(R.id.btn_cancel);
        mBtnRotate = view.findViewById(R.id.btn_rotate);
        mIvRotate = view.findViewById(R.id.iv_rotate);
        mBtnScale = view.findViewById(R.id.btn_scale);
        mIvScale = view.findViewById(R.id.iv_scale);
        mBtnTranslate = view.findViewById(R.id.btn_translate);
        mIvTranslate = view.findViewById(R.id.iv_translate);
        mBtnAccelerateDecelerateInterpolator = view.findViewById(R.id.btn_accelerate_decelerate_interpolator);
        mBtnAccelerateInterpolator = view.findViewById(R.id.btn_accelerate_interpolator);
        mBtnDecelerateInterpolator = view.findViewById(R.id.btn_decelerate_interpolator);
        mBtnBounceInterpolator = view.findViewById(R.id.btn_bounce_interpolator);
        mBtnCycleInterpolator = view.findViewById(R.id.btn_cycle_interpolator);
        mBtnLinearInterpolator = view.findViewById(R.id.btn_linear_interpolator);
        mBtnListener = view.findViewById(R.id.btn_listener);
        mTvListener = view.findViewById(R.id.tv_listener);

        mImageView.setBackgroundResource(R.mipmap.ic_launcher);

        mAlphaAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha);
        mRotateAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        mScaleAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.scale);
        mTranslateAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.translate);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
                log.debug(LogTag.LOG_UNKNOWN_VIEW_IS_CLICKED);
        }
    }
}
