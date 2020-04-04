package com.gudigudigudi.appdemojetpack.dagger2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.gudigudigudi.appdemojetpack.databinding.ActivityDaggerBinding;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity {

    @Inject
    int testValue;

    private ActivityDaggerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDaggerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ZhaiNan zhaiNan = DaggerWaimaiPingTai.builder()
                .shangjiaAModule(new ShangjiaAModule("王小二包子店"))
                .build().waimai();

        ZhaiNan zhaiNan2 = new ZhaiNan();
        WaimaiPingTai waimaiPingTai = DaggerWaimaiPingTai.builder()
                .shangjiaAModule(new ShangjiaAModule("沙县小吃"))
                .build();

        waimaiPingTai.zhuru(zhaiNan2);

        waimaiPingTai.inject(this);

        binding.btn.setOnClickListener(v -> ToastUtils.showShort(zhaiNan.eat()));
        binding.btn2.setOnClickListener(v -> ToastUtils.showShort(zhaiNan2.eat()));
        binding.btn3.setOnClickListener(v -> ToastUtils.showShort("testValue is " + testValue));
    }
}
