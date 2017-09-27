package com.gudigudigudi.mdtemplate;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

public class FragmentDemoActivity extends BaseActivity implements LeftFragment.OnFragmentInteractionListener, RightFragment.OnFragmentInteractionListener, Right2Fragment.OnFragmentInteractionListener {

    private final static String TAG = "FragmentDemoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new Right2Fragment());
            }
        });

        replaceFragment(new RightFragment());
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void replaceFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.right_fragment_layout, fragment);
//        // Enter back key, and go back to last fragment.
//        transaction.addToBackStack(null);
//
//        transaction.commit();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.right_fragment_layout, fragment)
                .addToBackStack(null)
                .commit();
    }
}
