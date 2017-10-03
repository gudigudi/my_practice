package com.gudigudigudi.mdtemplate.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by gu on 10/3/17.
 */

@Database(entities = {Book.class, Category.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BookDao bookDao();

    public abstract CategoryDao categoryDao();
}
