package com.gudigudigudi.appdemojetpack.business.userProfile;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    public int id;
    public String name;
    public String lastName;

    public User(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }
}
