package com.gudigudigudi.appdemojetpack.business.weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.preference.PreferenceManager;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gudigudigudi.appdemojetpack.R;
import com.gudigudigudi.appdemojetpack.business.weather.model.Forecast;
import com.gudigudigudi.appdemojetpack.business.weather.model.Weather;
import com.gudigudigudi.appdemojetpack.databinding.ActivityWeatherBinding;
import com.gudigudigudi.commonlib.base.BaseActivity;
import com.gudigudigudi.commonlib.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

@Slf4j
public class WeatherActivity extends BaseActivity {

    private final static String MSG_GET_WEATHER_DETAILS_FAILED = "获取天气信息失败";

    // TODO : binding for inside fragments.
    public ActivityWeatherBinding binding;

    public String weatherId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeatherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.swipeRefleshLayout.setColorSchemeResources(R.color.colorPrimary);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = preferences.getString("weather", null);
        if (weatherString != null) {
            Gson gson = new Gson();
            Weather weather = null;
            try {
                weather = gson.fromJson(new JSONObject(weatherString).getJSONArray("HeWeather").getJSONObject(0).toString(), Weather.class);
            } catch (JSONException e) {
                log.error(e.getMessage());
            }
            if (weather != null) {
                weatherId = weather.basic.weatherId;
                showWeatherInfo(weather);
            }
        } else {
            weatherId = getIntent().getStringExtra("weather_id");
            binding.scrollvWeather.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }

        String bingPic = preferences.getString("bing_pic", null);
        if (bingPic != null) {
            Glide.with(this).load(bingPic).into(binding.imgvBingPic);
        } else {
            loadBingPic();
        }

        binding.swipeRefleshLayout.setOnRefreshListener(() -> WeatherActivity.this.requestWeather(weatherId));

        binding.weatherTitle.btnNav.setOnClickListener(view -> binding.drawerLayout.openDrawer(GravityCompat.START));
    }

    public void requestWeather(String weatherId) {
        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=bc0418b57b2d4918819d3974ac1285d9";

        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> {
                    ToastUtils.showShort(MSG_GET_WEATHER_DETAILS_FAILED);
                    binding.swipeRefleshLayout.setRefreshing(false);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                String weatherContent = null;
                try {
                    weatherContent = new JSONObject(responseText).getJSONArray("HeWeather").getJSONObject(0).toString();
                } catch (JSONException e) {
                    log.error(e.toString());
                }
                final Weather weather = new Gson().fromJson(weatherContent, Weather.class);

                runOnUiThread(() -> {
                    if (weather != null && "ok".equals(weather.status)) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                        editor.putString("weather", responseText);
                        editor.apply();
                        showWeatherInfo(weather);
                    } else {
                        ToastUtils.showShort(MSG_GET_WEATHER_DETAILS_FAILED);
                    }
                    binding.swipeRefleshLayout.setRefreshing(false);
                });
            }
        });
    }

    private void showWeatherInfo(Weather weather) {
        if (weather != null && "ok".equals(weather.status)) {
            String cityName = weather.basic.cityName;
            String updateTime = weather.basic.update.updatetime.split(" ")[1];
            String degree = weather.now.temperature + " C";
            String weatherInfo = weather.now.more.info;

            binding.weatherTitle.txtvTitleCity.setText(cityName);
            binding.weatherTitle.txtvTitleUpdateTime.setText(updateTime);

            binding.weatherNow.txtvDegree.setText(degree);
            binding.weatherNow.txtvWeatherInfo.setText(weatherInfo);

            binding.weatherForecast.layoutForecast.removeAllViews();

            for (Forecast forecast : weather.forecastList) {
                View view = LayoutInflater.from(this).inflate(R.layout.weather_forecast_item, binding.weatherForecast.layoutForecast, false);

                view.<TextView>findViewById(R.id.date_text).setText(forecast.date);
                view.<TextView>findViewById(R.id.info_text).setText(forecast.more.info);
                view.<TextView>findViewById(R.id.max_text).setText(forecast.temperature.max);
                view.<TextView>findViewById(R.id.min_text).setText(forecast.temperature.min);

                binding.weatherForecast.layoutForecast.addView(view);
            }

            if (weather.aqi != null) {
                binding.weatherAqi.txtvAqi.setText(weather.aqi.city.aqi);
                binding.weatherAqi.txtvPm25.setText(weather.aqi.city.pm25);
            }

            binding.weatherSuggestion.txtvComfort.setText("舒适度: " + weather.suggestion.comfort.info);
            binding.weatherSuggestion.txtvCarWash.setText("洗车指数: " + weather.suggestion.carWash.info);
            binding.weatherSuggestion.txtvSport.setText("运动建议: " + weather.suggestion.sport.info);

            binding.scrollvWeather.setVisibility(View.VISIBLE);

            startService(new Intent(this, WeatherAutoUpdateService.class));
        } else {
            ToastUtils.showShort(MSG_GET_WEATHER_DETAILS_FAILED);
        }
    }

    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error(e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();

                runOnUiThread(() -> Glide.with(WeatherActivity.this).load(bingPic).into(binding.imgvBingPic));
            }
        });
    }
}
