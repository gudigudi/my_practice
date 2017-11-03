package com.gudigudigudi.mdtemplate;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gudigudigudi.mdtemplate.view.AnimationLoadingDialog;

public class LoadingAnimationActivity extends AppCompatActivity {

    private Button button;
    private AnimationLoadingDialog animationLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_animation);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationLoadingDialog = new AnimationLoadingDialog(LoadingAnimationActivity.this, "loading...", R.drawable.frame_animation);
                animationLoadingDialog.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animationLoadingDialog.dismiss();
                    }
                }, 3000);
            }
        });
    }
}
