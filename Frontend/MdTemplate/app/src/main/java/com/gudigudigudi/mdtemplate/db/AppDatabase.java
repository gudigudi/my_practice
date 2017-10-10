package com.gudigudigudi.mdtemplate.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.gudigudigudi.mdtemplate.BaseApplication;

/**
 * Created by gu on 10/3/17.
 */

@Database(entities = {
        Book.class,
        Category.class,
        Province.class,
        City.class,
        County.class},
        version = BaseApplication.APP_DB_VERSION)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BookDao bookDao();

    public abstract CategoryDao categoryDao();

    public abstract ProvinceDao provinceDao();

    public abstract CityDao cityDao();

    public abstract CountyDao countyDao();
}
