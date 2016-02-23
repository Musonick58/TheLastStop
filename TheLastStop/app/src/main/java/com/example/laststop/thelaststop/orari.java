package com.example.laststop.thelaststop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;


public class orari extends ActionBarActivity {
    private ArrayList<HashMap> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orari);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String linea = getIntent().getExtras().getString("Linea");
        String fermata = getIntent().getExtras().getString("Fermata");
        final String aux1 = linea;
        final String aux2 = fermata;
        setTitle("Linea " + linea + " fermata di " + fermata);

        ListView orari = (ListView)findViewById(R.id.listaOrari);

        populate(linea);

        myListAdapter adapter = new myListAdapter(this,list);
        /*adapter.getView();*/
        orari.setAdapter(adapter);

        /* aggiungere listener su click della lista */
        orari.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nuovo = new Intent (getApplicationContext(),segnalazione.class);
                String ora = parent.getAdapter().getItem(position).toString();
                nuovo.putExtra("Linea",aux1);
                nuovo.putExtra("Fermata", aux2);
                nuovo.putExtra("Ora", ora);
                startActivity(nuovo);
            }
        });
    }

    public void populate(String s){
        list = new ArrayList<HashMap>();
        int i;
        for( i = 0; i<15; i++) {
            HashMap temp = new HashMap();
            temp.put("Ora Arrivo", "18:20");
            temp.put("Ora Partenza", "18:22");
            temp.put("Ritardo", "2 min");
            list.add(temp);
        }
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

    public String systemTime() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm a");
        // you can get seconds by adding  "...:ss" to it
        date.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));

        String localTime = date.format(currentLocalTime) + ":00";
        //int currentHour = cal.get(Calendar.HOUR);
        //int currentMinutes = cal.get(Calendar.MINUTE);
        //int currentSeconds = cal.get(Calendar.SECOND);
        Log.d("orario corrente:", localTime);
        return localTime;
    }
}
