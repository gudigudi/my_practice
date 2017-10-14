package com.gudigudigudi.mdtemplate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.base.BaseActivity;

public class FirstActivity extends BaseActivity {

    private final static String TAG = "FirstActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Log.d(TAG, this.toString());
        Log.d(TAG, "This task id is " + getTaskId());

        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(FirstActivity.this,"You click Button 1",Toast.LENGTH_SHORT).show();
//                finish();
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                Intent intent = new Intent(FirstActivity.this, FirstActivity.class);

//                String data = "Hello, world!";
//                intent.putExtra("extra_data", data);
//
//                Intent intent=new Intent("com.gudigudigudi.mdtemplate.ACTION_START");
//                Intent intent=new Intent(FirstActivity.this,ThirdActivity.class);
//                Intent intent=new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:13967704302"));

                startActivity(intent);
//                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = ""; //=data.getStringExtra("extra_data");
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        returnedData = bundle.getString("extra_data");
                    } else {
                        Log.d(TAG, "intent bundle is NULL!");
                    }
                    Log.d(TAG, returnedData);
                } else {
                    Log.d(TAG, "resultCode is " + String.valueOf(resultCode));
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.manu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(FirstActivity.this, "You click Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(FirstActivity.this, "You click Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
                Log.i(TAG, "Unknown menu item is selected");
        }
        return true;
    }
}
