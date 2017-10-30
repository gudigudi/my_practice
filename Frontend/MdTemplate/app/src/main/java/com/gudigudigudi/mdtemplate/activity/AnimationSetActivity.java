package com.gudigudigudi.mdtemplate.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.gudigudigudi.mdtemplate.R;

public class AnimationSetActivity extends AppCompatActivity {

    private ImageView iv_cart, iv_shirt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_set);

        iv_cart = findViewById(R.id.iv_cart);
        iv_shirt = findViewById(R.id.iv_shirt);

        iv_shirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationSet animationSet = (AnimationSet) AnimationUtils.loadAnimation(AnimationSetActivity.this, R.anim.animation_set);
                animationSet.setFillAfter(true);
                animationSet.setInterpolator(new AccelerateInterpolator());
                iv_shirt.startAnimation(animationSet);
            }
        });
    }
}
