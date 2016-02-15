package com.example.laststop.thelaststop;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import java.util.List;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class linee extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linee);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String trasporto = getIntent().getExtras().getString("Trasporto");
        setTitle("Linee " + trasporto);
        List<String> lines = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lines);
        ListView listView = (ListView) findViewById(R.id.listaLinee);
        listView.setAdapter(adapter);

    }

}
