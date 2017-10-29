package com.gudigudigudi.mdtemplate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.base.BaseActivity;

public class SecondActivity extends BaseActivity {

    private final static String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button button = findViewById(R.id.replace);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("extra_data", "FOR FirstActivity: result of SecondActivity, back pressed.");
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
