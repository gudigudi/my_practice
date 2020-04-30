package com.gudigudigudi.appdemojetpack.business.weather;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;

import androidx.preference.PreferenceManager;

import com.google.gson.Gson;
import com.gudigudigudi.appdemojetpack.business.weather.model.Weather;
import com.gudigudigudi.commonlib.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

@NoArgsConstructor
@Slf4j
public class WeatherAutoUpdateService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateWeather();
        updateBingPic();

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int eightHour = 8 * 60 * 60 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + eightHour;
        Intent i = new Intent(this, WeatherAutoUpdateService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);

        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * update weather info.
     */
    private void updateWeather() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = preferences.getString("weather", null);
        if (weatherString != null) {

            Weather weather = null;
            try {
                weather = new Gson().fromJson(new JSONObject(weatherString).getJSONArray("HeWeather").getJSONObject(0).toString(), Weather.class);
            } catch (JSONException e) {
                log.error(e.getMessage());
            }
            final String weatherId = Objects.requireNonNull(weather).basic.weatherId;
            String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=bc0418b57b2d4918819d3974ac1285d9";

            HttpUtil.sendRequest(weatherUrl, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    log.error(e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText = response.body().string();
                    Weather weather = null;
                    try {
                        weather = new Gson().fromJson(new JSONObject(responseText).getJSONArray("HeWeather").getJSONObject(0).toString(), Weather.class);
                    } catch (JSONException e) {
                        log.error(e.getMessage());
                    }

                    if (weather != null && "ok".equals(weather.status)) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherAutoUpdateService.this).edit();
                        editor.putString("weather", responseText);
                        editor.apply();
                    }
                }
            });
        }
    }

    /**
     * update bing daily picture.
     */
    private void updateBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherAutoUpdateService.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
            }
        });
    }
}
