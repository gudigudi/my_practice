package com.gudigudigudi.mdtemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

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
        Button btn = (Button) findViewById(R.id.button1);
        btn.setText(stringFromJNI());
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
}
