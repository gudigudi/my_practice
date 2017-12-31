package com.gudigudigudi.mdtemplate.model;

/**
 * Created by gu on 12/30/17.
 */

import com.google.gson.annotations.SerializedName;

/**
 * github user repos.
 * example: https://api.github.com/users/gudigudi/repos
 */
public class Repo {

    public int id;
    public String name;
    @SerializedName("full_name")
    public String fullName;

    public class Owner {

        public String login;
        public int id;
        public String avatar_url;
    }

}
