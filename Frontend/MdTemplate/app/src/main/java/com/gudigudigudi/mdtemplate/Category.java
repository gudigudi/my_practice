package com.gudigudigudi.mdtemplate;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by gu on 10/3/17.
 */

@Entity
public class Category {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "category_name")
    private String categoryName;
    @ColumnInfo(name = "category_code")
    private int categoryCode;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }
}
