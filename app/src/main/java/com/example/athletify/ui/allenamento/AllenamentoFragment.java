package com.example.athletify.ui.allenamento;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.athletify.R;
import com.google.android.material.card.MaterialCardView;

public class AllenamentoFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_allenamento,container,false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialCardView buttonWorkout1 = (MaterialCardView) view.findViewById(R.id.scheda_intermedio_entry);
        MaterialCardView buttonWorkout2 = (MaterialCardView) view.findViewById(R.id.scheda_intermedio);
        MaterialCardView buttonWorkout3 = (MaterialCardView) view.findViewById(R.id.scheda_avanzato);

        buttonWorkout1.setOnClickListener(this);
        buttonWorkout2.setOnClickListener(this);
        buttonWorkout3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String workout = "";
        switch (view.getId()) {
            case R.id.scheda_intermedio_entry:
                workout="Push-Pull-Legs-Workout";
                break;
            case R.id.scheda_intermedio:
                workout="Upper-Lower-Workout";
                break;
            case R.id.scheda_avanzato:
                workout="Full-Body-Workout";
                break;
            default:
                break;
        }

        Intent intent = new Intent(getContext(), wkWeekActivity.class);
        intent.putExtra("selectedWorkout",workout);
        startActivity(intent);
    }

}