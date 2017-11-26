package com.gudigudigudi.mdtemplate.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gudigudigudi.mdtemplate.R;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class PermissionActivity extends AppCompatActivity {

    private Button mBtnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        mBtnCall = findViewById(R.id.btn_call);

        mBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionActivityPermissionsDispatcher.callWithCheck(PermissionActivity.this);
            }
        });
    }

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:10086");
        intent.setData(data);

        try {
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @OnShowRationale(Manifest.permission.CALL_PHONE)
    void showNeedPermissionReason(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("为何开启次权限")
                .setPositiveButton("同意", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface anInterface, int i) {
                        request.proceed();
                    }
                })
                .setNegativeButton("不同意", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface anInterface, int i) {
                        request.cancel();
                    }
                })
                .setCancelable(true)
                .show();
    }

    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    void showNeedPermissionDenied() {
        new AlertDialog.Builder(this)
                .setMessage("不开启将无法工作")
                .show();
    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void showNeedPermissionNotAsk() {
        new AlertDialog.Builder(this)
                .setMessage("如果需要开启,请在系统设置中开启应用的权限")
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface anInterface, int i) {

                    }
                })
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
