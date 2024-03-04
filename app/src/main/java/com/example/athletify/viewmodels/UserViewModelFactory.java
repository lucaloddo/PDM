package com.example.athletify.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.athletify.repositories.user.IUserRepository;

public class UserViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;
    private final IUserRepository iUserRepository;

    public UserViewModelFactory(Application application, IUserRepository iUserRepository) {
        this.application = application;
        this.iUserRepository = iUserRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UserViewModel(application, iUserRepository);
    }
}
