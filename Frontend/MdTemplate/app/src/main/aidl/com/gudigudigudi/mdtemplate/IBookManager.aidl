// IBookManager.aidl
package com.gudigudigudi.mdtemplate;

import com.gudigudigudi.mdtemplate.Book;

interface IBookManager{

    List<Book> getBookList();

    void addBook(in Book book);
}