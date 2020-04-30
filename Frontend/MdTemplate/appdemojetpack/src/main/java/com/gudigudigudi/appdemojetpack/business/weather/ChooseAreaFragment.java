package com.gudigudigudi.appdemojetpack.business.weather;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.reflect.TypeToken;
import com.gudigudigudi.appdemojetpack.business.weather.room.model.City;
import com.gudigudigudi.appdemojetpack.business.weather.room.model.County;
import com.gudigudigudi.appdemojetpack.business.weather.room.model.Province;
import com.gudigudigudi.appdemojetpack.databinding.FragmentChooseAreaBinding;
import com.gudigudigudi.commonlib.base.BaseFragment;
import com.gudigudigudi.commonlib.util.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

@Slf4j
public class ChooseAreaFragment extends BaseFragment {
    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<>();

    private List<Province> provinceList;
    private List<City> cityList;
    private List<County> countyList;

    private Province currentProvince;
    private City currentCity;
    private QueryLevelCode currentLevelCode;

    private enum QueryLevelCode {
        CITY,
        PROVINCE,
        COUNTRY
    }

    private AppDatabase appDatabase;

    private final String API_URL = "http://guolin.tech/api/china";
    private FragmentChooseAreaBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChooseAreaBinding.inflate(inflater, container, false);

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
        binding.listView.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appDatabase = Room.databaseBuilder(getContext(), AppDatabase.class, "app").build();

        binding.listView.setOnItemClickListener((view12, view1, i, l) -> {
            switch (currentLevelCode) {
                case CITY:
                    currentCity = cityList.get(i);
                    new QueryTask().execute(QueryLevelCode.COUNTRY);
                case PROVINCE:
                    currentProvince = provinceList.get(i);
                    new QueryTask().execute(QueryLevelCode.CITY);
                case COUNTRY:
                    String weatherId = countyList.get(i).getWeatherId();
                    if (getActivity() instanceof ChooseAreaActivity) {
                        Intent intent = new Intent(getActivity(), WeatherActivity.class);
                        intent.putExtra("weather_id", weatherId);
                        startActivity(intent);
                        getActivity().finish();
                    } else if (getActivity() instanceof WeatherActivity) {
                        WeatherActivity activity = (WeatherActivity) getActivity();
                        activity.binding.drawerLayout.closeDrawers();
                        activity.binding.swipeRefleshLayout.setRefreshing(true);
                        activity.requestWeather(weatherId);
                    }
            }
        });

        binding.btnBack.setOnClickListener(view13 -> {
            switch (currentLevelCode) {
                case CITY:
                    new QueryTask().execute(QueryLevelCode.PROVINCE);
                case PROVINCE:
                    binding.btnBack.setVisibility(View.GONE);
                case COUNTRY:
                    new QueryTask().execute(QueryLevelCode.CITY);
            }
        });

        new QueryTask().execute(QueryLevelCode.PROVINCE);
        getActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
    }

    /**
     * 查询数据：city/province/country
     */
    private class QueryTask extends AsyncTask<QueryLevelCode, Void, Void> {
        @Override
        protected Void doInBackground(QueryLevelCode... queryLevelCodes) {
            QueryLevelCode code = queryLevelCodes[0];
            switch (code) {
                case CITY:
                    getActivity().runOnUiThread(() -> {
                        binding.txtvTitle.setText(currentProvince.getName());
                        binding.btnBack.setVisibility(View.VISIBLE);
                    });

                    cityList = appDatabase.cityDao().getCityInProvince(currentProvince.getCode());

                    if (!cityList.isEmpty()) {
                        dataList.clear();

                        for (City city : cityList) {
                            dataList.add(city.getName());
                        }
                        getActivity().runOnUiThread(() -> {
                            adapter.notifyDataSetChanged();
                            binding.listView.setSelection(0);
                        });
                        currentLevelCode = code;
                    } else {
                        queryFromServer(API_URL + currentProvince.getCode(), code);
                    }
                    break;
                case PROVINCE:
                    getActivity().runOnUiThread(() -> {
                        binding.txtvTitle.setText("中国");
                        binding.btnBack.setVisibility(View.GONE);
                    });

                    provinceList = appDatabase.provinceDao().getAll();

                    if (!provinceList.isEmpty()) {
                        dataList.clear();

                        for (Province province : provinceList) {
                            dataList.add(province.getName());
                        }
                        getActivity().runOnUiThread(() -> {
                            adapter.notifyDataSetChanged();
                            binding.listView.setSelection(0);
                        });
                        currentLevelCode = code;
                    } else {
                        queryFromServer(API_URL, code);
                    }
                    break;
                case COUNTRY:
                    getActivity().runOnUiThread(() -> {
                        binding.txtvTitle.setText(currentCity.getName());
                        binding.btnBack.setVisibility(View.VISIBLE);
                    });

                    countyList = appDatabase.countyDao().getCountyInCity(currentCity.getCode());

                    if (!countyList.isEmpty()) {
                        dataList.clear();
                        for (County county : countyList) {
                            dataList.add(county.getName());
                        }
                        getActivity().runOnUiThread(() -> {
                            adapter.notifyDataSetChanged();
                            binding.listView.setSelection(0);
                        });
                        currentLevelCode = code;
                    } else {
                        queryFromServer(API_URL + currentProvince.getCode() + "/" + currentCity.getCode(), code);
                    }
                    break;
                default:
                    break;
            }
            return null;
        }
    }

    private void queryFromServer(String address, QueryLevelCode code) {
        HttpUtil.sendRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(() -> ToastUtils.showShort("Fail to load"));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();

                switch (code) {
                    case CITY:
                        List<City> cities = GsonUtils.fromJson(responseBody, new TypeToken<List<City>>() {
                        }.getType());
                        for (City city : cities) {
                            city.setProvinceId(currentProvince.getCode());
                        }
                        appDatabase.cityDao().insertAll(cities);
                    case PROVINCE:
                        List<Province> provinces = GsonUtils.fromJson(responseBody, new TypeToken<List<Province>>() {
                        }.getType());
                        appDatabase.provinceDao().insertAll(provinces);
                    case COUNTRY:
                        List<County> counties = GsonUtils.fromJson(responseBody, new TypeToken<List<County>>() {
                        }.getType());
                        for (County county : counties) {
                            county.setCityId(currentCity.getCode());
                        }
                        appDatabase.countyDao().insertAll(counties);
                }
                new QueryTask().execute(code);
            }
        });
    }
}
