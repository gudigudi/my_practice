package com.gudigudigudi.mdtemplate;

import android.os.AsyncTask;
import android.widget.Toast;

import com.gudigudigudi.mdtemplate.base.BaseApplication;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by gu on 11/12/17.
 * 检查url的状态
 */

public class CheckUrlTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {
        return urlResponse(urls[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(BaseApplication.getContext(), "URL responded with " + result, Toast.LENGTH_SHORT).show();
    }

    private String urlResponse(String checkUrl) {
        InputStream is = null;
        try {
            URL url = new URL(checkUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            int response = conn.getResponseCode();

            Logger.d("The response is: " + response);
            is = conn.getInputStream();
            return String.valueOf(response);
        } catch (IOException e) {
            return "Unable to retrieve urls. URL may be invalid.";
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
