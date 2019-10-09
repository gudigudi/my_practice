package com.gudigudigudi.mdtemplate.arch.persistence.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.gudigudigudi.mdtemplate.arch.persistence.room.model.Book;

import java.util.List;


/**
 * Created by gu on 10/3/17.
 */

@Dao
public interface BookDao {

    @Query("SELECT * FROM book")
    List<Book> getAll();

    @Query("SELECT * FROM book WHERE id = :userId")
    List<Book> getBookById(int userId);

    @Query("SELECT * FROM book WHERE name = :name LIMIT 1")
    Book getBookByName(String name);

    @Insert
    void insertAll(List<Book> books);

    @Insert
    void insert(Book book);

    @Delete
    void deleteBook(Book book);

    @Query("UPDATE book SET price = :price WHERE name = :name")
    void updatePriceByName(String name, double price);

}
