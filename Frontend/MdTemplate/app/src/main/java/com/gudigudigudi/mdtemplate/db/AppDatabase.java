package com.gudigudigudi.mdtemplate.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.gudigudigudi.mdtemplate.base.BaseApplication;
import com.gudigudigudi.mdtemplate.db.dao.BookDao;
import com.gudigudigudi.mdtemplate.db.dao.CategoryDao;
import com.gudigudigudi.mdtemplate.db.dao.CityDao;
import com.gudigudigudi.mdtemplate.db.dao.CountyDao;
import com.gudigudigudi.mdtemplate.db.dao.ProvinceDao;
import com.gudigudigudi.mdtemplate.db.model.Book;
import com.gudigudigudi.mdtemplate.db.model.Category;
import com.gudigudigudi.mdtemplate.db.model.City;
import com.gudigudigudi.mdtemplate.db.model.County;
import com.gudigudigudi.mdtemplate.db.model.Province;

/**
 * Created by gu on 10/3/17.
 */

@Database(entities = {
        Book.class,
        Category.class,
        Province.class,
        City.class,
        County.class},
        version = BaseApplication.APP_DB_VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BookDao bookDao();

    public abstract CategoryDao categoryDao();

    public abstract ProvinceDao provinceDao();

    public abstract CityDao cityDao();

    public abstract CountyDao countyDao();
}
