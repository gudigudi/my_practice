package com.gudigudigudi.mdtemplate.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.blankj.utilcode.util.NetworkUtils;
import com.gudigudigudi.commonlib.base.BaseFragment;
import com.gudigudigudi.mdtemplate.R;

public class BroadcastFragment extends BaseFragment {

    private static final String TAG = "BroadcastFragment";

    private Button button;
    private Button button2;

    private IntentFilter mIntentFilter;
    private NetworkChangeReceiver mNetworkChangeReceiver;

    private IntentFilter mIntentFilterLocal;
    private LocalReceiver mLocalReceiver;
    private LocalBroadcastManager mLocalBroadcastManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_broadcast, container, false);

        button = view.findViewById(R.id.replace);
        button2 = view.findViewById(R.id.button2);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mNetworkChangeReceiver = new NetworkChangeReceiver();
        getActivity().registerReceiver(mNetworkChangeReceiver, mIntentFilter);

        button.setOnClickListener(view1 -> {
            String intentStr = getResources().getString(R.string.CUSTOM_BROADCAST);
            Log.d(TAG, "send broadcast with intent " + intentStr);
            Intent intent = new Intent(intentStr);
            getActivity().sendBroadcast(intent);
        });

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        mIntentFilterLocal = new IntentFilter();
        mIntentFilterLocal.addAction(getString(R.string.LOCAL_BROADCAST));
        mLocalReceiver = new LocalReceiver();
        mLocalBroadcastManager.registerReceiver(mLocalReceiver, mIntentFilterLocal);

        button2.setOnClickListener(view12 -> {
            Intent intent = new Intent(getString(R.string.LOCAL_BROADCAST));
            // send local broadcast.
            mLocalBroadcastManager.sendBroadcast(intent);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getActivity().unregisterReceiver(mNetworkChangeReceiver);
        mLocalBroadcastManager.unregisterReceiver(mLocalReceiver);
    }

    /**
     * 网络检测广播接收器
     */
    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (NetworkUtils.isConnected()) {
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 本地广播接收器
     */
    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
        }
    }
}
