package com.gudigudigudi.mdtemplate;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ContentProviderActivity extends AppCompatActivity {

    private static final String TAG = "ContentProviderActivity";

    private Button btn_make_call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        btn_make_call = (Button) findViewById(R.id.make_call);

        btn_make_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(ContentProviderActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ContentProviderActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    make_call();
                }
            }
        });
    }

    private void make_call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL); // call phone.
            intent.setData(Uri.parse("tel:13967704302"));
            startActivity(intent);
        } catch (SecurityException e) {
            Log.d(TAG, e.toString());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    make_call();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
//                    ActivityCompat.requestPermissions(ContentProviderActivity.this,
//                            new String[]{Manifest.permission.CALL_PHONE}, 1); // request permission again.
                }
                break;
            default:
                Log.d(TAG, "onRequestPermissionsResult: " + "Error");
        }
    }
}
