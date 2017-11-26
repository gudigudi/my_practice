package com.gudigudigudi.mdtemplate.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.fragment.ChooseAreaFragment;
import com.gudigudigudi.mdtemplate.model.weather.Forecast;
import com.gudigudigudi.mdtemplate.model.weather.Weather;
import com.gudigudigudi.mdtemplate.service.WeatherAutoUpdateService;
import com.gudigudigudi.mdtemplate.util.HttpUtil;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity implements ChooseAreaFragment.OnFragmentInteractionListener {

    public DrawerLayout mDrawerLayout;
    private Button mBtnNav;
    private ImageView mIVBingPicImg;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private ScrollView mSVWeatherLayout;
    private TextView mTVTitleCity;
    private TextView mTVTitleUpdateTime;
    private TextView mTVDegreeText;
    private TextView mTVWeatherInfoText;
    private LinearLayout mLLForecastLayout;
    private TextView mTVaqiText;
    private TextView mTVpm25Text;
    private TextView mTVcomfortText;
    private TextView mTVcarWashText;
    private TextView mTVsportText;

    public String weatherId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);

        mIVBingPicImg = findViewById(R.id.bing_pic_img);
        mSwipeRefreshLayout = findViewById(R.id.swipe_reflesh);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mBtnNav = findViewById(R.id.nav_btn);
        mSVWeatherLayout = findViewById(R.id.weather_layout);
        mTVTitleCity = findViewById(R.id.title_city);
        mTVTitleUpdateTime = findViewById(R.id.title_update_time);
        mTVDegreeText = findViewById(R.id.degree_text);
        mTVWeatherInfoText = findViewById(R.id.weather_info_text);
        mLLForecastLayout = findViewById(R.id.forecast_layout);
        mTVaqiText = findViewById(R.id.aqi_text);
        mTVpm25Text = findViewById(R.id.pm25_text);
        mTVcomfortText = findViewById(R.id.comfort_text);
        mTVcarWashText = findViewById(R.id.car_wash_text);
        mTVsportText = findViewById(R.id.sport_text);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = preferences.getString("weather", null);
        if (weatherString != null) {
            Gson gson = new Gson();
            Weather weather = null;
            try {
                weather = gson.fromJson(new JSONObject(weatherString).getJSONArray("HeWeather").getJSONObject(0).toString(), Weather.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            weatherId = weather.basic.weatherId;
            showWeatherInfo(weather);
        } else {
            weatherId = getIntent().getStringExtra("weather_id");
            mSVWeatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }

        String bingPic = preferences.getString("bing_pic", null);
        if (bingPic != null) {
            Glide.with(this).load(bingPic).into(mIVBingPicImg);
        } else {
            loadBingPic();
        }

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestWeather(weatherId);
            }
        });

        mBtnNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void requestWeather(String weatherId) {
        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=bc0418b57b2d4918819d3974ac1285d9";

        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                Logger.json(responseText);
                String weatherContent = null;
                try {
                    weatherContent = new JSONObject(responseText).getJSONArray("HeWeather").getJSONObject(0).toString();
                } catch (JSONException e) {
                    Logger.e(e.toString());
                }
                final Weather weather = new Gson().fromJson(weatherContent, Weather.class);

                Logger.json(new Gson().toJson(weather));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && "ok".equals(weather.status)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather", responseText);
                            editor.apply();
                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
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

            mTVTitleCity.setText(cityName);
            mTVTitleUpdateTime.setText(updateTime);
            mTVDegreeText.setText(degree);
            mTVWeatherInfoText.setText(weatherInfo);

            mLLForecastLayout.removeAllViews();

            for (Forecast forecast : weather.forecastList) {
                View view = LayoutInflater.from(this).inflate(R.layout.weather_forecast_item, mLLForecastLayout, false);
                TextView dateText = (TextView) view.findViewById(R.id.date_text);
                TextView infoText = (TextView) view.findViewById(R.id.info_text);
                TextView maxText = (TextView) view.findViewById(R.id.max_text);
                TextView minText = (TextView) view.findViewById(R.id.min_text);

                dateText.setText(forecast.date);
                infoText.setText(forecast.more.info);
                maxText.setText(forecast.temperature.max);
                minText.setText(forecast.temperature.min);

                mLLForecastLayout.addView(view);
            }

            if (weather.aqi != null) {
                mTVaqiText.setText(weather.aqi.city.aqi);
                mTVpm25Text.setText(weather.aqi.city.pm25);
            }

            String comfort = "舒适度: " + weather.suggestion.comfort.info;
            String carWash = "洗车指数: " + weather.suggestion.carWash.info;
            String sport = "运动建议: " + weather.suggestion.sport.info;
            mTVcomfortText.setText(comfort);
            mTVcarWashText.setText(carWash);
            mTVsportText.setText(sport);

            mSVWeatherLayout.setVisibility(View.VISIBLE);

            Intent intent = new Intent(this, WeatherAutoUpdateService.class);
            startService(intent);
        } else {
            Toast.makeText(this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(mIVBingPicImg);
                    }
                });
            }
        });
    }
}
