package com.gudigudigudi.mdtemplate.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.gudigudigudi.mdtemplate.R;

public class TransitionActivity extends AppCompatActivity {

    private Scene scene1, scene2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        RelativeLayout sceneRoot = findViewById(R.id.scene_container);
        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_transition, this);
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_transition2, this);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transition transition_fade = TransitionInflater.from(TransitionActivity.this).inflateTransition(R.transition.fade);
                TransitionManager.go(scene2, transition_fade);
            }
        });
    }
}
