package com.gudigudigudi.appdemojetpack.business.weather;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gudigudigudi.appdemojetpack.business.weather.room.dao.CityDao;
import com.gudigudigudi.appdemojetpack.business.weather.room.dao.CountyDao;
import com.gudigudigudi.appdemojetpack.business.weather.room.dao.ProvinceDao;
import com.gudigudigudi.appdemojetpack.business.weather.room.model.City;
import com.gudigudigudi.appdemojetpack.business.weather.room.model.County;
import com.gudigudigudi.appdemojetpack.business.weather.room.model.Province;
import com.gudigudigudi.commonlib.base.BaseApplication;


@Database(entities = {
        Province.class,
        City.class,
        County.class}, version = BaseApplication.APP_DB_VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProvinceDao provinceDao();

    public abstract CityDao cityDao();

    public abstract CountyDao countyDao();
}
