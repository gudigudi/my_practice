package com.gudigudigudi.mdtemplate.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gu on 12/23/17.
 */

public class Book implements Parcelable {

    public int bookId;
    public String bookName;

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel parcel) {
            return new Book(parcel);
        }

        @Override
        public Book[] newArray(int i) {
            return new Book[i];
        }
    };

    public Book(int bookI, String bookName) {
        this.bookId = bookI;
        this.bookName = bookName;
    }

    public Book(Parcel parcel) {
        this.bookId = parcel.readInt();
        this.bookName = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
