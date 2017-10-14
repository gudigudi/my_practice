package com.gudigudigudi.mdtemplate.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

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

    private WebView webView;

    private Button btn_send_request;
    private TextView tv_response_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = (WebView) findViewById(R.id.web_view);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.baidu.com");

        btn_send_request = (Button) findViewById(R.id.send_request);
        tv_response_text = (TextView) findViewById(R.id.response_text);

        btn_send_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                sendRequestWithHttpURLConnection();
                sendRequestWithOkHttp();
                sendRequestGetXMLWithOkHttp();
                sendRequestGetJSONWithOkHttp();
            }
        });

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
                        showResponse(response);
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
                showResponse(response.body().string());
            }
        });
    }

    private void showResponse(final String responseData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_response_text.setText(responseData);
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
