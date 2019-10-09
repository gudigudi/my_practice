package com.gudigudigudi.appdemojetpack.business.weather.room.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by gu on 10/9/17.
 */

/**
 * CREATE TABLE province (
 * id INTEGER PRIMARY KEY AUTOINCREMENT,
 * name TEXT,
 * code INTEGER );
 */
@Data
@Entity
public class Province {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "code")
    @SerializedName("id")
    private int code;
    @ColumnInfo(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
