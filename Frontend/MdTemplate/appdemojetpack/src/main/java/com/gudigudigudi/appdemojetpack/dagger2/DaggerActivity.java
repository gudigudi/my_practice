package com.gudigudigudi.appdemojetpack.dagger2;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.gudigudigudi.appdemojetpack.R;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity {

    @Inject
    int testValue;

    Button mButton, mButton2, mButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

        mButton = findViewById(R.id.btn);
        mButton2 = findViewById(R.id.btn2);
        mButton3 = findViewById(R.id.btn3);

        ZhaiNan zhaiNan = DaggerWaimaiPingTai.builder()
                .shangjiaAModule(new ShangjiaAModule("王小二包子店"))
                .build().waimai();

        ZhaiNan zhaiNan2 = new ZhaiNan();
        WaimaiPingTai waimaiPingTai = DaggerWaimaiPingTai.builder()
                .shangjiaAModule(new ShangjiaAModule("沙县小吃"))
                .build();

        waimaiPingTai.zhuru(zhaiNan2);

        waimaiPingTai.inject(this);

        mButton.setOnClickListener(v -> ToastUtils.showShort(zhaiNan.eat()));
        mButton2.setOnClickListener(v -> ToastUtils.showShort(zhaiNan2.eat()));
        mButton3.setOnClickListener(v -> ToastUtils.showShort("testValue is " + testValue));
    }
}
