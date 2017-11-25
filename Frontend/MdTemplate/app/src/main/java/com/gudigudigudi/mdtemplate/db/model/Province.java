package com.gudigudigudi.mdtemplate.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gu on 10/9/17.
 */

/**
 * CREATE TABLE province (
 * id INTEGER PRIMARY KEY AUTOINCREMENT,
 * name TEXT,
 * code INTEGER );
 */
@Entity
public class Province {

    @PrimaryKey(autoGenerate = true)
    private int _id;
    @ColumnInfo(name = "code")
    @SerializedName("id")
    private int code;
    @ColumnInfo(name = "name")
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
