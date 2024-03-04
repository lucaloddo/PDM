package com.example.athletify.viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.athletify.models.LoginResponse;
import com.example.athletify.repositories.user.IUserRepository;
import com.example.athletify.utils.Constants;

public class UserViewModel extends AndroidViewModel {

    private MutableLiveData<LoginResponse> loginResultMutableLiveData;

    private final IUserRepository userRepository;

    private boolean isLogged;

    public UserViewModel(Application application, IUserRepository userRepository) {
        super(application);
        this.userRepository = userRepository;
        this.isLogged = false;
    }

    public MutableLiveData<LoginResponse> getUser(String username, String password) {
        if (!isLogged) {
            login(username, password);
        }
        return loginResultMutableLiveData;
    }

    public MutableLiveData<LoginResponse> createUser(String username, String password) {
        if (!isLogged) {
            register(username, password);
        }
        return loginResultMutableLiveData;
    }

    private void login(String username, String password) {
        loginResultMutableLiveData = userRepository.login(username, password);
    }

    private void register(String username, String password) {
        loginResultMutableLiveData = userRepository.register(username, password);
    }

    public void setLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public String getAuthenticationToken() {
        SharedPreferences sharedPref = getApplication().getSharedPreferences(Constants.AUTHENTICATION_SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString(Constants.AUTHENTICATION_TOKEN, null);
    }

    public String getUserId() {
        SharedPreferences sharedPref = getApplication().getSharedPreferences(Constants.AUTHENTICATION_SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString(Constants.USER_ID, null);
    }
}
