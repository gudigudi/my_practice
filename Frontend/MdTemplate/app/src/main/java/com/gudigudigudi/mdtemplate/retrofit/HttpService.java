package com.gudigudigudi.mdtemplate.retrofit;


import android.database.Observable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by gu on 1/21/18.
 */

public interface HttpService {

    @GET("index.php?m=Api&c=User&a=userInfo")
    Call<Result<Content>> getUserInfo();

    @FormUrlEncoded
    @POST
    Observable<Result<User>> getUserInfo(@Field("user_id") String user_id);

    @GET("ajax.php")
    Call<Result<Content>> getInfo(@Body Content content);

    @GET("{path/}{index.php?m=Api&c=User&a=userInfo}")
    Call<Result<Content>> getUserInfoPath(@Path("path") String path);

    @GET("ajax.php")
    Call<Result<Content>> getInfo(@Query("id") String id);

}
