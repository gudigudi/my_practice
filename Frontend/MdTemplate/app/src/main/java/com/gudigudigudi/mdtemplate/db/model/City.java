package com.gudigudigudi.mdtemplate.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gu on 10/9/17.
 */

/**
 * CREATE TABLE city (
 * id INTEGER PRIMARY KEY AUTOINCREMENT,
 * name TEXT,
 * code INTEGER,
 * provinceId INTEGER );
 */
//@Entity(foreignKeys = {
//        @ForeignKey(entity = Province.class,
//                parentColumns = "id",
//                childColumns = "provinceId",
//                onDelete = ForeignKey.CASCADE)
//})
@Entity
public class City {

    @PrimaryKey(autoGenerate = true)
    private int _id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "code")
    @SerializedName("id")
    private int code;
    @ColumnInfo(name = "provinceId")
    private int provinceId;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
