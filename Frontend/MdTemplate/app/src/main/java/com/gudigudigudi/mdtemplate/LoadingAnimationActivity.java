package com.gudigudigudi.mdtemplate;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gudigudigudi.mdtemplate.view.AnimationLoadingDialog;

public class LoadingAnimationActivity extends AppCompatActivity {

    private Button mButton;
    private AnimationLoadingDialog mAnimationLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_animation);

        mButton = findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnimationLoadingDialog = new AnimationLoadingDialog(LoadingAnimationActivity.this, "loading...", R.drawable.frame_animation);
                mAnimationLoadingDialog.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAnimationLoadingDialog.dismiss();
                    }
                }, 3000);
            }
        });
    }
}
