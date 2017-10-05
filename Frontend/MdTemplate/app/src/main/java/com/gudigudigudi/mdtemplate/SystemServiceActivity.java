package com.gudigudigudi.mdtemplate;

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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;

public class SystemServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SystemServiceActivity";

    public static final String FILE_PRIVIDER = "com.gudigudigudi.mdtemplate.fileprivider";
    private static final int REQUEST_CODE_TAKE_PHOTO = 1;
    private static final int REQUEST_CODE_CHOOSE_PHOTO = 2;
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 3;

    private Button btn_send_notification;

    private Button btn_take_photo;
    private Uri imageUri;
    private ImageView picture;

    private Button btn_choose_photo;

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Button btn_play;
    private Button btn_pause;
    private Button btn_stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_service);

        btn_send_notification = (Button) findViewById(R.id.send_notification);

        btn_take_photo = (Button) findViewById(R.id.take_photo);
        btn_choose_photo = (Button) findViewById(R.id.choose_from_album);
        picture = (ImageView) findViewById(R.id.picture);

        btn_play = (Button) findViewById(R.id.play);
        btn_pause = (Button) findViewById(R.id.pause);
        btn_stop = (Button) findViewById(R.id.stop);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
        } else {
            initMediaPlayer();
        }

        btn_send_notification.setOnClickListener(this);
        btn_take_photo.setOnClickListener(this);
        btn_choose_photo.setOnClickListener(this);
        btn_play.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(Bitmap.createScaledBitmap(bitmap,
                                picture.getWidth(), picture.getWidth() * bitmap.getHeight() / bitmap.getWidth(), false));
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
                Log.d(TAG, "Unknown requestCode.");
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
                    Toast.makeText(SystemServiceActivity.this, "You denied the permission",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlayer();
                } else {
                    Toast.makeText(this, "You denied the permission.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                Log.d(TAG, "Unknown request code.");
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.send_notification:
                intent = new Intent(SystemServiceActivity.this, NotificationToggledActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(SystemServiceActivity.this, 0, intent, 0);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                Notification notification = new NotificationCompat.Builder(getApplicationContext(), null)
                        .setContentIntent(pendingIntent)
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

                notificationManager.notify(1, notification);

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
                    imageUri = FileProvider.getUriForFile(SystemServiceActivity.this, FILE_PRIVIDER, outputImage);
                } else {
                    imageUri = Uri.fromFile(outputImage);
                }

                // start camera.
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
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
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.pause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.stop:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    initMediaPlayer();
                }
                break;
            default:
                Log.d(TAG, "Unknown view is clicked.");
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
            picture.setImageBitmap(Bitmap.createScaledBitmap(bitmap,
                    picture.getWidth(), picture.getWidth() * bitmap.getHeight() / bitmap.getWidth(), false));
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    private void initMediaPlayer() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "music.mp3");
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
