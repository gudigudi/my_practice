package com.gudigudigudi.mdtemplate.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

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
