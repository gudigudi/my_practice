package com.gudigudigudi.mdtemplate.arch.persistence.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.gudigudigudi.mdtemplate.arch.persistence.room.model.Category;

import java.util.List;


/**
 * Created by gu on 10/3/17.
 */

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM category")
    List<Category> getAll();

    @Query("SELECT * FROM category WHERE code= :code LIMIT 1")
    Category getCategoryByCode(int code);

}
