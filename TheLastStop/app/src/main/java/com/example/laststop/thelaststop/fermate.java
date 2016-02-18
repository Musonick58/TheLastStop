package com.example.laststop.thelaststop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class fermate extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fermate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String line = getIntent().getExtras().getString("Linea");
        final String aux = line;
        setTitle("Linea " + line);
        ListView stop = (ListView)findViewById(R.id.listaFermate);

        ArrayList<String> list = new ArrayList<>();
        list.add("Via Torino0");
        list.add("Via Torino1");
        list.add("Via Torino2");
        list.add("Via Torino3");
        list.add("Via Torino4");
        list.add("Via Torino5");
        list.add("Via Torino6");
        list.add("Via Torino7");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        stop.setAdapter(adapter);


        stop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fermata = parent.getAdapter().getItem(position).toString();
                Intent x = new Intent(getApplicationContext(), orari.class);
                x.putExtra("Fermata", fermata);
                x.putExtra("Linea", aux);
                startActivity(x);
            }
        });
    }

}
