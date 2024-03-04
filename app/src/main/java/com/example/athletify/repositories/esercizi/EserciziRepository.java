package com.example.athletify.repositories.esercizi;

import androidx.annotation.NonNull;

import com.example.athletify.services.EserciziService;
import com.example.athletify.models.Esercizio;
import com.example.athletify.utils.Constants;
import com.example.athletify.utils.EsercizioResponseCallback;
import com.example.athletify.utils.EserciziServiceLocator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class EserciziRepository implements IEserciziRepository {

    private final EserciziService eserciziService;
    private final EsercizioResponseCallback responseCallback;

    public EserciziRepository(EsercizioResponseCallback responseCallback) {
        this.eserciziService = EserciziServiceLocator.getInstance().getEserciziServiceWithRetrofit();
        this.responseCallback = responseCallback;
    }

    @Override
    public void fetchEsercizi() {
        Call<List<Esercizio>> call = eserciziService.getAll(Constants.ESERCIZI_API_KEY);

        call.enqueue(new Callback<List<Esercizio>>() {
            @Override
            public void onResponse(@NonNull Call<List<Esercizio>> call, @NonNull retrofit2.Response<List<Esercizio>> response) {
                if (response.body() != null && response.isSuccessful()) {
                    List<Esercizio> esercizioList = response.body();
                    responseCallback.onResponse(esercizioList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Esercizio>> call, @NonNull Throwable t) {
                responseCallback.onFailure(t.getMessage());
            }
        });
    }
}
