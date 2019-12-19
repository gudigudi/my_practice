package com.gudigudigudi.appdemojetpack.business.userProfile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class UserProfileViewModel extends ViewModel {

    private String userId;
    private MutableLiveData<User> user;
    private UserRepository userRepo;


    public LiveData<User> getUser() {
        if (user == null) {
            user = new MutableLiveData<>();
            user.setValue(new User(12, "java", "jdk8"));
        }

        return user;
    }

    public void setUserName(String name) {
        User u = user.getValue();
        u.setName(name);
        user.postValue(u);
    }
}
