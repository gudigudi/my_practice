package com.gudigudigudi.mdtemplate.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by gu on 10/10/17.
 */

@Dao
public interface ProvinceDao {

    @Query("SELECT * FROM province")
    List<Province> getAll();

}
