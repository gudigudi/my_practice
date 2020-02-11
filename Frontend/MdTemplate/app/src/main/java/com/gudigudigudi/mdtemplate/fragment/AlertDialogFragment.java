package com.gudigudigudi.mdtemplate.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.blankj.utilcode.util.ToastUtils;
import com.gudigudigudi.commonlib.base.BaseFragment;
import com.gudigudigudi.commonlib.constants.LogTag;
import com.gudigudigudi.mdtemplate.R;

import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlertDialogFragment extends BaseFragment implements View.OnClickListener {

    private String[] single = {"Java", "C", "C++"};
    private String[] multi = {"android", "IOS", "WP"};

    private String mMultiChoises = "";
    private String mSingleChoise = single[0];

    private Button mBtnToggleSingleChoiseAlertdialog;
    private Button mBtnToggleMultiChoisesAlertdialog;
    private Button mBtnToggleProgressDialog;
    private Button mBtnToggleLoadingDialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alert_dialog, container, false);

        mBtnToggleSingleChoiseAlertdialog = view.findViewById(R.id.btn_toggle_single_choise_alertdialog);
        mBtnToggleMultiChoisesAlertdialog = view.findViewById(R.id.btn_toggle_multi_choises_alertdialog);
        mBtnToggleProgressDialog = view.findViewById(R.id.btn_toggle_progress_dialog);
        mBtnToggleLoadingDialog = view.findViewById(R.id.btn_toggle_loading_dialog);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBtnToggleSingleChoiseAlertdialog.setOnClickListener(this);
        mBtnToggleMultiChoisesAlertdialog.setOnClickListener(this);
        mBtnToggleProgressDialog.setOnClickListener(this);
        mBtnToggleLoadingDialog.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        AlertDialog dialog;
        switch (view.getId()) {
            case R.id.btn_toggle_single_choise_alertdialog:
                dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                        .setTitle("Single choise")
                        .setSingleChoiceItems(single, 0, (anInterface, i) -> mSingleChoise = single[i])
                        .setPositiveButton("Yes", (anInterface, i) -> ToastUtils.showShort("You click " + mSingleChoise)).create();
                dialog.show();
                break;
            case R.id.btn_toggle_multi_choises_alertdialog:
                dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("Multi choises")
                        .setMultiChoiceItems(multi, null, (anInterface, i, b) -> {
                            if (b) {
                                mMultiChoises += multi[i] + ".";
                            }
                        })
                        .setPositiveButton("Yes", (anInterface, i) -> {
                            if (!"".equals(mMultiChoises)) {
                                ToastUtils.showShort("You click " + mMultiChoises);
                                mMultiChoises = "";
                            } else {
                                ToastUtils.showShort("You click nothing.");
                            }

                        })
                        .setNegativeButton("No", (anInterface, i) -> anInterface.dismiss()).create();
                dialog.show();
                break;
            case R.id.btn_toggle_progress_dialog:
                View progressView = LayoutInflater.from(getContext()).inflate(R.layout.progress_dialog, null);
                ProgressBar progressBar = progressView.findViewById(R.id.progressBar);
                TextView tvPercentage = progressView.findViewById(R.id.percentage);
                TextView tvPercentage2 = progressView.findViewById(R.id.percentage2);

                tvPercentage.setText(progressBar.getProgress() + "%");
                tvPercentage2.setText(progressBar.getProgress() + "/" + progressBar.getMax());

                dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                        .setTitle("Progress dialog")
                        .setView(progressView)
                        .create();
                dialog.show();
                break;
            case R.id.btn_toggle_loading_dialog:
                View loadingView = LayoutInflater.from(getContext()).inflate(R.layout.loading_dialog, null);
                TextView tvLoadingTip = loadingView.findViewById(R.id.loading_tip);
                ImageView ivLoading = loadingView.findViewById(R.id.loading_image);
                Animation animation = AnimationUtils.loadAnimation(Objects.requireNonNull(getActivity()).getApplicationContext(), R.anim.loading);

                ivLoading.setVisibility(View.VISIBLE);
                ivLoading.setAnimation(animation);
                tvLoadingTip.setText("正在加载中");

                dialog = new AlertDialog.Builder(getActivity())
                        .setView(loadingView)
                        .create();
                dialog.show();
                break;
            default:
                log.debug(LogTag.LOG_UNKNOWN_VIEW_IS_CLICKED);
        }
    }
}
