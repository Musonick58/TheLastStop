package com.example.laststop.thelaststop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;


public class orari extends ActionBarActivity {
    private ArrayList<HashMap> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orari);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String linea = getIntent().getExtras().getString("Linea");
        ListView orari = (ListView)findViewById(R.id.listaOrari);

        populate(linea);

        myListAdapter adapter = new myListAdapter(this,list);
        orari.setAdapter(adapter);
        setTitle("Linea " + linea);

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
}
