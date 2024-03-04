package com.example.athletify.ui.allenamento;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.athletify.R;

public class wkWeekActivity extends AppCompatActivity {

    private static String WorkoutString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wk_weekly);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String selectedWorkoutString = bundle.getString("selectedWorkout");
            setWorkoutString(selectedWorkoutString);

            TextView textView = findViewById(R.id.selectedWorkout);
            textView.setText(getWorkoutString());

        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.wkDaysListFrag, new wkDaysListFragmentListView());
        ft.commit();

        setTitle(getString(R.string.titoloActivitySelezionaGiornoAllenamento));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void setWorkoutString(String wk) {
        WorkoutString = wk;
    }

    public static String getWorkoutString() {
        return WorkoutString;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}