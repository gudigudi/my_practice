package com.gudigudigudi.apptestespresso;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import com.gudigudigudi.commonlib.base.BaseActivity;


public class WebViewActivity extends BaseActivity {

    public static final String KEY_URL_TO_LOAD = "KEY_URL_TO_LOAD";

    @VisibleForTesting
    protected static final String WEB_FORM_URL = "file:///android_asset/web_form.html";

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mWebView = findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.requestFocus();
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setAllowContentAccess(true);
        mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);

        mWebView.loadUrl(urlFromIntent(getIntent()));
    }

    private static String urlFromIntent(@NonNull Intent intent) {
        String url = intent.getStringExtra(KEY_URL_TO_LOAD);
        return !TextUtils.isEmpty(url) ? url : WEB_FORM_URL;
    }
}
