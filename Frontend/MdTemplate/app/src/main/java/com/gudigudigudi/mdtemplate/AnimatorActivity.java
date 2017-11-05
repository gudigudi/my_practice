package com.gudigudigudi.mdtemplate;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AnimatorActivity extends AppCompatActivity {

    private Button button;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.image_view);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TranslateAnimation translateAnimation = new TranslateAnimation(0, 200, 0, 0);
//                translateAnimation.setDuration(2000);
//                translateAnimation.setFillAfter(true);
//                imageView.startAnimation(translateAnimation);
                ObjectAnimator.ofFloat(imageView, "translationX", 0, 200)
                        .setDuration(2000)
                        .start();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AnimatorActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
