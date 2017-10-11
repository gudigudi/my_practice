package com.gudigudigudi.mdtemplate.gson;

/**
 * Created by gu on 10/11/17.
 */

public class AQI {

    public AQICity city;

    public class AQICity {
        public String aqi;
        public String pm25;
    }
}
