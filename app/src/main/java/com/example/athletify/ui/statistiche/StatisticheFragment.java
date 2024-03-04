package com.example.athletify.ui.statistiche;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.athletify.R;
import com.example.athletify.models.Statistica;
import com.example.athletify.models.StatisticaBody;
import com.example.athletify.services.StatisticaService;
import com.example.athletify.utils.Constants;
import com.example.athletify.utils.StatisticaServiceLocator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticheFragment extends Fragment {

    private static final String TAG = "StatisticheFragment";

    private StatisticaService statisticaService;

    private StatisticaBody statisticaBody;

    private Statistica statisticaWithFitnessApi;

    private String eta;
    private String altezza;
    private String peso;
    private String genere;
    private String attivita;
    private String circ_collo;
    private String circ_fianchi;
    private String circ_vita;

    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_statistiche, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        main(this.view);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.statistiche_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.view_profile){
            viewProfile();
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        main(this.view);
    }

    public void viewProfile() {
        Intent intent = new Intent(getContext(), ProfiloActivity.class);
        startActivity(intent);
    }

    public void main(View view) {
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(Constants.STATISTICHE_SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        eta = sharedPref.getString(Constants.ETA_SHARED_PREFERENCES, "null");
        altezza = sharedPref.getString(Constants.ALTEZZA_SHARED_PREFERENCES, "null");
        peso = sharedPref.getString(Constants.PESO_SHARED_PREFERENCES, "null");
        genere = sharedPref.getString(Constants.GENERE_SHARED_PREFERENCES, "null");
        attivita = sharedPref.getString(Constants.ATTIVITA_SHARED_PREFERENCES, "null");
        circ_collo = sharedPref.getString(Constants.CIRC_COLLO_SHARED_PREFERENCES, "null");
        circ_fianchi = sharedPref.getString(Constants.CIRC_FIANCHI_SHARED_PREFERENCES, "null");
        circ_vita = sharedPref.getString(Constants.CIRC_VITA_SHARED_PREFERENCES, "null");

        TextView textViewBMI = view.findViewById(R.id.BMI);
        TextView textViewBFP = view.findViewById(R.id.BFP);
        TextView textViewLBM = view.findViewById(R.id.LBM);
        TextView textViewTDEE = view.findViewById(R.id.TDEE);

        statisticaBody = new StatisticaBody(altezza, peso, eta, genere, attivita, circ_collo, circ_fianchi, circ_vita);

        statisticaService = StatisticaServiceLocator.getInstance().getStatisticaServiceWithRetrofit();

        if (eta.equals("null") ||
                altezza.equals("null") ||
                peso.equals("null") ||
                genere.equals("null") ||
                attivita.equals("null") ||
                circ_collo.equals("null") ||
                circ_fianchi.equals("null") ||
                circ_vita.equals("null")) {
        } else {
            Call<Statistica> call = statisticaService.getAll(statisticaBody, Constants.STATISTICA_API_KEY);

            call.enqueue(new Callback<Statistica>() {
                @Override
                public void onResponse(Call<Statistica> call, Response<Statistica> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        statisticaWithFitnessApi = response.body();

                        editor.putFloat(Constants.BMI_SHARED_PREFERENCES, (float) statisticaWithFitnessApi.getBodyMassIndex().getValue());
                        String bmi = Double.toString(statisticaWithFitnessApi.getBodyMassIndex().getValue());
                        textViewBMI.setText(bmi);

                        editor.putFloat(Constants.BFP_SHARED_PREFERENCES, (float) statisticaWithFitnessApi.getBodyFatPercentage().getBmi().getValue());
                        String bfp = Double.toString(statisticaWithFitnessApi.getBodyFatPercentage().getBmi().getValue());
                        textViewBFP.setText(bfp);

                        editor.putFloat(Constants.LBM_SHARED_PREFERENCES, (float) statisticaWithFitnessApi.getLeanBodyMass().getBmi().getValue());
                        String lbm = Double.toString(statisticaWithFitnessApi.getLeanBodyMass().getBmi().getValue());
                        textViewLBM.setText(lbm);

                        editor.putFloat(Constants.TDEE_SHARED_PREFERENCES, (float) statisticaWithFitnessApi.getTotalDailyEnergyExpenditure().getBmi().getCalories().getValue());
                        String tdee = Double.toString(statisticaWithFitnessApi.getTotalDailyEnergyExpenditure().getBmi().getCalories().getValue());
                        textViewTDEE.setText(tdee);

                        editor.apply();
                    }
                }

                @Override
                public void onFailure(Call<Statistica> call, Throwable t) {
                    Log.d(TAG, t.getMessage());
                }
            });
        }
    }
}