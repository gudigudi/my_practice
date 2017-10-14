package com.gudigudigudi.mdtemplate.model.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gu on 10/11/17.
 */

public class Forecast {

    public String date;
    @SerializedName("tmp")
    public Temperature temperature;
    @SerializedName("cond")
    public More more;

    public class Temperature {
        public String max;
        public String min;
    }

    public class More {
        @SerializedName("txt_d")
        public String info;
    }
}
