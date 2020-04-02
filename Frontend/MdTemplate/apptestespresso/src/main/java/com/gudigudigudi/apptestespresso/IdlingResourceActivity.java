package com.gudigudigudi.apptestespresso;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.IdlingResource;

import com.gudigudigudi.apptestespresso.databinding.ActivityMainEspressoBinding;

public class IdlingResourceActivity extends AppCompatActivity implements MessageDelayer.DelayerCallback {

    private ActivityMainEspressoBinding binding;

    @Nullable
    private SimpleIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainEspressoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnChangeText.setOnClickListener(v -> {
            binding.txtvToBeChanged.setText(R.string.app_name);
            MessageDelayer.processMessage(binding.edittUserInput.getText().toString(), this, mIdlingResource);
        });
    }

    @Override
    public void onDone(String text) {
        binding.txtvToBeChanged.setText(text);
    }

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }
}
