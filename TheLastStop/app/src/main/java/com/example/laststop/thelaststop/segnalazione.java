package com.example.laststop.thelaststop;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class segnalazione extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segnalazione);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button invia = (Button)findViewById(R.id.invia);
        TextView info = (TextView)findViewById(R.id.descrizione);
        String linea = getIntent().getExtras().getString("Linea");
        String fermata = getIntent().getExtras().getString("Fermata");
        String ora = getIntent().getExtras().getString("Ora").substring(15,19);

        info.setText("Segnalazione ritardo sulla linea " + linea + " fermata di " + fermata + " delle ore " + ora + ". L'ora della segnalazione sara' rilevata automaticamente");

        invia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*JOHN qui va la roba per inviare la segnalazione */
            }
        });

    }

}
