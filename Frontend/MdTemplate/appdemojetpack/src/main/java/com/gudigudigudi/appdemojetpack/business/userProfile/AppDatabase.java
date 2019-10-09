package com.gudigudigudi.appdemojetpack.business.userProfile;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gudigudigudi.commonlib.base.BaseApplication;


@Database(entities = {User.class}, version = BaseApplication.APP_DB_VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}
