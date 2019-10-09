package com.gudigudigudi.appdemojetpack.work;

import android.content.Context;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestWorker extends Worker {

    private String id;
    private int time;

    public TestWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        Data data = workerParams.getInputData();
        time = data.getInt(WorkActivity.TIME, -1);
        id = data.getString(WorkActivity.ID);
        String content = data.getString(WorkActivity.DATA);

        log.info("Worker: 传入数据->id: " + id + " time:" + time, " data:" + content);
    }

    @NonNull
    @Override
    public Result doWork() {
        // 模拟耗时操作
        SystemClock.sleep(time);

        log.info("doWork:" + id + "-线程id:" + Thread.currentThread().getId() + " finished.");
        //传输数据给外部监听者
        Data data = new Data.Builder()
                .putString(WorkActivity.ID, id)
                .putString(WorkActivity.DATA, "data backhaul")
                .putLong(WorkActivity.TIME, System.currentTimeMillis())
                .build();

        return Result.success(data);
    }
}
