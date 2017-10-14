package com.gudigudigudi.mdtemplate.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.fragment.NewsTitleFragment;

public class NewsTitleActivity extends AppCompatActivity implements NewsTitleFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_title);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
