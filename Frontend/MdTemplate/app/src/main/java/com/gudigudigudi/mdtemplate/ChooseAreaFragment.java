package com.gudigudigudi.mdtemplate;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gudigudigudi.mdtemplate.db.AppDatabase;
import com.gudigudigudi.mdtemplate.db.City;
import com.gudigudigudi.mdtemplate.db.County;
import com.gudigudigudi.mdtemplate.db.Province;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChooseAreaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChooseAreaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChooseAreaFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private static final int LEVEL_PROVINCE = 0;
    private static final int LEVEL_CITY = 1;
    private static final int LEVEL_COUNTY = 2;

    private AlertDialog progressAlertDialog;
    private TextView titleText;
    private Button btn_back;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<>();

    private List<Province> provinceList;
    private List<City> cityList;
    private List<County> countyList;

    private Province currentProvince;
    private City currentCity;
    private int currentLevel;

    private AppDatabase appDatabase;

    public ChooseAreaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ChooseAreaFragment.
     */
    public static ChooseAreaFragment newInstance(String param1, String param2) {
        ChooseAreaFragment fragment = new ChooseAreaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_area, container, false);

        titleText = (TextView) view.findViewById(R.id.title_text);
        btn_back = (Button) view.findViewById(R.id.back_button);
        listView = (ListView) view.findViewById(R.id.list_view);

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        appDatabase = Room.databaseBuilder(getContext(),
                AppDatabase.class, "app").build();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int i, long l) {
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
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Logger.d(LogUtil.LOG_VIEW_IS_CLICKED, "button back");
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
                    btn_back.setVisibility(View.GONE);
                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                queryProvinces();
            }
        }).start();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void queryProvinces() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                titleText.setText("中国");
                btn_back.setVisibility(View.GONE);
            }
        });

        provinceList = appDatabase.provinceDao().getAll();

        if (provinceList.size() > 0) {
            Logger.d("query from local db.");
            dataList.clear();

            for (Province province : provinceList) {
                dataList.add(province.getName());
                Gson gson = new Gson();
                Logger.json(gson.toJson(province));
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                    listView.setSelection(0);
                }
            });
            currentLevel = LEVEL_PROVINCE;
        } else {
            Logger.d("query from server api.");
            String address = "http://guolin.tech/api/china";
            queryFromServer(address, "province");
        }
    }

    private void queryCities() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                titleText.setText(currentProvince.getName());
                btn_back.setVisibility(View.VISIBLE);
            }
        });

        cityList = appDatabase.cityDao().getCityInProvince(currentProvince.getCode());

        if (cityList.size() > 0) {
            dataList.clear();

            for (City city : cityList) {
                dataList.add(city.getName());
                Gson gson = new Gson();
                Logger.json(gson.toJson(city));
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                    listView.setSelection(0);
                }
            });
            currentLevel = LEVEL_CITY;
        } else {
            int provinceCode = currentProvince.getCode();
            String address = "http://guolin.tech/api/china/" + provinceCode;
            Logger.d(address);
            queryFromServer(address, "city");
        }
    }

    private void queryCounties() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                titleText.setText(currentCity.getName());
                btn_back.setVisibility(View.VISIBLE);
            }
        });

        countyList = appDatabase.countyDao().getCountyInCity(currentCity.getCode());

        if (countyList.size() > 0) {
            dataList.clear();
            for (County county : countyList) {
                dataList.add(county.getName());
                Gson gson = new Gson();
                Logger.json(gson.toJson(county));
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                    listView.setSelection(0);
                }
            });
            currentLevel = LEVEL_COUNTY;
        } else {
            int provinceCode = currentProvince.getCode();
            int cityCode = currentCity.getCode();
            String address = "http://guolin.tech/api/china/" + provinceCode + "/" + cityCode;
            queryFromServer(address, "county");
        }
    }

    private void queryFromServer(String address, final String queryLevelCode) {
        // show progressDialog.

        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // close progressDialog.
                        Toast.makeText(getContext(), "Fail to load", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                Logger.json(responseBody);
                Gson gson = new Gson();
                Logger.d(queryLevelCode);

                if ("province".equals(queryLevelCode)) {
                    List<Province> provinces = gson.fromJson(responseBody, new TypeToken<List<Province>>() {
                    }.getType());
                    appDatabase.provinceDao().insertAll(provinces);
                    Logger.d("query server api: province");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            queryProvinces();
                        }
                    }).start();
                } else if ("city".equals(queryLevelCode)) {
                    List<City> cities = gson.fromJson(responseBody, new TypeToken<List<City>>() {
                    }.getType());
                    for (City city : cities) {
                        Logger.json(gson.toJson(city));
                        city.setProvinceId(currentProvince.getCode());
                    }
                    appDatabase.cityDao().insertAll(cities);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            queryCities();
                        }
                    }).start();
                } else if ("county".equals(queryLevelCode)) {
                    List<County> counties = gson.fromJson(responseBody, new TypeToken<List<County>>() {
                    }.getType());
                    for (County county : counties) {
                        Logger.json(gson.toJson(counties));
                        county.setCityId(currentCity.getCode());
                    }

                    appDatabase.countyDao().insertAll(counties);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            queryCounties();
                        }
                    }).start();
                }
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
