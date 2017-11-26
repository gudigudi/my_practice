package com.gudigudigudi.mdtemplate.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.model.App;
import com.gudigudigudi.mdtemplate.util.HttpUtil;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WebActivity extends AppCompatActivity {

    private static final String TAG = "WebActivity";

    private WebView mWebView;
    private EditText mEditText;
    private Button mBtnLoad, mBtnLastPage, mBtnNextPage, mBtnTop;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        mWebView = findViewById(R.id.web_view);
        mEditText = findViewById(R.id.edit_text);
        mBtnLoad = findViewById(R.id.btn_load);
        mBtnLastPage = findViewById(R.id.btn_last_page);
        mBtnNextPage = findViewById(R.id.btn_next_page);
        mBtnTop = findViewById(R.id.btn_top);
        mProgressBar = findViewById(R.id.progressBar);

        mEditText.setText("http://www.zhihu.com");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mEditText.setFocusedByDefault(false);
        }

        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mWebView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    float webViewHeight = mWebView.getContentHeight() * mWebView.getScale();
                    float nowHeight = mWebView.getHeight() + mWebView.getScrollY();

                    if (nowHeight == webViewHeight) {
                        Toast.makeText(WebActivity.this, "being bottom", Toast.LENGTH_SHORT).show();
                        mBtnTop.setVisibility(View.VISIBLE);
                    } else if (mWebView.getScrollY() == 0) {
                        Toast.makeText(WebActivity.this, "being top", Toast.LENGTH_SHORT).show();
                    } else {
                        mBtnTop.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        mBtnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebView.loadUrl(mEditText.getText().toString());
            }
        });
        mBtnLastPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebView.goBack();
            }
        });
        mBtnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebView.goForward();
            }
        });
        mBtnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBtnTop.setVisibility(View.GONE);
                mWebView.scrollTo(0, 0);
            }
        });
    }

    /**
     * rewrite for webview.
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void sendRequestGetJSONWithOkHttp() {
        String address = "http://114.215.134.219/get_data.json";
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                parseJSONWithGSON(response.body().string());
            }
        });
    }

    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>() {
        }.getType());

        for (App app : appList) {
            Logger.json(gson.toJson(app));
        }
    }

    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Logger.json(jsonObject.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String address = "https://www.baidu.com";
                HttpUtil.sendHttpRequest(address, new HttpUtil.HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
//                        showResponse(response);
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                        Logger.d(e);
                    }
                });
            }
        }).start();
    }

    private void sendRequestWithOkHttp() {
        String address = "https://www.baidu.com";
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                showResponse(response.body().string());
            }
        });
    }

    private void sendRequestGetXMLWithOkHttp() {
        String address = "http://114.215.134.219/get_data.xml";
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                parseXMLWithPull(response.body().string());
            }
        });
    }

    private void parseXMLWithPull(String xmlData) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlData));

            int eventType = parser.getEventType();

            String id = "";
            String name = "";
            String version = "";

            while (eventType != parser.END_DOCUMENT) {
                String nodeName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("id".equals(nodeName)) {
                            id = parser.nextText();
                        } else if ("name".equals(nodeName)) {
                            name = parser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = parser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("app".equals(nodeName)) {
                            Logger.d(TAG + " id is " + id);
                            Logger.d(TAG + " name is " + name);
                            Logger.d(TAG + " version is " + version);
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
