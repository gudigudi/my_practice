package com.gudigudigudi.appdemojetpack.business.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gu on 10/11/17.
 */

public class Basic {

    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update {
        @SerializedName("loc")
        public String updatetime;
    }
}
