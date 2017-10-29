package com.gudigudigudi.mdtemplate.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.gudigudigudi.mdtemplate.R;

public class AnimationActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button btn_start, btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        imageView = findViewById(R.id.image_view);
        btn_start = findViewById(R.id.btn_start);
        btn_cancel = findViewById(R.id.btn_cancel);

        imageView.setBackgroundResource(R.mipmap.ic_launcher);

        final AlphaAnimation animation = new AlphaAnimation(1, 0);
        // 设置动画持续时间
        animation.setDuration(2000);
        // 设置重复次数
        animation.setRepeatCount(3);
        // 执行前的等待时间
        animation.setStartOffset(1000);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.startAnimation(animation);
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation.cancel();
            }
        });
    }
}
