package com.gudigudigudi.mdtemplate.arch.persistence.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gudigudigudi.commonlib.base.BaseApplication;
import com.gudigudigudi.mdtemplate.arch.persistence.room.dao.BookDao;
import com.gudigudigudi.mdtemplate.arch.persistence.room.dao.CategoryDao;
import com.gudigudigudi.mdtemplate.arch.persistence.room.model.Book;
import com.gudigudigudi.mdtemplate.arch.persistence.room.model.Category;


@Database(entities = {Book.class, Category.class,},
        version = BaseApplication.APP_DB_VERSION,
        exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BookDao bookDao();

    public abstract CategoryDao categoryDao();
}
