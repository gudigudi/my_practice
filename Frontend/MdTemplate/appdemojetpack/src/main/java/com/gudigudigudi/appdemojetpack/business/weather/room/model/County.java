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
 * CREATE TABLE county (
 * id INTEGER PRIMARY KEY AUTOINCREMENT,
 * name TEXT,
 * weatherId TEXT,
 * cityId INTEGER );
 */
@Data
@Entity(foreignKeys = {
        @ForeignKey(entity = City.class,
                parentColumns = "id",
                childColumns = "cityId",
                onDelete = ForeignKey.CASCADE)
}, indices = {@Index("cityId")})
public class County {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "code")
    public int code;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "weatherId")
    @SerializedName("weather_id")
    public String weatherId;
    @ColumnInfo(name = "cityId")
    public int cityId;
}
