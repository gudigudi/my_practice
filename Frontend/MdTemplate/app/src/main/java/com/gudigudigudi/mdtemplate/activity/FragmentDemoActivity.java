package com.gudigudigudi.mdtemplate.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.base.BaseActivity;
import com.gudigudigudi.mdtemplate.fragment.LeftFragment;
import com.gudigudigudi.mdtemplate.fragment.Right2Fragment;
import com.gudigudigudi.mdtemplate.fragment.RightFragment;
import com.gudigudigudi.mdtemplate.util.LogUtil;
import com.orhanobut.logger.Logger;

public class FragmentDemoActivity extends BaseActivity implements View.OnClickListener,
        LeftFragment.OnFragmentInteractionListener, RightFragment.OnFragmentInteractionListener,
        Right2Fragment.OnFragmentInteractionListener {

    private final static String TAG = "FragmentDemoActivity";

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);

        // Button in LeftFragment.
        Button btn_replace = (Button) findViewById(R.id.replace);
        Button btn_remove = (Button) findViewById(R.id.remove);
        Button btn_hide = (Button) findViewById(R.id.hide);

        fragment = new Right2Fragment();

        btn_replace.setOnClickListener(this);
        btn_remove.setOnClickListener(this);
        btn_hide.setOnClickListener(this);

        replaceFragment(new RightFragment());
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.replace:
                replaceFragment(fragment);
                break;
            case R.id.remove:
                removeFragment(fragment);
                break;
            case R.id.hide:
                hideFragment(fragment);
                break;
            default:
                Logger.d(LogUtil.LOG_UNKNOWN_VIEW_IS_CLICKED);
        }
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.right_fragment_layout, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void removeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .remove(fragment)
                .commit();
    }

    private void hideFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .hide(fragment)
                .commit();
    }
}