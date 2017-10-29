package com.gudigudigudi.mdtemplate.activity.materialdesign;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.util.LogUtil;
import com.orhanobut.logger.Logger;

public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private String single[] = {"Java", "C", "C++"};
    private String multi[] = {"android", "IOS", "WP"};

    private String mMultiChoises = "";
    private String mSingleChoise = single[0];

    private Button btn_toggle_single_choise_alertdialog;
    private Button btn_toggle_multi_choises_alertdialog;
    private Button btn_toggle_progress_dialog;
    private Button btn_toggle_loading_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);

        btn_toggle_single_choise_alertdialog = findViewById(R.id.btn_toggle_single_choise_alertdialog);
        btn_toggle_multi_choises_alertdialog = findViewById(R.id.btn_toggle_multi_choises_alertdialog);
        btn_toggle_progress_dialog = findViewById(R.id.btn_toggle_progress_dialog);
        btn_toggle_loading_dialog = findViewById(R.id.btn_toggle_loading_dialog);

        btn_toggle_single_choise_alertdialog.setOnClickListener(this);
        btn_toggle_multi_choises_alertdialog.setOnClickListener(this);
        btn_toggle_progress_dialog.setOnClickListener(this);
        btn_toggle_loading_dialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        AlertDialog dialog;
        switch (view.getId()) {
            case R.id.btn_toggle_single_choise_alertdialog:
                dialog = new AlertDialog.Builder(AlertDialogActivity.this)
                        .setTitle("Single choise")
                        .setSingleChoiceItems(single, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface anInterface, int i) {
                                mSingleChoise = single[i];
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface anInterface, int i) {
                                Toast.makeText(AlertDialogActivity.this, "You click " + mSingleChoise, Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                dialog.show();
                break;
            case R.id.btn_toggle_multi_choises_alertdialog:
                dialog = new AlertDialog.Builder(AlertDialogActivity.this)
                        .setTitle("Multi choises")
                        .setMultiChoiceItems(multi, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface anInterface, int i, boolean b) {
                                if (b) {
                                    mMultiChoises += multi[i] + ".";
                                }
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface anInterface, int i) {
                                if (!mMultiChoises.equals("")) {
                                    Toast.makeText(AlertDialogActivity.this, "You click " + mMultiChoises, Toast.LENGTH_SHORT).show();
                                    mMultiChoises = "";
                                } else {
                                    Toast.makeText(AlertDialogActivity.this, "You click nothing.", Toast.LENGTH_SHORT).show();
                                }

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface anInterface, int i) {
                                anInterface.dismiss();
                            }
                        }).create();
                dialog.show();
                break;
            case R.id.btn_toggle_progress_dialog:
                View progressView = LayoutInflater.from(this).inflate(R.layout.progress_dialog, null);
                ProgressBar progressBar = (ProgressBar) progressView.findViewById(R.id.progressBar);
                TextView tv_percentage = (TextView) progressView.findViewById(R.id.percentage);
                TextView tv_percentage2 = (TextView) progressView.findViewById(R.id.percentage2);

                tv_percentage.setText(progressBar.getProgress() + "%");
                tv_percentage2.setText(progressBar.getProgress() + "/" + progressBar.getMax());

                dialog = new AlertDialog.Builder(AlertDialogActivity.this)
                        .setTitle("Progress dialog")
                        .setView(progressView)
                        .create();
                dialog.show();
                break;
            case R.id.btn_toggle_loading_dialog:
                View loadingView = LayoutInflater.from(this).inflate(R.layout.loading_dialog, null);
                TextView tv_loading_tip = loadingView.findViewById(R.id.loading_tip);
                ImageView iv_loading = loadingView.findViewById(R.id.loading_image);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.loading);

                iv_loading.setVisibility(View.VISIBLE);
                iv_loading.setAnimation(animation);
                tv_loading_tip.setText("正在加载中");

                dialog = new AlertDialog.Builder(AlertDialogActivity.this)
                        .setView(loadingView)
                        .create();
                dialog.show();
            default:
                Logger.d(LogUtil.LOG_UNKNOWN_VIEW_IS_CLICKED);
        }
    }
}
