package com.gudigudigudi.mdtemplate.model.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gu on 10/11/17.
 */

public class Now {

    @SerializedName("tmp")
    public String temperature;
    @SerializedName("cond")
    public More more;

    public class More {
        @SerializedName("txt")
        public String info;
    }
}
