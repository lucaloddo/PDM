package com.example.athletify.utils;

import com.example.athletify.services.EserciziService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EserciziServiceLocator {

    private static EserciziServiceLocator instance = null;

    private EserciziServiceLocator() {}

    public static EserciziServiceLocator getInstance() {
        if (instance == null) {
            synchronized(EserciziServiceLocator.class) {
                instance = new EserciziServiceLocator();
            }
        }
        return instance;
    }

    public EserciziService getEserciziServiceWithRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.ESERCIZI_API_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(EserciziService.class);
    }
}
