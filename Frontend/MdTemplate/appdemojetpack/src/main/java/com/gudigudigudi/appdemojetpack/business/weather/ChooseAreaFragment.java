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

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gudigudigudi.appdemojetpack.business.weather.room.model.City;
import com.gudigudigudi.appdemojetpack.business.weather.room.model.County;
import com.gudigudigudi.appdemojetpack.business.weather.room.model.Province;
import com.gudigudigudi.appdemojetpack.databinding.FragmentChooseAreaBinding;
import com.gudigudigudi.commonlib.base.BaseFragment;
import com.gudigudigudi.commonlib.constants.LogTag;
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

    private static final int LEVEL_PROVINCE = 0;
    private static final int LEVEL_CITY = 1;
    private static final int LEVEL_COUNTY = 2;

    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<>();

    private List<Province> provinceList;
    private List<City> cityList;
    private List<County> countyList;

    private Province currentProvince;
    private City currentCity;
    private int currentLevel;

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
            if (currentLevel == LEVEL_PROVINCE) {
                currentProvince = provinceList.get(i);
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        queryCities();
                        return null;
                    }
                }.execute();
            } else if (currentLevel == LEVEL_CITY) {
                currentCity = cityList.get(i);
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        queryCounties();
                        return null;
                    }
                }.execute();
            } else if (currentLevel == LEVEL_COUNTY) {
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
            log.debug(LogTag.LOG_VIEW_IS_CLICKED, "button back");
            if (currentLevel == LEVEL_COUNTY) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        queryCities();
                        return null;
                    }
                }.execute();
            } else if (currentLevel == LEVEL_CITY) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        queryProvinces();
                        return null;
                    }
                }.execute();
            } else if (currentLevel == LEVEL_PROVINCE) {
                binding.btnBack.setVisibility(View.GONE);
            }
        });

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                queryProvinces();
                return null;
            }
        }.execute();

        getActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());

    }

    private void queryProvinces() {
        getActivity().runOnUiThread(() -> {
            binding.txtvTitle.setText("中国");
            binding.btnBack.setVisibility(View.GONE);
        });

        provinceList = appDatabase.provinceDao().getAll();

        if (!provinceList.isEmpty()) {
            log.debug("query from local db.");
            dataList.clear();

            for (Province province : provinceList) {
                dataList.add(province.getName());
            }
            getActivity().runOnUiThread(() -> {
                adapter.notifyDataSetChanged();
                binding.listView.setSelection(0);
            });
            currentLevel = LEVEL_PROVINCE;
        } else {
            log.debug("query from server api.");
            queryFromServer(API_URL, "province");
        }
    }

    private void queryCities() {
        getActivity().runOnUiThread(() -> {
            binding.txtvTitle.setText(currentProvince.getName());
            binding.btnBack.setVisibility(View.VISIBLE);
        });

        cityList = appDatabase.cityDao().getCityInProvince(currentProvince.getCode());

        if (cityList.size() > 0) {
            dataList.clear();

            for (City city : cityList) {
                dataList.add(city.getName());
            }
            getActivity().runOnUiThread(() -> {
                adapter.notifyDataSetChanged();
                binding.listView.setSelection(0);
            });
            currentLevel = LEVEL_CITY;
        } else {
            int provinceCode = currentProvince.getCode();
            String address = API_URL + provinceCode;
            log.debug(address);
            queryFromServer(address, "city");
        }
    }

    private void queryCounties() {
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
            currentLevel = LEVEL_COUNTY;
        } else {
            int provinceCode = currentProvince.getCode();
            int cityCode = currentCity.getCode();
            String address = API_URL + provinceCode + "/" + cityCode;
            queryFromServer(address, "county");
        }
    }

    private void queryFromServer(String address, final String queryLevelCode) {
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(() -> ToastUtils.showShort("Fail to load"));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                Gson gson = new Gson();
                log.debug(queryLevelCode);

                if ("province".equals(queryLevelCode)) {
                    List<Province> provinces = gson.fromJson(responseBody, new TypeToken<List<Province>>() {
                    }.getType());
                    appDatabase.provinceDao().insertAll(provinces);
                    log.debug("query server api: province");
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            queryProvinces();
                            return null;
                        }
                    }.execute();
                } else if ("city".equals(queryLevelCode)) {
                    List<City> cities = gson.fromJson(responseBody, new TypeToken<List<City>>() {
                    }.getType());
                    for (City city : cities) {
                        city.setProvinceId(currentProvince.getCode());
                    }
                    appDatabase.cityDao().insertAll(cities);
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            queryCities();
                            return null;
                        }
                    }.execute();
                } else if ("county".equals(queryLevelCode)) {
                    List<County> counties = gson.fromJson(responseBody, new TypeToken<List<County>>() {
                    }.getType());
                    for (County county : counties) {
                        county.setCityId(currentCity.getCode());
                    }

                    appDatabase.countyDao().insertAll(counties);
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            queryCounties();
                            return null;
                        }
                    }.execute();
                }
            }
        });
    }
}
