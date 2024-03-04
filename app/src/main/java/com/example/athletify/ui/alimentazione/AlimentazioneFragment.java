package com.example.athletify.ui.alimentazione;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.athletify.R;
import com.example.athletify.utils.Constants;

public class AlimentazioneFragment extends Fragment {

    private double bmi;
    private double bfp;
    private double lbm;
    private double tdee;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_alimentazione, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        setHasOptionsMenu(true);

        SharedPreferences sharedPref = getActivity().getSharedPreferences(Constants.STATISTICHE_SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        bmi = sharedPref.getFloat(Constants.BMI_SHARED_PREFERENCES, 0);
        bfp = sharedPref.getFloat(Constants.BFP_SHARED_PREFERENCES, 0);
        lbm = sharedPref.getFloat(Constants.LBM_SHARED_PREFERENCES, 0);
        tdee = sharedPref.getFloat(Constants.TDEE_SHARED_PREFERENCES, 0);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textViewCalorieMantenimento = view.findViewById(R.id.calorieMantenimento);
        textViewCalorieMantenimento.setText(String.valueOf((int) tdee));

        TextView textViewProteineMantenimento = view.findViewById(R.id.proteineMantenimento);
        textViewProteineMantenimento.setText(String.valueOf(calcoloProteine(lbm)));

        TextView textViewCarboidratiMantenimento = view.findViewById(R.id.carboidratiMantenimento);
        textViewCarboidratiMantenimento.setText(String.valueOf(calcoloCarboidrati(tdee)));

        TextView textViewGrassiMantenimento = view.findViewById(R.id.grassiMantenimento);
        textViewGrassiMantenimento.setText(String.valueOf(calcoloGrassi(tdee)));

        TextView textViewCalorieDefinizione = view.findViewById(R.id.calorieDefinizione);
        textViewCalorieDefinizione.setText(String.valueOf(calorieDefinizione(tdee)));

        TextView textViewProteineDefinizione = view.findViewById(R.id.proteineDefinizione);
        textViewProteineDefinizione.setText(String.valueOf(calcoloProteineDefinizione(lbm)));

        TextView textViewGrassiDefinizione = view.findViewById(R.id.grassiDefinizione);
        textViewGrassiDefinizione.setText(String.valueOf(calcoloGrassiDefinizione(tdee)));

        TextView textViewCarboidratiDefinizione = view.findViewById(R.id.carboidratiDefinizione);
        textViewCarboidratiDefinizione.setText(String.valueOf(calcoloCarboidratiDefinizione(tdee)));

        TextView textViewCalorieMassa = view.findViewById(R.id.calorieMassa);
        textViewCalorieMassa.setText(String.valueOf(calorieMassa(tdee)));

        TextView textViewProteineMassa = view.findViewById(R.id.proteineMassa);
        textViewProteineMassa.setText(String.valueOf(calcoloProteineMassa(lbm)));

        TextView textViewCarboidratiMassa = view.findViewById(R.id.carboidratiMassa);
        textViewCarboidratiMassa.setText(String.valueOf(calcoloCarboidratiMassa(tdee)));

        TextView textViewGrassiMassa = view.findViewById(R.id.grassiMassa);
        textViewGrassiMassa.setText(String.valueOf(calcoloGrassiMassa(tdee)));

    }

    public int calcoloProteine(double lbm) {
        return (int) (lbm * 2.205 * 1.4);
    }

    public int calorieProteine(double lbm) {
        return calcoloProteine(lbm) * 4;
    }

    public int calorieGrassi(double tdee) {
        return (int) (tdee * 0.25);
    }

    public int calcoloGrassi(double tdee) {
        return calorieGrassi(tdee) / 9;
    }

    public int calcoloCarboidrati(double tdee) {
        return (int) ((tdee - (calorieGrassi(tdee) + calorieProteine(lbm))) / 4);
    }

    public int calorieDefinizione(double tdee) {
        return (int) (tdee * 0.8);
    }

    public int calcoloProteineDefinizione(double lbm) {
        return (int) (lbm * 2.205 * 1.6);
    }

    public int calorieProteineDefinizione(double lbm) {
        return calcoloProteine(lbm) * 4;
    }

    public int calorieGrassiDefinizione(double tdee) {
        return (int) (calorieDefinizione(tdee) * 0.25);
    }

    public int calcoloGrassiDefinizione(double tdee) {
        return calorieGrassiDefinizione(tdee) / 9;
    }

    public int calcoloCarboidratiDefinizione(double tdee) {
        return (calorieDefinizione(tdee) - (calorieGrassiDefinizione(tdee) + calorieProteineDefinizione(lbm))) / 4;
    }

    public int calorieMassa(double tdee) {
        return (int) (tdee * 1.1);
    }

    public int calcoloProteineMassa(double lbm) {
        return (int) (lbm * 2.205 * 1.3);
    }

    public int calorieProteineMass(double lbm) {
        return calcoloProteine(lbm) * 4;
    }

    public int calorieGrassiMassa(double tdee) {
        return (int) (calorieMassa(tdee) * 0.25);
    }

    public int calcoloGrassiMassa(double tdee) {
        return calorieGrassiMassa(tdee) / 9;
    }

    public int calcoloCarboidratiMassa(double tdee) {
        return (calorieMassa(tdee) - (calorieGrassiMassa(tdee) + calorieProteineMass(lbm))) / 4;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.alimentazione_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if(item.getItemId() == R.id.Consigli){
           Intent intent = new Intent(getContext(), SuggerimentiActivity.class);
           startActivity(intent);
       }
       return true;
    }
}