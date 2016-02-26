package com.example.laststop.thelaststop;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class aggiornamento extends ActionBarActivity {
    ArrayList<HashMap> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("metodo", "Sono entrato su onCreate di aggiornamento.java");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiornamento);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myListAdapter adapter;

        // Es timetable -> [\"nomeFermata#oraArrivo#oraPartenza\"]
        ArrayList<String> timetable = getIntent().getExtras().getStringArrayList("TimeTable");
        ArrayList<String> nFermate = new ArrayList<>();
        ArrayList<String> hArrivi = new ArrayList<>();
        ArrayList<String> hPartenze = new ArrayList<>();
        for (int i = 0; i < timetable.size(); i++){
            nFermate.add(timetable.get(i).split("#")[0]);
            hArrivi.add(timetable.get(i).split("#")[1]);
            hPartenze.add(timetable.get(i).split("#")[2]);
        }

        String linea = getIntent().getExtras().getString("Linea");
        String servizio = getIntent().getExtras().getString("Servizio");
        String capoln = getIntent().getExtras().getString("Capolinea");
        String fermata = getIntent().getExtras().getString("Fermata");
        String orario = getIntent().getExtras().getString("Orario");
        ListView update = (ListView)findViewById(R.id.listaAggiornata);

        populate(nFermate, hArrivi, hPartenze);

        adapter = new myListAdapter(this, list);
        update.setAdapter(adapter);


    }

    public void populate(ArrayList<String> fermate, ArrayList<String> arrivi, ArrayList<String> partenze){
        Log.d("metodo", "Sono entrato su populate di aggiornamento.java");
        list = new ArrayList<HashMap>();
        int i;
        Log.d("POPULATE arrivi", arrivi.toString());
        Log.d("POPULATE partenze", partenze.toString());
        for( i = 0; i < arrivi.size(); i++) {
            HashMap temp = new HashMap();
            temp.put("Fermata", fermate.get(i));
            temp.put("Ora arrivo", arrivi.get(i));
            temp.put("Partenza prevista", partenze.get(i));
            list.add(temp);
        }
        Log.d("POPULATE list", list.toString());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("metodo","Sono entrato su onOptionsItemSelected di aggiornamenti.java");
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*public void populate(ArrayList<String> arrivi, ArrayList<String> partenze){
        Log.d("metodo","Sono entrato su populate di orari.java");
        list = new ArrayList<HashMap>();
        int i;
        for( i = 0; i < arrivi.size(); i++) {
            HashMap temp = new HashMap();
            temp.put("Ora Arrivo", arrivi.get(i));
            temp.put("Ora Partenza", partenze.get(i));
            temp.put("Ritardo", "" + Funzioni.diffOrari(arrivi.get(i), partenze.get(i)));
            list.add(temp);
        }
    }*/
}
