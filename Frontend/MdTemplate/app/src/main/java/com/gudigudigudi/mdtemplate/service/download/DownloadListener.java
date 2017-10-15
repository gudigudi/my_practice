package com.gudigudigudi.mdtemplate.service.download;

/**
 * Created by gu on 10/14/17.
 */

public interface DownloadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
