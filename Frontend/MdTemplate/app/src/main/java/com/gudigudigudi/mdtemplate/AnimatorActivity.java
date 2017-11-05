package com.gudigudigudi.mdtemplate;

import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
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
//                ObjectAnimator.ofFloat(imageView, "translationX", 0, 200)
//                        .setDuration(2000)
//                        .start();
//                ObjectAnimator.ofFloat(imageView,"rotationX",0,360)
//                        .setDuration(2000)
//                        .start();
//                ObjectAnimator.ofFloat(imageView,"rotationY",0,360)
//                        .setDuration(2000)
//                        .start();
//                ObjectAnimator.ofFloat(imageView,"scaleX",1,0.5f)
//                        .setDuration(2000)
//                        .start();
//                ObjectAnimator.ofFloat(imageView,"scaleY",1,0.5f)
//                        .setDuration(2000)
//                        .start();
//                ObjectAnimator.ofFloat(imageView,"alpha",1,0.5f)
//                        .setDuration(2000)
//                        .start();
                ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(AnimatorActivity.this, R.animator.background);
                objectAnimator.setEvaluator(new ArgbEvaluator());
                objectAnimator.setTarget(imageView);
                objectAnimator.start();
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
