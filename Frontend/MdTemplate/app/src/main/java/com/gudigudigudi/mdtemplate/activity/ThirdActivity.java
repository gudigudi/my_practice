package com.gudigudigudi.mdtemplate.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gudigudigudi.mdtemplate.ActivityCollector;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.base.BaseActivity;

public class ThirdActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Button btn = findViewById(R.id.button_3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCollector.finishAll();
            }
        });
    }
}
