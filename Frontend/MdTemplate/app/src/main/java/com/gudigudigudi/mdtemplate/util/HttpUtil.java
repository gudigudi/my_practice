package com.gudigudigudi.mdtemplate.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by gu on 10/7/17.
 */

public class HttpUtil {

    private static final int CONNECTION_TIMEOUT = 8000;
    private static final int READ_TIMEOUT = 8000;

    public static void sendOkHttpRequest(String address, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendHttpRequest(String address, final HttpCallbackListener listener) {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(address);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            if (listener != null) {
                listener.onFinish(response.toString());
            }
        } catch (Exception e) {
            listener.onError(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public interface HttpCallbackListener {
        void onFinish(String response);

        void onError(Exception e);
    }

}
