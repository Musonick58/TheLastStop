package com.example.laststop.thelaststop;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import androidclient.AsyncDownload;

public class segnalazione extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segnalazione);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button invia = (Button) findViewById(R.id.invia);
        TextView info = (TextView) findViewById(R.id.descrizione);
        final String linea = getIntent().getExtras().getString("Linea");
        final String fermata = getIntent().getExtras().getString("Fermata");
        final String servizio = getIntent().getExtras().getString("serviceType");
        final String capoln = getIntent().getExtras().getString("Capolinea");
        final String ora = getIntent().getExtras().getString("Ora") + ":00";

        info.setText("Segnalazione ritardo sulla linea " + linea + " fermata di " + fermata + " delle ore " + ora + ". L'ora della segnalazione sara' rilevata automaticamente");

        invia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncDownload asd = new AsyncDownload();
                // Es -> DataRequest:Delay:linea:capolinea:fermata:servizio:ora(hh.mm.00):ritardo(hh.mm.00)
                String ritardo = systemTime();
                String richiesta = "DataRequest:SetDelay:" + linea + ":" + capoln + ":" + fermata + ":" + servizio + ":" + ora.replace(":", ".") + ":" + ritardo;
                Log.d("richiesta", richiesta);
                asd.execute(richiesta);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static String systemTime() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm");
        // you can get seconds by adding  "...:ss" to it
        date.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));

        String ritardo = date.format(currentLocalTime) + ".00";
        //int currentHour = cal.get(Calendar.HOUR);
        //int currentMinutes = cal.get(Calendar.MINUTE);
        //int currentSeconds = cal.get(Calendar.SECOND);
        ritardo = ritardo.replace(':', '.');
        Log.d("orario corrente", ritardo);
        return ritardo;
    }
}