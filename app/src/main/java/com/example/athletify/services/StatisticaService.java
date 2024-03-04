package com.example.athletify.services;

import com.example.athletify.models.Statistica;
import com.example.athletify.models.StatisticaBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface StatisticaService {
    @POST("fitness")
    Call<Statistica> getAll(@Body StatisticaBody statisticaBody,
                            @Header("x-rapidapi-key") String apiKey);
}
