package com.gudigudigudi.mdtemplate;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private final static String TAG="SecondActivity";
//    private final static int RESULT_OK=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // get intent data from FirstActivity.
        Intent intent=getIntent();
        String data=intent.getStringExtra("extra_data");
        Log.d(TAG, data);

        Button button= findViewById(R.id.button_2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(SecondActivity.this,FirstActivity.class);
//               Intent intent=new Intent("com.gudigudigudi.mdtemplate.ACTION_START");
//                Intent intent=new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://www.baidu.com"));
//                startActivity(intent);
                Intent intent=new Intent();
                intent.putExtra("extra_data","FOR FirstActivity: result of SecondActivity");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent=new Intent();
        intent.putExtra("extra_data","FOR FirstActivity: result of SecondActivity, back pressed.");
        setResult(RESULT_OK,intent);
        finish();
    }
}
