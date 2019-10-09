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
    private int id;
    @ColumnInfo(name = "code")
    private int code;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "weatherId")
    @SerializedName("weather_id")
    private String weatherId;
    @ColumnInfo(name = "cityId")
    private int cityId;

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

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
