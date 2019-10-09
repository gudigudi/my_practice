package com.gudigudigudi.mdtemplate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.gudigudigudi.commonlib.base.BaseActivity;
import com.gudigudigudi.mdtemplate.R;


public class FruitContentActivity extends BaseActivity {

    public static final String FRUIT_NAME = "fruit_name";
    public static final String FRUIT_IMAGE_ID = "fruit_image_id";

    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView mFruitImageView;
    private TextView mFruitContentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_content);

        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        mFruitImageView = findViewById(R.id.fruit_image_view);
        mFruitContentText = findViewById(R.id.fruit_content_text);

        mCollapsingToolbarLayout.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(mFruitImageView);
        mFruitContentText.setText(generateFruitContent(fruitName));
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
