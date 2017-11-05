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

    private Button button;
    private ImageView imageView;
    private TextView textView;
    private WebView webView;

    private boolean isShow = false;
    private int height = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.image_view);
        textView = findViewById(R.id.text_view);
        webView = findViewById(R.id.web_view);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("http://baidu.com");

        imageView.setOnClickListener(this);
        button.setOnClickListener(this);
        textView.setOnClickListener(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                height = textView.getMeasuredHeight();
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
                objectAnimator.setTarget(imageView);
                objectAnimator.start();
                break;
            case R.id.text_view:
                isShow = !isShow;
                final ValueAnimator valueAnimator;
                if (isShow) {
                    valueAnimator = ValueAnimator.ofInt(height, 40);
                    textView.setText("click to expand");
                } else {
                    valueAnimator = ValueAnimator.ofInt(40, height);
                    textView.setText("click to shrink");
                }
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        textView.getLayoutParams().height = (Integer) valueAnimator.getAnimatedValue();
                        textView.requestLayout();
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
