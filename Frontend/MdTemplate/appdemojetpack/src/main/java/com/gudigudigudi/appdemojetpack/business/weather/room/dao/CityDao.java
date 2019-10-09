package com.gudigudigudi.appdemojetpack.business.weather.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.gudigudigudi.appdemojetpack.business.weather.room.model.City;

import java.util.List;

/**
 * Created by gu on 10/10/17.
 */

@Dao
public interface CityDao {

    @Query("SELECT * FROM city")
    List<City> getAll();

    @Query("SELECT * FROM city WHERE provinceId = :provinceId")
    List<City> getCityInProvince(int provinceId);

    @Insert
    void insert(City city);

    @Insert
    void insertAll(List<City> cities);
}
