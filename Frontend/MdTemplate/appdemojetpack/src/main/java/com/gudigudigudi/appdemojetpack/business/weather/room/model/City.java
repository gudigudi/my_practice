package com.gudigudigudi.appdemojetpack.business.weather.room.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

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
@Data
@Entity(foreignKeys = {
        @ForeignKey(entity = Province.class,
                parentColumns = "id",
                childColumns = "provinceId",
                onDelete = ForeignKey.CASCADE)
}, indices = {@Index("provinceId")})
public class City {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "code")
    @SerializedName("id")
    private int code;
    @ColumnInfo(name = "provinceId")
    private int provinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
