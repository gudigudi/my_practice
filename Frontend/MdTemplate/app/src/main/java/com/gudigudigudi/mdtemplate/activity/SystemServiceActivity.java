package com.gudigudigudi.mdtemplate.activity;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.UriUtils;
import com.gudigudigudi.commonlib.base.BaseActivity;
import com.gudigudigudi.commonlib.constants.LogTag;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.databinding.ActivitySystemServiceBinding;

import java.io.File;
import java.io.FileNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SystemServiceActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_TAKE_PHOTO = 1;
    private static final int REQUEST_CODE_CHOOSE_PHOTO = 2;
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 3;

    private Uri mImageUri;

    private MediaPlayer mMediaPlayer = new MediaPlayer();

    private ActivitySystemServiceBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySystemServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSendNotification.setOnClickListener(this);
        binding.btnSendHeadsupNotification.setOnClickListener(this);
        binding.btnSendFoldNotification.setOnClickListener(this);

        binding.btnTakePhoto.setOnClickListener(this);
        binding.btnChooseFromAlbum.setOnClickListener(this);

        binding.btnPlay.setOnClickListener(this);
        binding.btnPause.setOnClickListener(this);
        binding.btnStop.setOnClickListener(this);

        binding.btnPlayVideo.setOnClickListener(this);
        binding.btnPauseVideo.setOnClickListener(this);
        binding.btnReplayVideo.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
        } else {
            initMediaPlayer();
            initVideoView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
        if (binding.videoView != null) {
            binding.videoView.suspend();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(mImageUri));
                        binding.imgvPicture.setImageBitmap(Bitmap.createScaledBitmap(bitmap, binding.imgvPicture.getWidth(), binding.imgvPicture.getWidth() * bitmap.getHeight() / bitmap.getWidth(), false));
                    } catch (FileNotFoundException e) {
                        log.error(e.getLocalizedMessage());
                    }
                }
                break;
            case REQUEST_CODE_CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    handleImage(data);
                }
                break;
            default:
                log.debug(LogTag.LOG_UNKNOWN_REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CHOOSE_PHOTO:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    ToastUtils.showShort("You denied the permission");
                }
                break;
            case REQUEST_CODE_WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlayer();
                    initVideoView();
                } else {
                    ToastUtils.showShort("You denied the permission");
                    finish();
                }
                break;
            default:
                log.debug(LogTag.LOG_UNKNOWN_REQUEST_CODE);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        PendingIntent pi = null;
        Notification notification = null;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        switch (view.getId()) {
            case R.id.btn_send_notification:
                intent = new Intent(SystemServiceActivity.this, MainActivity.class);
                pi = PendingIntent.getActivity(SystemServiceActivity.this, 0, intent, 0);
                notification = new NotificationCompat.Builder(getApplicationContext(), null)
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .setContentTitle("This is content title")
                        .setContentText("This is content text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.ic_arrow_back)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setVibrate(new long[]{0, 1000, 1000, 1000})
                        .setLights(Color.GREEN, 1000, 1000)
                        .build();
                break;
            case R.id.btn_send_headsup_notification:
                notification = new NotificationCompat.Builder(getApplicationContext(), null)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .setSmallIcon(R.drawable.ic_create)
                        .addAction(R.drawable.ic_arrow_back, "Previous", pi)
                        .addAction(R.drawable.ic_arrow_back, "Pause", pi)
                        .addAction(R.drawable.ic_arrow_back, "Next", pi)
                        .setContentTitle("Heads-up notice")
                        .setContentText("Heads Up!")
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.apple))
                        .build();
                break;
            case R.id.btn_send_fold_notification:
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_fold);

                notification = new NotificationCompat.Builder(getApplicationContext(), null)
                        .setSmallIcon(R.drawable.ic_create)
                        .setContentTitle("Fold notice")
                        .setCustomContentView(remoteViews)
                        .setCustomBigContentView(remoteViews)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.apple))
                        .build();
                break;
            case R.id.btn_take_photo:
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                FileUtils.createFileByDeleteOldFile(outputImage);
                mImageUri = UriUtils.file2Uri(outputImage);
                // start camera.
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
                startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
                break;
            case R.id.btn_choose_from_album:
                if (ContextCompat.checkSelfPermission(SystemServiceActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SystemServiceActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_CHOOSE_PHOTO);
                } else {
                    openAlbum();
                }

                break;
            case R.id.btn_play:
                if (!mMediaPlayer.isPlaying()) {
                    mMediaPlayer.start();
                }
                break;
            case R.id.btn_pause:
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                }
                break;
            case R.id.stop:
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                    initMediaPlayer();
                }
                break;
            case R.id.btn_play_video:
                if (!binding.videoView.isPlaying()) {
                    binding.videoView.start();
                }
                break;
            case R.id.btn_pause_video:
                if (binding.videoView.isPlaying()) {
                    binding.videoView.pause();
                }
                break;
            case R.id.btn_replay_video:
                if (binding.videoView.isPlaying()) {
                    binding.videoView.resume();
                }
                break;
            default:
                log.debug(LogTag.LOG_UNKNOWN_VIEW_IS_CLICKED);
        }

        if (notification != null) {
            notificationManager.notify(1, notification);
        }
    }

    private void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_CHOOSE_PHOTO);
    }

    private void handleImage(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();

        if (DocumentsContract.isDocumentUri(this, uri)) {
            // Uri type is document.
            String docId = DocumentsContract.getDocumentId(uri);

            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Uri type is content.
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // Uri type is file.
            imagePath = uri.getPath();
        }

        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;

        // get real image path by Uri and selection.
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }

        return path;
    }

    private void displayImage(String path) {

        if (path != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            binding.imgvPicture.setImageBitmap(Bitmap.createScaledBitmap(bitmap,
                    binding.imgvPicture.getWidth(), binding.imgvPicture.getWidth() * bitmap.getHeight() / bitmap.getWidth(), false));
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    private void initMediaPlayer() {
        try {
            mMediaPlayer.setDataSource(new File(PathUtils.getRootPath() + "music.mp3").getPath());
            mMediaPlayer.prepare();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
    }

    private void initVideoView() {
        binding.videoView.setVideoPath(new File(PathUtils.getRootPath() + "movie.mp4").getPath());
    }
}
