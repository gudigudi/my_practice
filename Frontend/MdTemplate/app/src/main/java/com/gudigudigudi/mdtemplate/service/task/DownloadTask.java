package com.gudigudigudi.mdtemplate.service.task;

import android.os.AsyncTask;
import android.os.Environment;

import com.gudigudigudi.mdtemplate.service.DownloadListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by gu on 10/14/17.
 */

public class DownloadTask extends AsyncTask {

    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCELED = 3;

    private DownloadListener listener;

    private boolean isCanceled = false;
    private boolean isPaused = false;

    private int lastProgress;

    public DownloadTask(DownloadListener listener) {
        this.listener = listener;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;

        try {
            long downloadLength = 0;
            String downloadUrl = (String) objects[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

            file = new File(directory + fileName);
            if (file.exists()) {
                downloadLength = file.length();
            }

            long contentLength = getContentLength(downloadUrl);
            if (contentLength == 0) {
                downloadLength = file.length();
            } else if (contentLength == downloadLength) {
                return TYPE_SUCCESS;
            }

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE", "bytes=" + downloadLength + "-")
                    .url(downloadUrl)
                    .build();

            Response response = client.newCall(request).execute();
            if (response != null) {

                is = response.body().byteStream();
                savedFile.seek(downloadLength);
                byte[] b = new byte[1024];
                int total = 0;
                int len;

                while ((len = is.read(b)) != -1) {
                    if (isCanceled) {
                        return TYPE_CANCELED;
                    } else if (isPaused) {
                        return TYPE_PAUSED;
                    } else {
                        total += len;
                        savedFile.write(b, 0, len);
                        int progress = (int) ((total + downloadLength) * 100 / contentLength);
                        publishProgress(progress);
                    }
                }
                response.close();
                return TYPE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TYPE_FAILED;
    }

    @Override
    protected void onPostExecute(Object o) {
        switch ((int) o) {
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_PAUSED:
                listener.onPaused();
                break;
            case TYPE_CANCELED:
                listener.onCanceled();
                break;
            default:
        }
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        int progress = (int) values[0];
        if (progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    private long getContentLength(String downloadUrl) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.body().close();
            return contentLength;
        }
        return 0;
    }
}
