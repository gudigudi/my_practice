package com.gudigudigudi.mdtemplate.activity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.provider.DatabaseContentProvider;
import com.gudigudigudi.mdtemplate.util.LogUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class ContentProviderActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ContentProviderActivity";

    private static final int PERMISSION_REQUESTCODE_CALL_PHONE = 1;
    private static final int PERMISSION_REQUESTCODE_READ_CONTACTS = 2;

    private Button btn_make_call;

    ArrayAdapter<String> adapter;
    List<String> contactList = new ArrayList<>();
    private ListView contactsView;

    private String newId;

    private Button addData, queryData, updateData, deleteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        btn_make_call = findViewById(R.id.make_call);
        contactsView = findViewById(R.id.contacts_view);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactList);
        contactsView.setAdapter(adapter);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ContentProviderActivity.this,
                    new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_REQUESTCODE_READ_CONTACTS);
        } else {
            readContacts();
        }

        addData = findViewById(R.id.add_data);
        queryData = findViewById(R.id.query_data);
        updateData = findViewById(R.id.update_data);
        deleteData = findViewById(R.id.delete_data);

        btn_make_call.setOnClickListener(this);
        addData.setOnClickListener(this);
        queryData.setOnClickListener(this);
        updateData.setOnClickListener(this);
        deleteData.setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_REQUESTCODE_CALL_PHONE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    make_call();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
//                    ActivityCompat.requestPermissions(ContentProviderActivity.this,
//                            new String[]{Manifest.permission.CALL_PHONE}, 1); // request permission again.
                }
                break;
            case PERMISSION_REQUESTCODE_READ_CONTACTS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Log.d(TAG, "onRequestPermissionsResult: " + "Unknown permission is requested.");
        }
    }

    private void readContacts() {
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String display = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactList.add(display + '\n' + number);
                }

                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private void make_call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL); // call phone.
            intent.setData(Uri.parse("tel:13967704302"));
            startActivity(intent);
        } catch (SecurityException e) {
            Log.d(TAG, e.toString());
        }
    }

    @Override
    public void onClick(View view) {
        Uri uri = null;
        ContentValues values = new ContentValues();

        switch (view.getId()) {
            case R.id.make_call:
                if (ContextCompat.checkSelfPermission(ContentProviderActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ContentProviderActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUESTCODE_CALL_PHONE);
                } else {
                    make_call();
                }
                break;
            case R.id.add_data:
                uri = Uri.parse("content://" + DatabaseContentProvider.AUTHORITY + "/book");
                values.put("name", "A Clash of Kings");
                values.put("author", "George Martin");
                values.put("pages", 1040);
                values.put("price", 22.85);
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
                values.clear();
                break;
            case R.id.query_data:
                uri = Uri.parse("content://" + DatabaseContentProvider.AUTHORITY + "/book");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d(TAG, "book name is: " + name);
                        Log.d(TAG, "author name is: " + author);
                        Log.d(TAG, "pages name is: " + pages);
                        Log.d(TAG, "price name is: " + price);
                    }
                    cursor.close();
                }
                break;
            case R.id.update_data:
                uri = Uri.parse("content://" + DatabaseContentProvider.AUTHORITY + "/book/" + newId);
                values.put("name", "A Storm of Swords");
                values.put("pages", 1216);
                values.put("price", 24.05);
                getContentResolver().update(uri, values, null, null);
                values.clear();
                break;
            case R.id.delete_data:
                uri = Uri.parse("content://" + DatabaseContentProvider.AUTHORITY + "/book/" + newId);
                getContentResolver().delete(uri, null, null);
                break;
            default:
                Logger.d(LogUtil.LOG_UNKNOWN_VIEW_IS_CLICKED);
        }
    }
}
