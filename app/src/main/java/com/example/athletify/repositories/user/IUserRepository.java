package com.example.athletify.repositories.user;

import androidx.lifecycle.MutableLiveData;

import com.example.athletify.models.LoginResponse;

public interface IUserRepository {
    MutableLiveData<LoginResponse> login(String email, String password);
    MutableLiveData<LoginResponse> register(String email, String password);
}