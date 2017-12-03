package com.gudigudigudi.mdtemplate.base;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

/**
 * Created by gu on 12/3/17.
 */

public class BaseWebView extends WebView {
    public BaseWebView(Context context) {
        super(context);
        setJsNativeObject();
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setJsNativeObject();
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setJsNativeObject();
    }

    private void setJsNativeObject() {
        getSettings().setJavaScriptEnabled(true);
        addJavascriptInterface(new JsNativeObject(), "injectedObject");
    }

    class JsNativeObject {

        @JavascriptInterface
        public void showShareWindow() {

        }
    }
}
