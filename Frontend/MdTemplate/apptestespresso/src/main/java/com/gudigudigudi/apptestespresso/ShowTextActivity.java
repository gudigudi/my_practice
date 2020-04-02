package com.gudigudigudi.apptestespresso;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.gudigudigudi.apptestespresso.databinding.ActivityShowTextBinding;
import com.gudigudigudi.commonlib.base.BaseActivity;

public class ShowTextActivity extends BaseActivity {

    public final static String KEY_EXTRA_MESSAGE =
            "com.example.android.testing.espresso.basicsample.MESSAGE";
    private ActivityShowTextBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowTextBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.txtvShow.setText(getIntent().getStringExtra(KEY_EXTRA_MESSAGE));
    }

    /**
     * Creates an {@link Intent} for {@link ShowTextActivity} with the message to be displayed.
     *
     * @param context the {@link Context} where the {@link Intent} will be used
     * @param message a {@link String} with text to be displayed
     * @return an {@link Intent} used to start {@link ShowTextActivity}
     */
    static protected Intent newStartIntent(Context context, String message) {
        Intent newIntent = new Intent(context, ShowTextActivity.class);
        newIntent.putExtra(KEY_EXTRA_MESSAGE, message);
        return newIntent;
    }
}
