package com.gudigudigudi.appdemojetpack.business.weather.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gudigudigudi.appdemojetpack.business.weather.room.model.County;

import java.util.List;

/**
 * Created by gu on 10/10/17.
 */

@Dao
public interface CountyDao {

    @Query("SELECT * FROM county")
    List<County> getAll();

    @Query("SELECT * FROM county WHERE cityId = :cityId")
    List<County> getCountyInCity(int cityId);

    @Insert
    void insert(County county);

    @Insert
    void insertAll(List<County> counties);
}
