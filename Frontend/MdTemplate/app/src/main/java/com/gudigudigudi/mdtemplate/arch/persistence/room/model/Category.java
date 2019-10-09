package com.gudigudigudi.mdtemplate.arch.persistence.room.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

/**
 * Created by gu on 10/3/17.
 */

@Data
@Entity
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "code")
    private int code;
}
