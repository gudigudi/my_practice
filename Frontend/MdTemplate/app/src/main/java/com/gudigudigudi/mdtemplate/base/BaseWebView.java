package com.gudigudigudi.mdtemplate.base;

import android.content.Context;
import android.net.http.SslError;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by gu on 12/3/17.
 */

public class BaseWebView extends WebView {

    private final static String TAG = "BaseWebView";

    public BaseWebView(Context context) {
        super(context, null);
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        configWebView();
    }

    private void configWebView() {
        setWebViewClient(new BaseWebViewClient());
        try {
            setCertificate(this.getContext());
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        setJsNativeObject();
    }

    private void setJsNativeObject() {
        getSettings().setJavaScriptEnabled(true);
        addJavascriptInterface(new JsNativeObject(), "JsNativeObject");
    }

    /**
     * @param context
     */
    private void setCertificate(Context context) throws CertificateException, IOException, NoSuchProviderException, NoSuchAlgorithmException, KeyManagementException {
        CertificateFactory factory = CertificateFactory.getInstance("X.509");
        InputStream inputStream = new BufferedInputStream(context.getAssets().open("website-cert.crt"));
        final Certificate certificate;
        try {
            certificate = factory.generateCertificate(inputStream);
        } finally {
            inputStream.close();
        }

        BaseTrustManager trustManager = new BaseTrustManager(certificate);
        SSLContext sslContext = SSLContext.getInstance("TLSv1", "AndroidOpenSSL");
        sslContext.init(null, new TrustManager[]{trustManager}, null);
    }

    private class JsNativeObject {

        @JavascriptInterface
        public void showShareWindow() {

        }

        @JavascriptInterface
        public void printLog(String message) {
            Log.d(TAG, message);
        }

        @JavascriptInterface
        public void showToast(String message) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    private class BaseWebViewClient extends WebViewClient {


        /**
         * ssl error.
         *
         * @param view
         * @param handler
         * @param error
         */
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            Log.e(TAG, "SSL Error: autostop web page loading...");
            handler.cancel();
        }
    }

    private static class BaseTrustManager implements X509TrustManager {

        private Certificate mCertificate;

        public BaseTrustManager(Certificate certificate) {
            mCertificate = certificate;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            if (chain == null || 0 == chain.length) {
                Log.e(TAG, "Certificate chain is invalid.");
            } else if (authType == null || authType.length() == 0) {
                Log.e(TAG, "Authentication type is invalid.");
            } else {
                for (X509Certificate cert : chain) {
                    cert.checkValidity();

                    try {
                        cert.verify(mCertificate.getPublicKey());
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (SignatureException e) {
                        e.printStackTrace();
                    } catch (NoSuchProviderException e) {
                        e.printStackTrace();
                    } catch (InvalidKeyException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
