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
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "code")
    @SerializedName("id")
    public int code;
    @ColumnInfo(name = "provinceId")
    public int provinceId;
}
