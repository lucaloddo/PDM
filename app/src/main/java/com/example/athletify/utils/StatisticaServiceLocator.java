package com.example.athletify.utils;

import com.example.athletify.services.StatisticaService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatisticaServiceLocator {

    private static StatisticaServiceLocator instance = null;

    private StatisticaServiceLocator() {}

    public static StatisticaServiceLocator getInstance() {
        if (instance == null) {
            synchronized(StatisticaServiceLocator.class) {
                instance = new StatisticaServiceLocator();
            }
        }
        return instance;
    }

    public StatisticaService getStatisticaServiceWithRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.STATISTICA_API_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(StatisticaService.class);
    }
}
