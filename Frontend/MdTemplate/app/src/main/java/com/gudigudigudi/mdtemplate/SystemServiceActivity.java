package com.gudigudigudi.mdtemplate;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;

public class SystemServiceActivity extends AppCompatActivity {

    private static final String TAG = "SystemServiceActivity";

    public static final String FILE_PRIVIDER = "com.gudigudigudi.mdtemplate.fileprivider";
    private static final int REQUEST_CODE_TAKE_PHOTO = 1;

    private Button btn_send_notification;

    private Button btn_take_photo;
    private Uri imageUri;
    private ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_service);

        btn_send_notification = (Button) findViewById(R.id.send_notification);

        btn_send_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SystemServiceActivity.this, NotificationToggledActivity.class);
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
            }
        });

        btn_take_photo = (Button) findViewById(R.id.take_photo);
        picture = (ImageView) findViewById(R.id.picture);
        btn_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 2;

                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri), null, options);
                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                Log.d(TAG, "Unknown requestCode.");
                break;
        }
    }
}
