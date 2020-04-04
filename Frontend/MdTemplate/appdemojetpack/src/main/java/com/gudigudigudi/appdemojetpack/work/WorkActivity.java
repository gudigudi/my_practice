package com.gudigudigudi.appdemojetpack.work;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.gudigudigudi.appdemojetpack.R;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkActivity extends AppCompatActivity {

    static final String TIME = "time";
    static final String DATA = "data";
    static final String ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        doTheWork();
    }

    private void doTheWork() {
        String TAG = "tag";

        OneTimeWorkRequest a = getRequest(TAG, 1000, "A", TestWorker.class);
        OneTimeWorkRequest b = getRequest(TAG, 2000, "B", TestWorker.class);
        OneTimeWorkRequest c = getRequest(TAG, 3000, "C", TestWorker.class);
        OneTimeWorkRequest d = getRequest(TAG, 4000, "D", TestWorker.class);

        WorkManager.getInstance(this)
                .enqueue(Arrays.asList(a, b, c, d));

        LiveData<List<WorkInfo>> liveData = WorkManager.getInstance(this).getWorkInfosByTagLiveData(TAG);
        liveData.observe(this, workInfos -> {
            log.info("长度:" + workInfos.size());

            for (WorkInfo info : workInfos) {
                if (info.getState() == WorkInfo.State.SUCCEEDED) {
                    Data data = info.getOutputData();
                    log.info("onChanged:" + data.getString(ID) + " " + data.getString(DATA) + " " + data.getLong(TIME, -1));
                }
            }
        });
    }

    private OneTimeWorkRequest getRequest(String tag, int time, String id, Class cls) {
        //传递参数数据
        Data data = new Data.Builder()
                .putInt(TIME, time)
                .putString(ID, id)
                .putString(DATA, "phil " + id)
                .build();

        Constraints constraints = new Constraints.Builder()
                .setRequiresStorageNotLow(false)
                .setRequiresBatteryNotLow(false)
                .setRequiresCharging(false)
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .build();

        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(cls)
                .setInputData(data)
                .setConstraints(constraints)
                .addTag(tag)
                .build();

        return request;
    }
}
