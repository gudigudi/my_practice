package com.gudigudigudi.appdemojetpack.room;


/**
 * Created by gu on 10/3/17.
 */

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * CREATE TABLE book (
 * id INTEGER PRIMARY KEY AUTOINCREMENT
 * author TEXT,
 * price REAL,
 * pages INTEGER,
 * name TEXT );
 */
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

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getPages() {
        return pages;
    }

    public String getName() {
        return name;
    }

    public String getPress() {
        return press;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPress(String press) {
        this.press = press;
    }
}
