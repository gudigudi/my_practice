package com.gudigudigudi.mdtemplate;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Button btn1;
    private Button btn_goto_firstactivity;
    private EditText editText;
    private Button btn_toast_edittext;
    private ImageView imageView;
    private Button btn_change_imageview;
    private Button btn_toggle_alertdialog;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            String tmpData = savedInstanceState.getString("data_key");
            Log.d(TAG, "tmpData : " + tmpData);
        }

        // Example of a call to a native method
        btn1 = (Button) findViewById(R.id.button1);
        btn1.setText(stringFromJNI());

        btn_goto_firstactivity = (Button) findViewById(R.id.button_goto_firstactivity);

        editText = (EditText) findViewById(R.id.edit_text);
        btn_toast_edittext = (Button) findViewById(R.id.btn_toast_edittext);

        imageView = (ImageView) findViewById(R.id.image_view);
        btn_change_imageview = (Button) findViewById(R.id.btn_change_imageview);

        btn_toggle_alertdialog = (Button) findViewById(R.id.btn_toggle_alertdialog);

        btn1.setOnClickListener(this);
        btn_goto_firstactivity.setOnClickListener(this);
        btn_toast_edittext.setOnClickListener(this);
        btn_change_imageview.setOnClickListener(this);
        btn_toggle_alertdialog.setOnClickListener(this);

    }

    /**
     * 避免activity被回收后, 数据无法保存.
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tmpData = "Something you just typed.";
        Log.d(TAG, "onSaveInstanceState: " + tmpData);
        outState.putString("data_key", tmpData);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native void nativeInit();

    public native void nativeFree();

    /**
     * 活动全局点击事件监听
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick");

        switch (view.getId()) {
            case R.id.button1:
                ((Button) view).setText(stringFromJNI());
                break;
            case R.id.button_goto_firstactivity:
                Log.d(TAG, "click button and goto firstactivity");
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_toast_edittext:
                String inputText = editText.getText().toString();
                if (!inputText.equals("")) {
                    Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_change_imageview:
                Drawable ic_launcher = ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_launcher, null);
                Drawable ic_launcher_round = ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_launcher_round, null);
                if (imageView.getDrawable().getConstantState().equals(ic_launcher.getConstantState())) {
                    imageView.setImageResource(R.mipmap.ic_launcher_round);
                } else if (imageView.getDrawable().getConstantState().equals(ic_launcher_round.getConstantState())) {
                    imageView.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Log.d(TAG, "unknown imageview id");
                }
                break;
            case R.id.btn_toggle_alertdialog:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("A alert dialog")
                        .setMessage("Something important to show")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface anInterface, int i) {

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface anInterface, int i) {

                            }
                        })
                        .show();


                break;
            default:
                Log.d(TAG, "unknown view clicked");
        }
    }
}
