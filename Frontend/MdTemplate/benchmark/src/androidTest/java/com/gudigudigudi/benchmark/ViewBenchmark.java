package com.gudigudigudi.benchmark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.benchmark.BenchmarkState;
import androidx.benchmark.junit4.BenchmarkRule;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.benchmark.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ViewBenchmark {

    @Rule
    public BenchmarkRule mBenchmarkRule = new BenchmarkRule();

    @Test
    public void BenchmarkSimpleViewInflate() {
        Context context = ApplicationProvider.getApplicationContext();
        final BenchmarkState state = mBenchmarkRule.getState();
        LayoutInflater inflater = LayoutInflater.from(context);
        FrameLayout root = new FrameLayout(context);

        while (state.keepRunning()) {
            inflater.inflate(R.layout.activity_main, root, false);
        }
    }

    @Test
    public void BenchmarkBitmapProcessing() {
        final BenchmarkState state = mBenchmarkRule.getState();

        while (state.keepRunning()) {
            state.pauseTiming();

            Bitmap bitmap = constructTestBitmap();

            state.resumeTiming();

            processBitmap(bitmap);
        }
    }

    private void processBitmap(Bitmap bitmap) {
    }

    private Bitmap constructTestBitmap() {
        return BitmapFactory.decodeResource(ApplicationProvider.getApplicationContext().getResources(), R.drawable.apple);
    }
}
