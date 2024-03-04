package com.example.athletify.ui.allenamento;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.athletify.R;
import com.example.athletify.models.wkExercise;

import java.util.ArrayList;

public class wkDayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wk_daily);

        Bundle bundle = getIntent().getExtras();
        ArrayList<wkExercise> listaEsercizi = bundle.getParcelableArrayList("listEs");

        RecyclerView recyclerView = findViewById(R.id.wkList_list);
        wkExerciseRecyclerViewAdapter wkAdapterRec = new wkExerciseRecyclerViewAdapter(listaEsercizi, new wkExerciseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(wkExercise esercizio) { }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(wkAdapterRec);

        setTitle(getString(R.string.titoloActivitySchedaAllenamento));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}