package com.example.athletify.ui.autenticazione;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.athletify.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_welcome);
    }

}
