package com.gudigudigudi.mdtemplate.fragment;

import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.gudigudigudi.commonlib.base.BaseFragment;
import com.gudigudigudi.commonlib.constants.LogTag;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.databinding.FragmentAnimatorBinding;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnimatorFragment extends BaseFragment implements View.OnClickListener {

    private FragmentAnimatorBinding binding;

    private boolean isShow = false;
    private int height = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAnimatorBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.imageView.setOnClickListener(this);
        binding.button.setOnClickListener(this);
        binding.textView.setOnClickListener(this);

        new Handler().postDelayed(() -> height = binding.textView.getMeasuredHeight(), 1000);

        binding.webView.loadUrl("http://baidu.com");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_view:
                ToastUtils.showShort("clicked");
                break;
            case R.id.button:
                ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(getActivity(), R.animator.background);
                objectAnimator.setEvaluator(new ArgbEvaluator());
                objectAnimator.setTarget(binding.imageView);
                objectAnimator.start();
                break;
            case R.id.text_view:
                isShow = !isShow;
                final ValueAnimator valueAnimator;
                if (isShow) {
                    valueAnimator = ValueAnimator.ofInt(height, 40);
                    binding.textView.setText("click to expand");
                } else {
                    valueAnimator = ValueAnimator.ofInt(40, height);
                    binding.textView.setText("click to shrink");
                }
                valueAnimator.addUpdateListener(animation -> {
                    binding.textView.getLayoutParams().height = (Integer) valueAnimator.getAnimatedValue();
                    binding.textView.requestLayout();
                });
                valueAnimator.setInterpolator(new BounceInterpolator());
                valueAnimator.setDuration(1000);
                valueAnimator.start();
                break;
            default:
                log.debug(LogTag.LOG_UNKNOWN_VIEW_IS_CLICKED);
        }
    }
}
