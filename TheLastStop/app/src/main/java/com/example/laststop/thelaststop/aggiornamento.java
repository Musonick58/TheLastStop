package com.example.laststop.thelaststop;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class aggiornamento extends ActionBarActivity {
    ArrayList<HashMap> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiornamento);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myListAdapter adapter;
        ArrayList<String> orari = getIntent().getExtras().getStringArrayList("timetable");
        ArrayList<String> ritardi;
        ArrayList<String> fermate;
        ListView update = (ListView)findViewById(R.id.listaAggiornata);

        popola();

        adapter = new myListAdapter(this, list);

        update.setAdapter(adapter);





    }
    public void popola(){
        list = new ArrayList<HashMap>();
        int i;
        for( i = 0; i < arrivi.size(); i++) {
            HashMap temp = new HashMap();
            temp.put("Ora Arrivo", orari.get(i));
            temp.put("Ora Partenza", .get(i));
            temp.put("Ritardo", listaRitardi.get(i));
            list.add(temp);
        }
    }
}
