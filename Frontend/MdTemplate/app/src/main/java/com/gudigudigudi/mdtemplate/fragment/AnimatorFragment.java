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
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gudigudigudi.commonlib.base.BaseFragment;
import com.gudigudigudi.commonlib.constants.LogTag;
import com.gudigudigudi.mdtemplate.R;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnimatorFragment extends BaseFragment implements View.OnClickListener {

    private Button mButton;
    private ImageView mImageView;
    private TextView mTextView;
    private WebView mWebView;

    private boolean isShow = false;
    private int height = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animator, container, false);

        mButton = view.findViewById(R.id.button);
        mImageView = view.findViewById(R.id.image_view);
        mTextView = view.findViewById(R.id.text_view);
        mWebView = view.findViewById(R.id.web_view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mImageView.setOnClickListener(this);
        mButton.setOnClickListener(this);
        mTextView.setOnClickListener(this);

        new Handler().postDelayed(() -> height = mTextView.getMeasuredHeight(), 1000);

        mWebView.loadUrl("http://baidu.com");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_view:
                Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button:
                ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(getActivity(), R.animator.background);
                objectAnimator.setEvaluator(new ArgbEvaluator());
                objectAnimator.setTarget(mImageView);
                objectAnimator.start();
                break;
            case R.id.text_view:
                isShow = !isShow;
                final ValueAnimator valueAnimator;
                if (isShow) {
                    valueAnimator = ValueAnimator.ofInt(height, 40);
                    mTextView.setText("click to expand");
                } else {
                    valueAnimator = ValueAnimator.ofInt(40, height);
                    mTextView.setText("click to shrink");
                }
                valueAnimator.addUpdateListener(animation -> {
                    mTextView.getLayoutParams().height = (Integer) valueAnimator.getAnimatedValue();
                    mTextView.requestLayout();
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
