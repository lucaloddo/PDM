package com.example.athletify.ui.statistiche;

import android.content.Context;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import com.example.athletify.R;

import com.example.athletify.utils.Constants;

import com.google.android.material.textfield.TextInputLayout;

public class ProfiloActivity extends AppCompatActivity {

    private Button salvaDati;

    private String eta;
    private String altezza;
    private String peso;
    private String genere;
    private String attivita;
    private String circ_collo;
    private String circ_fianchi;
    private String circ_vita;

    private static final String[] GENERE = new String[] {
            "Maschio", "Femmina"
    };

    private static final String[] ATTIVITA = new String[] {
            "Sedentario", "Leggero", "Moderato", "Attivo", "Molto attivo"
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        SharedPreferences sharedPref = this.getSharedPreferences(Constants.STATISTICHE_SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);

        eta = sharedPref.getString(Constants.ETA_SHARED_PREFERENCES, "null");
        if (!eta.equals("null")) {
            ((TextInputLayout) findViewById(R.id.eta)).getEditText().setText(eta);
        }

        altezza = sharedPref.getString(Constants.ALTEZZA_SHARED_PREFERENCES, "null");
        if (!altezza.equals("null")) {
            ((TextInputLayout) findViewById(R.id.altezza)).getEditText().setText(altezza);
        }

        peso = sharedPref.getString(Constants.PESO_SHARED_PREFERENCES, "null");
        if (!peso.equals("null")) {
            ((TextInputLayout) findViewById(R.id.peso)).getEditText().setText(peso);
        }

        genere = sharedPref.getString(Constants.GENERE_SHARED_PREFERENCES, "null");
        if (!genere.equals("null")) {
            if (genere.equals("male")) {
                genere = "Maschio";
            } else {
                genere = "Femmina";
            }
            ((TextInputLayout) findViewById(R.id.genere)).getEditText().setText(genere);
        }

        attivita = sharedPref.getString(Constants.ATTIVITA_SHARED_PREFERENCES, "null");
        if (!attivita.equals("null")) {
            if (attivita.equals("little")) {
                attivita = "Sedentario";
            } else if (attivita.equals("light")) {
                attivita = "Leggero";
            } else if (attivita.equals("moderate")) {
                attivita = "Moderato";
            } else if (attivita.equals("heavy")) {
                attivita = "Attivo";
            } else {
                attivita = "Molto attivo";
            }
            ((TextInputLayout) findViewById(R.id.attivita)).getEditText().setText(attivita);
        }
        circ_collo = sharedPref.getString(Constants.CIRC_COLLO_SHARED_PREFERENCES, "null");
        if (!circ_collo.equals("null")) {
            ((TextInputLayout) findViewById(R.id.collo)).getEditText().setText(circ_collo);
        }
        circ_fianchi = sharedPref.getString(Constants.CIRC_FIANCHI_SHARED_PREFERENCES, "null");
        if (!circ_fianchi.equals("null")) {
            ((TextInputLayout) findViewById(R.id.fianchi)).getEditText().setText(circ_fianchi);
        }
        circ_vita = sharedPref.getString(Constants.CIRC_VITA_SHARED_PREFERENCES, "null");
        if (!circ_vita.equals("null")) {
            ((TextInputLayout) findViewById(R.id.vita)).getEditText().setText(circ_vita);
        }

        ArrayAdapter<String> genereAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, GENERE);
        AutoCompleteTextView genereTextView = (AutoCompleteTextView)
                findViewById(R.id.genere_ac);
        genereTextView.setAdapter(genereAdapter);

        ArrayAdapter<String> attivitaAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, ATTIVITA);
        AutoCompleteTextView attivitaTextView = (AutoCompleteTextView)
                findViewById(R.id.attivita_ac);
        attivitaTextView.setAdapter(attivitaAdapter);

        SharedPreferences.Editor editor = sharedPref.edit();

        salvaDati = findViewById(R.id.SalvaDati);
        salvaDati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eta = ((TextInputLayout) findViewById(R.id.eta)).getEditText().getText().toString();
                editor.putString(Constants.ETA_SHARED_PREFERENCES, eta);

                altezza = ((TextInputLayout) findViewById(R.id.altezza)).getEditText().getText().toString();
                editor.putString(Constants.ALTEZZA_SHARED_PREFERENCES, altezza);

                peso = ((TextInputLayout) findViewById(R.id.peso)).getEditText().getText().toString();
                editor.putString(Constants.PESO_SHARED_PREFERENCES, peso);

                genere = ((TextInputLayout) findViewById(R.id.genere)).getEditText().getText().toString();
                if (genere.equals("Maschio")) {
                    genere = "male";
                } else {
                    genere = "female";
                }
                editor.putString(Constants.GENERE_SHARED_PREFERENCES, genere);

                attivita = ((TextInputLayout) findViewById(R.id.attivita)).getEditText().getText().toString();
                if (attivita.equals("Sedentario")) {
                    attivita = "little";
                } else if (attivita.equals("Leggero")) {
                    attivita = "light";
                } else if (attivita.equals("Moderato")) {
                    attivita = "moderate";
                } else if (attivita.equals("Attivo")) {
                    attivita = "heavy";
                } else {
                    attivita = "veryheavy";
                }
                editor.putString(Constants.ATTIVITA_SHARED_PREFERENCES, attivita);

                circ_collo = ((TextInputLayout) findViewById(R.id.collo)).getEditText().getText().toString();
                editor.putString(Constants.CIRC_COLLO_SHARED_PREFERENCES, circ_collo);

                circ_fianchi = ((TextInputLayout) findViewById(R.id.fianchi)).getEditText().getText().toString();
                editor.putString(Constants.CIRC_FIANCHI_SHARED_PREFERENCES, circ_fianchi);

                circ_vita = ((TextInputLayout) findViewById(R.id.vita)).getEditText().getText().toString();
                editor.putString(Constants.CIRC_VITA_SHARED_PREFERENCES, circ_vita);

                editor.apply();

                onBackPressed();
            }
        });
    }
}
