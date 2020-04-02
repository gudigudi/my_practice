package com.gudigudigudi.apptestespresso;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import com.gudigudigudi.apptestespresso.databinding.ActivityWebViewBinding;
import com.gudigudigudi.commonlib.base.BaseActivity;

public class WebViewActivity extends BaseActivity {

    private ActivityWebViewBinding binding;

    public static final String KEY_URL_TO_LOAD = "KEY_URL_TO_LOAD";

    @VisibleForTesting
    protected static final String WEB_FORM_URL = "file:///android_asset/web_form.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWebViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.requestFocus();
        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        binding.webView.getSettings().setAllowFileAccess(true);
        binding.webView.getSettings().setAllowContentAccess(true);
        binding.webView.getSettings().setAllowFileAccessFromFileURLs(true);
        binding.webView.getSettings().setAllowUniversalAccessFromFileURLs(true);

        binding.webView.loadUrl(urlFromIntent(getIntent()));
    }

    private static String urlFromIntent(@NonNull Intent intent) {
        String url = intent.getStringExtra(KEY_URL_TO_LOAD);
        return !TextUtils.isEmpty(url) ? url : WEB_FORM_URL;
    }
}
