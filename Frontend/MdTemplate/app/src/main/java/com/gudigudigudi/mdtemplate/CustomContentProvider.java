package com.gudigudigudi.mdtemplate;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class CustomContentProvider extends ContentProvider {

    private static final String TAG = "CustomContentProvider";

    private static final String AUTHORITY = "com.gudigudigudi.mdtemplate.customprovider";
    private static final String MIME_DIR = "vnd.android.cursor.dir/vnd.";
    private static final String MIME_ITEM = "vnd.android.cursor.item/vnd.";

    public static final int TABLE1_DIR = 0;
    public static final int TABLE1_ITEM = 1;
    public static final int TABLE2_DIR = 2;
    public static final int TABLE2_ITEM = 3;

    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "table1", TABLE1_DIR);
        uriMatcher.addURI(AUTHORITY, "table1/#", TABLE1_ITEM);
        uriMatcher.addURI(AUTHORITY, "table2", TABLE2_DIR);
        uriMatcher.addURI(AUTHORITY, "table2/#", TABLE2_ITEM);
    }


    public CustomContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                return MIME_DIR + AUTHORITY + ".table1";
            case TABLE1_ITEM:
                return MIME_ITEM + AUTHORITY + ".table1";
            case TABLE2_DIR:
                return MIME_DIR + AUTHORITY + ".table2";
            case TABLE2_ITEM:
                return MIME_ITEM + AUTHORITY + ".table2";
            default:
                Log.d(TAG, "Unknown uri is matched.");
                break;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                // query all data in table1.
                break;
            case TABLE1_ITEM:
                // query an item's data in table1.
                break;
            case TABLE2_DIR:
                // query all data in table2.
                break;
            case TABLE2_ITEM:
                // query an item's data in table2.
                break;
            default:
                Log.d(TAG, "Unknown uri matched.");
        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}