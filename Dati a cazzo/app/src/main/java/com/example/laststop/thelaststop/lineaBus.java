package com.example.laststop.thelaststop;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import java.util.List;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class lineaBus extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<String> lineebus = new ArrayList<String>();
        lineebus.add("Via torino");
        lineebus.add("Via x");
        lineebus.add("Via circonvallazione");
        lineebus.add("Via paperepoupi");
        lineebus.add("Via logasd");
        lineebus.add("Via logasd");
        lineebus.add("Via logasd");
        lineebus.add("Via logasd");
        lineebus.add("Via logasd");
        lineebus.add("Via logasd");
        lineebus.add("Via logasd");
        lineebus.add("Via logasd");
        lineebus.add("Via logasd");
        lineebus.add("Via logasd");
        lineebus.add("Via logasd");
        lineebus.add("Via logasd");
        lineebus.add("Via logasd");
        lineebus.add("Via logasd");
        lineebus.add("Via logasd");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linea_bus);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayAdapter<String> busAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lineebus);
        ListView listView = (ListView) findViewById(R.id.listaLineeBus);
        listView.setAdapter(busAdapter);
    }

}
