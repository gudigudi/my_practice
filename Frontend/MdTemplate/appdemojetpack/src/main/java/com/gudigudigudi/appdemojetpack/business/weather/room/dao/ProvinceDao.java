package com.gudigudigudi.appdemojetpack.business.weather.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gudigudigudi.appdemojetpack.business.weather.room.model.Province;

import java.util.List;


/**
 * Created by gu on 10/10/17.
 */

@Dao
public interface ProvinceDao {

    @Query("SELECT * FROM province")
    List<Province> getAll();

    @Insert
    void insert(Province province);

    @Insert
    void insertAll(List<Province> provinces);
}
