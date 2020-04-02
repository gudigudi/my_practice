package com.gudigudigudi.apptestespresso;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

import com.gudigudigudi.apptestespresso.databinding.ActivityImageViewerBinding;
import com.gudigudigudi.commonlib.base.BaseActivity;

public class ImageViewerActivity extends BaseActivity {

    private ActivityImageViewerBinding binding;

    @VisibleForTesting
    protected static final String KEY_IMAGE_DATA = "data";

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras == null || !extras.containsKey(KEY_IMAGE_DATA)) {
                return;
            }
            Bitmap bitmap = (Bitmap) extras.get(KEY_IMAGE_DATA);
            binding.imageView.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onOpenCamera(View view) {
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
