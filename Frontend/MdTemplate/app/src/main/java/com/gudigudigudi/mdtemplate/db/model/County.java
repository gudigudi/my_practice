package com.gudigudigudi.mdtemplate.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

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

//@Entity(foreignKeys = {
//        @ForeignKey(entity = City.class,
//                parentColumns = "id",
//                childColumns = "cityId",
//                onDelete = ForeignKey.CASCADE)
//})
@Entity
public class County {

    @PrimaryKey(autoGenerate = true)
    private int _id;
    @ColumnInfo(name = "code")
    private int code;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "weatherId")
    @SerializedName("weather_id")
    private String weatherId;
    @ColumnInfo(name = "cityId")
    private int cityId;

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

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
