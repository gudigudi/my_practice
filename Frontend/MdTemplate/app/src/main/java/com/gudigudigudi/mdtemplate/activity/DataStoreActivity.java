package com.gudigudigudi.mdtemplate.activity;

import android.arch.persistence.room.Room;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.db.AppDBHelper;
import com.gudigudigudi.mdtemplate.db.AppDatabase;
import com.gudigudigudi.mdtemplate.db.dao.BookDao;
import com.gudigudigudi.mdtemplate.db.model.Book;
import com.gudigudigudi.mdtemplate.util.LogUtil;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class DataStoreActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "DataStoreActivity";

    private EditText mEditText;

    private Button mBtnSaveDataBySharedpreferences;
    private Button mBtnRestoreDataBySharedpreferences;

    private AppDBHelper mDBHelper;
    private Button mBtnCreateDB;
    private Button mBtnAddDataToDB;
    private Button mBtnUpdateDB;
    private Button mBtnDeleteDataFromDB;
    private Button mBtnQueryDataFromDB;

    private AppDatabase mAppDatabase;
    private BookDao mBookDao;
    private Button mBtnInsertRoom;
    private Button mBtnQueryRoom;
    private Button mBtnUpdateRoom;
    private Button mBtnDeleteRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_store);

        mEditText = findViewById(R.id.edit);

        String inputText = load();

        if (!TextUtils.isEmpty(inputText)) {
            mEditText.setText(inputText);
            mEditText.setSelection(inputText.length());
            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show();
        }

        mBtnSaveDataBySharedpreferences = findViewById(R.id.btn_save_data_by_sharedpreferences);
        mBtnRestoreDataBySharedpreferences = findViewById(R.id.btn_restore_data_by_sharedpreferences);

        mDBHelper = new AppDBHelper(this, "book.db", null, 3);
        mBtnCreateDB = findViewById(R.id.btn_create_db);
        mBtnAddDataToDB = findViewById(R.id.btn_add_data_to_db);
        mBtnUpdateDB = findViewById(R.id.btn_update_db);
        mBtnDeleteDataFromDB = findViewById(R.id.btn_delete_data_from_db);
        mBtnQueryDataFromDB = findViewById(R.id.btn_query_data_from_db);

        mAppDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "book").build();
        mBookDao = mAppDatabase.bookDao();
        mBtnInsertRoom = findViewById(R.id.btn_insert_room);
        mBtnQueryRoom = findViewById(R.id.btn_query_room);
        mBtnUpdateRoom = findViewById(R.id.btn_update_room);
        mBtnDeleteRoom = findViewById(R.id.btn_delete_room);

        mBtnSaveDataBySharedpreferences.setOnClickListener(this);
        mBtnRestoreDataBySharedpreferences.setOnClickListener(this);

        mBtnCreateDB.setOnClickListener(this);
        mBtnAddDataToDB.setOnClickListener(this);
        mBtnUpdateDB.setOnClickListener(this);
        mBtnDeleteDataFromDB.setOnClickListener(this);
        mBtnQueryDataFromDB.setOnClickListener(this);

        mBtnInsertRoom.setOnClickListener(this);
        mBtnQueryRoom.setOnClickListener(this);
        mBtnUpdateRoom.setOnClickListener(this);
        mBtnDeleteRoom.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        String input = mEditText.getText().toString();
        save(input);
    }

    private void save(String input) {

        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String load() {

        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();

        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return content.toString();
    }

    @Override
    public void onClick(View view) {
        SQLiteDatabase database;
        ContentValues values;

        switch (view.getId()) {
            case R.id.btn_save_data_by_sharedpreferences:
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", "Tom");
                editor.putInt("age", 28);
                editor.putBoolean("married", false);
                editor.apply();
                break;
            case R.id.btn_restore_data_by_sharedpreferences:
                SharedPreferences prefs = getSharedPreferences("data", MODE_PRIVATE);
                String name = prefs.getString("name", "");
                int age = prefs.getInt("age", 0);
                boolean married = prefs.getBoolean("married", false);

                Log.d(TAG, "name is " + name);
                Log.d(TAG, "age is " + age);
                Log.d(TAG, "married is " + married);
                break;
            case R.id.btn_create_db:
                mDBHelper.getWritableDatabase();
                break;
            case R.id.btn_add_data_to_db:
                database = mDBHelper.getWritableDatabase();
                values = new ContentValues();
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                values.put("press", "Unknown");
                database.insert("book", null, values);

                values.clear();

                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                values.put("press", "Unknown");
                database.insert("book", null, values);

                values.clear();

                break;
            case R.id.btn_update_db:
                database = mDBHelper.getWritableDatabase();
                values = new ContentValues();
                values.put("price", 10.99);
                database.update("book", values, "name = ?", new String[]{"The Da Vinci Code"});
                values.clear();

                break;
            case R.id.btn_delete_data_from_db:
                database = mDBHelper.getWritableDatabase();
                database.delete("book", "pages > ?", new String[]{"500"});

                break;
            case R.id.btn_query_data_from_db:
                database = mDBHelper.getWritableDatabase();
                Log.d(TAG, "query data from Sqlite db.");

                Log.d(TAG, "book database version is " + database.getVersion());

                Cursor cursor = database.query("book", null, null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String name1 = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        String press = cursor.getString(cursor.getColumnIndex("press"));
                        Log.d(TAG, "book name is " + name1);
                        Log.d(TAG, "book author is " + author);
                        Log.d(TAG, "book pages is " + pages);
                        Log.d(TAG, "book price is " + price);
                        Log.d(TAG, "book press is " + press);

                    } while (cursor.moveToNext());
                }
                break;
            case R.id.btn_insert_room:
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        Book book = new Book();
                        book.setName("The Dan Vinci Code");
                        book.setAuthor("Dan Brown");
                        book.setPages(454);
                        book.setPrice(16.96);
                        book.setPress("Unknown");

                        Log.d(TAG, "insert book into Sqlite db: " + book.toString());

                        mBookDao.insert(book);

                        return null;
                    }
                }.execute();
            case R.id.btn_query_room:
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        List<Book> bookList = mBookDao.getAll();
                        for (Book book : bookList) {
                            Log.d(TAG, "book is:" + book.toString());
                        }
                        return null;
                    }
                }.execute();
                break;
            case R.id.btn_update_room:
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        double price = 1.01;
                        String name = "The Dan Vinci Code";
                        Log.d(TAG, "book to be updated: " + mBookDao.getBookByName(name).toString());
                        mBookDao.updatePriceByName("The Dan Vinci Code", price);
                        Log.d(TAG, "book is updated: " + mBookDao.getBookByName(name).toString());
                        return null;
                    }
                }.execute();

                break;
            case R.id.btn_delete_room:
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        String name = "The Dan Vinci Code";
                        mBookDao.deleteBook(mBookDao.getBookByName(name));
                        Log.d(TAG, "book number: " + mBookDao.getAll().size());
                        return null;
                    }
                }.execute();
                break;
            default:
                Logger.d(LogUtil.LOG_UNKNOWN_VIEW_IS_CLICKED);
        }

    }
}
