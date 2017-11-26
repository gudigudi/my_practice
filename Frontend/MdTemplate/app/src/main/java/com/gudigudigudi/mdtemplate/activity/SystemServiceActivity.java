package com.gudigudigudi.mdtemplate.activity;

import android.Manifest;
import android.annotation.TargetApi;
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
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Toast;
import android.widget.VideoView;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.util.LogUtil;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileNotFoundException;

public class SystemServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SystemServiceActivity";

    public static final String FILE_PRIVIDER = "com.gudigudigudi.mdtemplate.fileprivider";
    private static final int REQUEST_CODE_TAKE_PHOTO = 1;
    private static final int REQUEST_CODE_CHOOSE_PHOTO = 2;
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 3;

    private Button mBtnSendNotification;
    private Button mBtnSendHeadsupNotification;
    private Button mBtnSendFoldNotification;

    private Button mBtnTakePhoto;
    private Uri mImageUri;
    private ImageView mIVPicture;

    private Button mBtnChoosePhoto;

    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private Button mBtnPlay;
    private Button mBtnPause;
    private Button mBtnStop;

    private Button mBtnPlayVideo;
    private Button mBtnPauseVideo;
    private Button mBtnReplayVideo;
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_service);

        mBtnSendNotification = findViewById(R.id.send_notification);
        mBtnSendHeadsupNotification = findViewById(R.id.send_headsup_notification);
        mBtnSendFoldNotification = findViewById(R.id.send_fold_notification);

        mBtnTakePhoto = findViewById(R.id.take_photo);
        mBtnChoosePhoto = findViewById(R.id.choose_from_album);
        mIVPicture = findViewById(R.id.picture);

        mBtnPlay = findViewById(R.id.play);
        mBtnPause = findViewById(R.id.pause);
        mBtnStop = findViewById(R.id.stop);

        mBtnPlayVideo = findViewById(R.id.play_video);
        mBtnPauseVideo = findViewById(R.id.pause_video);
        mBtnReplayVideo = findViewById(R.id.replay_video);
        mVideoView = findViewById(R.id.video_view);

        mBtnSendNotification.setOnClickListener(this);
        mBtnSendHeadsupNotification.setOnClickListener(this);
        mBtnSendFoldNotification.setOnClickListener(this);
        mBtnTakePhoto.setOnClickListener(this);
        mBtnChoosePhoto.setOnClickListener(this);
        mBtnPlay.setOnClickListener(this);
        mBtnPause.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        mBtnPlayVideo.setOnClickListener(this);
        mBtnPauseVideo.setOnClickListener(this);
        mBtnReplayVideo.setOnClickListener(this);

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
        if (mVideoView != null) {
            mVideoView.suspend();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(mImageUri));
                        mIVPicture.setImageBitmap(Bitmap.createScaledBitmap(bitmap,
                                mIVPicture.getWidth(), mIVPicture.getWidth() * bitmap.getHeight() / bitmap.getWidth(), false));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case REQUEST_CODE_CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        handleImageOnKitKat(data);
                    } else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                Logger.d(LogUtil.LOG_UNKNOWN_REQUEST_CODE);
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
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlayer();
                    initVideoView();
                } else {
                    Toast.makeText(this, "You denied the permission.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                Logger.d(LogUtil.LOG_UNKNOWN_REQUEST_CODE);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        PendingIntent pi = null;
        Notification notification = null;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        switch (view.getId()) {
            case R.id.send_notification:
                intent = new Intent(SystemServiceActivity.this, NotificationToggledActivity.class);
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
            case R.id.send_headsup_notification:
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
            case R.id.send_fold_notification:
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_fold);

                notification = new NotificationCompat.Builder(getApplicationContext(), null)
                        .setSmallIcon(R.drawable.ic_create)
                        .setContentTitle("Fold notice")
                        .setCustomContentView(remoteViews)
                        .setCustomBigContentView(remoteViews)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.apple))
                        .build();
                break;
            case R.id.take_photo:
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");

                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mImageUri = FileProvider.getUriForFile(SystemServiceActivity.this, FILE_PRIVIDER, outputImage);
                } else {
                    mImageUri = Uri.fromFile(outputImage);
                }

                // start camera.
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
                startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);

                break;
            case R.id.choose_from_album:
                if (ContextCompat.checkSelfPermission(SystemServiceActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SystemServiceActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_CHOOSE_PHOTO);
                } else {
                    openAlbum();
                }

                break;
            case R.id.play:
                if (!mMediaPlayer.isPlaying()) {
                    mMediaPlayer.start();
                }
                break;
            case R.id.pause:
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
            case R.id.play_video:
                if (!mVideoView.isPlaying()) {
                    mVideoView.start();
                }
                break;
            case R.id.pause_video:
                if (mVideoView.isPlaying()) {
                    mVideoView.pause();
                }
                break;
            case R.id.replay_video:
                if (mVideoView.isPlaying()) {
                    mVideoView.resume();
                }
                break;
            default:
                Logger.d(LogUtil.LOG_UNKNOWN_VIEW_IS_CLICKED);
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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitKat(Intent data) {
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

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);

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
            mIVPicture.setImageBitmap(Bitmap.createScaledBitmap(bitmap,
                    mIVPicture.getWidth(), mIVPicture.getWidth() * bitmap.getHeight() / bitmap.getWidth(), false));
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    private void initMediaPlayer() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "music.mp3");
            mMediaPlayer.setDataSource(file.getPath());
            mMediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initVideoView() {
        File file = new File(Environment.getExternalStorageDirectory(), "movie.mp4");
        mVideoView.setVideoPath(file.getPath());
    }
}
