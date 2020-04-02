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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gudigudigudi.commonlib.base.BaseFragment;
import com.gudigudigudi.commonlib.constants.LogTag;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.databinding.FragmentAnimationBinding;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnimationFragment extends BaseFragment implements Button.OnClickListener {

    private Animation mAlphaAnimation, mRotateAnimation, mScaleAnimation, mTranslateAnimation;

    private FragmentAnimationBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAnimationBinding.inflate(inflater, container, false);

        binding.imageView.setBackgroundResource(R.mipmap.ic_launcher);

        mAlphaAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha);
        mRotateAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        mScaleAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.scale);
        mTranslateAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.translate);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnStart.setOnClickListener(this);
        binding.btnCancel.setOnClickListener(this);
        binding.btnRotate.setOnClickListener(this);
        binding.btnScale.setOnClickListener(this);
        binding.btnTranslate.setOnClickListener(this);
        binding.btnAccelerateDecelerateInterpolator.setOnClickListener(this);
        binding.btnAccelerateInterpolator.setOnClickListener(this);
        binding.btnDecelerateInterpolator.setOnClickListener(this);
        binding.btnBounceInterpolator.setOnClickListener(this);
        binding.btnCycleInterpolator.setOnClickListener(this);
        binding.btnLinearInterpolator.setOnClickListener(this);
        binding.btnListener.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                binding.imageView.startAnimation(mAlphaAnimation);
                break;
            case R.id.btn_cancel:
                mAlphaAnimation.cancel();
                break;
            case R.id.btn_rotate:
                binding.ivRotate.startAnimation(mRotateAnimation);
                break;
            case R.id.btn_scale:
                binding.ivScale.startAnimation(mScaleAnimation);
                break;
            case R.id.btn_translate:
                binding.ivTranslate.startAnimation(mTranslateAnimation);
                break;
            case R.id.btn_accelerate_decelerate_interpolator:
                mRotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                binding.ivRotate.startAnimation(mRotateAnimation);
                break;
            case R.id.btn_accelerate_interpolator:
                mRotateAnimation.setInterpolator(new AccelerateInterpolator());
                binding.ivRotate.startAnimation(mRotateAnimation);
                break;
            case R.id.btn_decelerate_interpolator:
                mRotateAnimation.setInterpolator(new DecelerateInterpolator());
                binding.ivRotate.startAnimation(mRotateAnimation);
                break;
            case R.id.btn_bounce_interpolator:
                mRotateAnimation.setInterpolator(new BounceInterpolator());
                binding.ivRotate.startAnimation(mRotateAnimation);
                break;
            case R.id.btn_cycle_interpolator:
                mRotateAnimation.setInterpolator(new CycleInterpolator(3.0f));
                binding.ivRotate.startAnimation(mRotateAnimation);
                break;
            case R.id.btn_linear_interpolator:
                mRotateAnimation.setInterpolator(new LinearInterpolator());
                binding.ivRotate.startAnimation(mRotateAnimation);
                break;
            case R.id.btn_listener:
                mScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        binding.tvListener.setText("animation start...");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        binding.tvListener.setText("animation end...");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        binding.tvListener.setText("animation repeat...");
                    }
                });
                binding.ivScale.startAnimation(mScaleAnimation);
                break;
            default:
                log.debug(LogTag.LOG_UNKNOWN_VIEW_IS_CLICKED);
        }
    }
}
