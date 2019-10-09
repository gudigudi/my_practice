package com.gudigudigudi.appdemojetpack.business.weather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gu on 10/11/17.
 */

public class Weather {

    public String status;
    public Basic basic;
    public AQI aqi;
    public Now now;
    public Suggestion suggestion;
    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;
}
