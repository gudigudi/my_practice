package com.gudigudigudi.appdemojetpack.business.userProfile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private UserWebService webService;

    public LiveData<User> getUser(String userId) {

        final MutableLiveData<User> data = new MutableLiveData<>();

        webService.getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        return data;
    }
}
