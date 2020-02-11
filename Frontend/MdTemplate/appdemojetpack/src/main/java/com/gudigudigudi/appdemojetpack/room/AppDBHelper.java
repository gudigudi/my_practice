package com.gudigudigudi.appdemojetpack.room;

/**
 * Created by gu on 10/2/17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Book sqlite db.
 */
public class AppDBHelper extends SQLiteOpenHelper {

    public static final String CREATE_SQL = "CREATE TABLE book (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "author TEXT, " +
            "price REAL, " +
            "pages INTEGER, " +
            "name TEXT, " +
            "press TEXT )";
    public static final String CREATE_CATEGORY = "CREATE TABLE category (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, " +
            "code INTEGER )";

    public static final String CREATE_CITY = "CREATE TABLE city (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, " +
            "code INTEGER, " +
            "provinceId INTEGER )";
    public static final String CREATE_COUNTY = "CREATE TABLE county (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, " +
            "weatherId TEXT, " +
            "cityId INTEGER )";
    public static final String CREATE_PROVINCE = "CREATE TABLE province (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, " +
            "code INTEGER )";

    private Context context;

    public AppDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(CREATE_SQL);
        database.execSQL(CREATE_CATEGORY);
        database.execSQL(CREATE_CITY);
        database.execSQL(CREATE_COUNTY);
        database.execSQL(CREATE_PROVINCE);

        Toast.makeText(context, "Create database succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {

        database.execSQL("DROP TABLE IF EXISTS book");
        database.execSQL("DROP TABLE IF EXISTS category");
        database.execSQL("DROP TABLE IF EXISTS city");
        database.execSQL("DROP TABLE IF EXISTS county");
        database.execSQL("DROP TABLE IF EXISTS province");

        onCreate(database);
    }
}
