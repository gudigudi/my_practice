package com.gudigudigudi.mdtemplate.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by gu on 10/3/17.
 */

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

    public void setId(int id) {
        this.id = id;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", pages=" + pages +
                ", name='" + name + '\'' +
                ", press='" + press + '\'' +
                '}';
    }
}
