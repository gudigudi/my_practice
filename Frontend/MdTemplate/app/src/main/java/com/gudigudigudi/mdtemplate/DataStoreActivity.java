package com.gudigudigudi.mdtemplate;

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

import com.gudigudigudi.mdtemplate.db.AppDBHelper;
import com.gudigudigudi.mdtemplate.db.AppDatabase;
import com.gudigudigudi.mdtemplate.db.Book;
import com.gudigudigudi.mdtemplate.db.BookDao;

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

    private EditText editText;

    private Button btn_save_data_by_sharedpreferences;
    private Button btn_restore_data_by_sharedpreferences;


    private AppDBHelper dbHelper;
    private Button btn_create_db;
    private Button btn_add_data_to_db;
    private Button btn_update_db;
    private Button btn_delete_data_from_db;
    private Button btn_query_data_from_db;

    private AppDatabase appDatabase;
    private BookDao bookDao;
    private Button btn_insert_room;
    private Button btn_query_room;
    private Button btn_update_room;
    private Button btn_delete_room;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_store);

        editText = (EditText) findViewById(R.id.edit);

        String inputText = load();

        if (!TextUtils.isEmpty(inputText)) {
            editText.setText(inputText);
            editText.setSelection(inputText.length());
            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show();
        }

        btn_save_data_by_sharedpreferences = (Button) findViewById(R.id.btn_save_data_by_sharedpreferences);
        btn_restore_data_by_sharedpreferences = (Button) findViewById(R.id.btn_restore_data_by_sharedpreferences);


        dbHelper = new AppDBHelper(this, "book.db", null, 3);
        btn_create_db = (Button) findViewById(R.id.btn_create_db);
        btn_add_data_to_db = (Button) findViewById(R.id.btn_add_data_to_db);
        btn_update_db = (Button) findViewById(R.id.btn_update_db);
        btn_delete_data_from_db = (Button) findViewById(R.id.btn_delete_data_from_db);
        btn_query_data_from_db = (Button) findViewById(R.id.btn_query_data_from_db);


        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "book").build();
        bookDao = appDatabase.bookDao();
        btn_insert_room = (Button) findViewById(R.id.btn_insert_room);
        btn_query_room = (Button) findViewById(R.id.btn_query_room);
        btn_update_room = (Button) findViewById(R.id.btn_update_room);
        btn_delete_room = (Button) findViewById(R.id.btn_delete_room);


        btn_save_data_by_sharedpreferences.setOnClickListener(this);
        btn_restore_data_by_sharedpreferences.setOnClickListener(this);

        btn_create_db.setOnClickListener(this);
        btn_add_data_to_db.setOnClickListener(this);
        btn_update_db.setOnClickListener(this);
        btn_delete_data_from_db.setOnClickListener(this);
        btn_query_data_from_db.setOnClickListener(this);

        btn_insert_room.setOnClickListener(this);
        btn_query_room.setOnClickListener(this);
        btn_update_room.setOnClickListener(this);
        btn_delete_room.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        String input = editText.getText().toString();
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
                dbHelper.getWritableDatabase();
                break;
            case R.id.btn_add_data_to_db:
                database = dbHelper.getWritableDatabase();
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
                database = dbHelper.getWritableDatabase();
                values = new ContentValues();
                values.put("price", 10.99);
                database.update("book", values, "name = ?", new String[]{"The Da Vinci Code"});
                values.clear();

                break;
            case R.id.btn_delete_data_from_db:
                database = dbHelper.getWritableDatabase();
                database.delete("book", "pages > ?", new String[]{"500"});

                break;
            case R.id.btn_query_data_from_db:
                database = dbHelper.getWritableDatabase();
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

                        bookDao.insert(book);

                        return null;
                    }
                }.execute();
            case R.id.btn_query_room:
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        List<Book> bookList = bookDao.getAll();
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
                        Log.d(TAG, "book to be updated: " + bookDao.getBookByName(name).toString());
                        bookDao.updatePriceByName("The Dan Vinci Code", price);
                        Log.d(TAG, "book is updated: " + bookDao.getBookByName(name).toString());

                        return null;
                    }
                }.execute();

                break;
            case R.id.btn_delete_room:
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        String name = "The Dan Vinci Code";
                        bookDao.deleteBook(bookDao.getBookByName(name));
                        Log.d(TAG, "book number: " + bookDao.getAll().size());

                        return null;
                    }
                }.execute();

                break;

            default:
                Log.d(TAG, "Unknown view is clicked");
        }

    }
}
