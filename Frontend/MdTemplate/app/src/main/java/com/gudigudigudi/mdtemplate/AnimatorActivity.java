package com.gudigudigudi.mdtemplate;

import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gudigudigudi.mdtemplate.util.LogUtil;
import com.orhanobut.logger.Logger;

public class AnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private ImageView mImageView;
    private TextView mTextView;
    private WebView mWebView;

    private boolean isShow = false;
    private int height = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        mButton = findViewById(R.id.button);
        mImageView = findViewById(R.id.image_view);
        mTextView = findViewById(R.id.text_view);
        mWebView = findViewById(R.id.web_view);

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl("http://baidu.com");

        mImageView.setOnClickListener(this);
        mButton.setOnClickListener(this);
        mTextView.setOnClickListener(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                height = mTextView.getMeasuredHeight();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_view:
                Toast.makeText(AnimatorActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button:
                ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(AnimatorActivity.this, R.animator.background);
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
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mTextView.getLayoutParams().height = (Integer) valueAnimator.getAnimatedValue();
                        mTextView.requestLayout();
                    }
                });
                valueAnimator.setInterpolator(new BounceInterpolator());
                valueAnimator.setDuration(1000);
                valueAnimator.start();
                break;
            default:
                Logger.d(LogUtil.LOG_UNKNOWN_VIEW_IS_CLICKED);
        }
    }
}
