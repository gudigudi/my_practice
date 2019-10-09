package com.gudigudigudi.mdtemplate.arch.persistence.room.model;


/**
 * Created by gu on 10/3/17.
 */

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

/**
 * CREATE TABLE book (
 * id INTEGER PRIMARY KEY AUTOINCREMENT
 * author TEXT,
 * price REAL,
 * pages INTEGER,
 * name TEXT );
 */
@Data
@Entity
public class Book {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "author")
    private String author;
    @ColumnInfo(name = "price")
    private double price;
    @ColumnInfo(name = "pages")
    private int pages;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "press")
    private String press;
}
