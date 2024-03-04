package com.example.athletify.services;

import com.example.athletify.models.Esercizio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface EserciziService {
    @GET("exercises")
    Call<List<Esercizio>> getAll(@Header("x-rapidapi-key") String apiKey);
}
