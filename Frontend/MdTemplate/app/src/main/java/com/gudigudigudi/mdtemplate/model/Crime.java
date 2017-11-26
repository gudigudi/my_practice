package com.gudigudigudi.mdtemplate.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by gu on 11/26/17.
 */

public class Crime {

    private UUID mUUID;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime() {
        mUUID = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getUUID() {
        return mUUID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
