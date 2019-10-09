package com.gudigudigudi.commonlib.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * App启动白屏优化，使用SplashActivity示例
 *
 * <activity android:name=".SplashActivity"
 *           android:theme="@style/SplashTheme">
 *     <intent-filter>
 *         <action android:name="android.intent.action.MAIN"/>
 *         <category android:name="android.intent.category.LAUNCHER"/>
 *     </intent-filter>
 * </activity>
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this,SplashActivity.class));

        finish();
    }
}
