package com.gudigudigudi.mdtemplate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;

import com.bumptech.glide.Glide;
import com.gudigudigudi.commonlib.base.BaseActivity;
import com.gudigudigudi.mdtemplate.databinding.ActivityFruitContentBinding;


public class FruitContentActivity extends BaseActivity {

    public static final String FRUIT_NAME = "fruit_name";
    public static final String FRUIT_IMAGE_ID = "fruit_image_id";

    private ActivityFruitContentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFruitContentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);

        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        binding.collapsingToolbar.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(binding.fruitImageView);
        binding.fruitContentText.setText(generateFruitContent(fruitName));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private String generateFruitContent(String fruitName) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            builder.append(fruitName);
        }
        return builder.toString();
    }
}
