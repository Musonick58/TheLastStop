package com.example.laststop.thelaststop;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import com.example.laststop.thelaststop.R;

import java.util.ArrayList;

public class lineaBat extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<String> lineebat = new ArrayList<String>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linea_bat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lineebat.add("Via Acqua");
        lineebat.add("Via x");
        lineebat.add("San Marco");
        lineebat.add("Via paperepoupi");
        lineebat.add("Via altra acqua");
        lineebat.add("Via ancora acqua");
        lineebat.add("Via laguna");
        lineebat.add("Via altra laguna");
        lineebat.add("Via banane");
        lineebat.add("Via logasd");
        lineebat.add("Via logasd");
        lineebat.add("Via logasd");
        lineebat.add("Via logasd");
        lineebat.add("Via logasd");
        ArrayAdapter<String> batAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lineebat);
        ListView listView = (ListView) findViewById(R.id.listaLineeBat);
        listView.setAdapter(batAdapter);
    }

}
