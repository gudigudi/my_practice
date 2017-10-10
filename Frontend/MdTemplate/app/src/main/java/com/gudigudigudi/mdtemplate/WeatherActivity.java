package com.gudigudigudi.mdtemplate;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class WeatherActivity extends AppCompatActivity implements ChooseAreaFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
