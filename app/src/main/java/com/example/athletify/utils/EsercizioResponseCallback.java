package com.example.athletify.utils;

import com.example.athletify.models.Esercizio;

import java.util.List;

public interface EsercizioResponseCallback {
    void onResponse(List<Esercizio> esercizioList);
    void onFailure(String msg);
}
