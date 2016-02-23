package com.example.laststop.thelaststop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import androidclient.AsyncDownload;

public class fermate extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fermate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String line = getIntent().getExtras().getString("Linea");
        final String capoln = getIntent().getExtras().getString("Capolinea");
        final String linea = line;
        setTitle("Linea " + line + " con capolinea " + capoln);
        ListView stop = (ListView)findViewById(R.id.listaFermate);
        String lines = getIntent().getExtras().getString("Linea");
        StackPointerContainer.getInstance().addFermate(this);
        ArrayList<String> list = getIntent().getStringArrayListExtra("fermate");
        final String serviceType = getIntent().getExtras().getString("serviceType");
        /*list.add("Via Torino0");
        list.add("Via Torino1");
        list.add("Via Torino2");
        list.add("Via Torino3");
        list.add("Via Torino4");
        list.add("Via Torino5");
        list.add("Via Torino6");
        list.add("Via Torino7");*/

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        stop.setAdapter(adapter);
        stop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    String fermata = parent.getAdapter().getItem(position).toString();
                    AsyncDownload asd = new AsyncDownload();
                    asd.execute("DataRequest:TimeTable:" + linea + ":" + capoln + ":" + fermata + ":" + serviceType);
                    ArrayList<String> timetable=asd.get();
                    AsyncDownload asd2 = new AsyncDownload();
                    asd2.execute("DataRequest:TimeTable:" + linea + ":" + capoln + ":" + fermata + ":" + serviceType);
                    ArrayList<String> ritardi=asd2.get();
                    Thread.sleep(3000);
                    Intent x = new Intent(getApplicationContext(), orari.class);
                    if(timetable!=null && ritardi!=null){
                        x.putExtra("Fermata", fermata);
                        x.putExtra("Capolinea", capoln);
                        x.putExtra("Linea", linea);
                        x.putStringArrayListExtra("timetable",timetable);
                        x.putStringArrayListExtra("ritardi",ritardi);
                        startActivity(x);
                    }
            }catch(Exception e){
                    StackPointerContainer.getInstance().getMainPointer().popup(StackPointerContainer.getInstance().getFermatePointer(),costanti.CON_SERVER_ERR_MSG,costanti.CON_TOAST_ERR_MSG);
                }
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

}
