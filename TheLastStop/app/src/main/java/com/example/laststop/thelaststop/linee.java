package com.example.laststop.thelaststop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidclient.AsyncDownload;

public class linee extends ActionBarActivity {
    private ArrayList<HashMap> mezzi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linee);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String trasporto = getIntent().getExtras().getString("Trasporto");
        final String serviceType = getIntent().getExtras().getString("serviceType");
        setTitle("Linee " + trasporto);
        StackPointerContainer.getInstance().addLinee(this);
        final String aux = trasporto;
       // AsyncDownload asd = new AsyncDownload();
        //JOHN : modifica il metodo populate per passare i dati alla lista
        List<String> lines = getIntent().getExtras().getStringArrayList("lineearr"); //TODO: da riempire da John con nome delle linee(es.31,32,ecc)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lines);
        ListView listView = (ListView) findViewById(R.id.listaLinee);
        listView.setAdapter(adapter);

        final Intent stops = new Intent(linee.this, fermate.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                                               try{
                                                    AsyncDownload asd = new AsyncDownload();
                                                    String entry = parent.getAdapter().getItem(position).toString();
                                                    String[] splitedString = splitLineFromHeadsign(entry);
                                                    asd.execute("DataRequest:Stops:" + splitedString[0] + ":" + splitedString[1] + ":" + serviceType);
                                                    ArrayList<String> fermate = asd.get();
                                                    Log.d("ziojack:", "Linea: " + splitedString[0] + " capolinea:" + splitedString[1]);
                                                    stops.putExtra("Linea", entry);
                                                    stops.putExtra("Trasporto", aux);

                                                   if(fermate!=null){
                                                       stops.putStringArrayListExtra("fermate", fermate);
                                                       startActivity(stops);
                                                   }else{
                                                       StackPointerContainer.getInstance().getMainPointer().popup(StackPointerContainer.getInstance().getLineePointer(),costanti.CON_SERVER_ERR_MSG,costanti.CON_TOAST_ERR_MSG);
                                                   }

                                                }catch(Exception e){

                                                    e.printStackTrace();
                                                }
                                            }

                                            public String[] splitLineFromHeadsign(String s){
                                                String[] aux=s.split(" ");
                                                String[] ret = new String[2];
                                                ret[0]=aux[0];
                                                ret[1]="";
                                                for(int i=1; i < aux.length; i++){
                                                   // Log.d("ziojack:",aux[i]);
                                                    ret[1]+=aux[i]+" ";
                                                }
                                                ret[1]=ret[1].substring(0,ret[1].length()-1);//tolgo l'ultimo spazzio inutile
                                                return ret;
                                            }

                                        }
        );
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
    public void populate(String s){
        mezzi = new ArrayList<>();
        int i;
        for( i = 0; i<1; i++) {
            HashMap temp = new HashMap();
            temp.put("Linea", "31");
            temp.put("Capolinea", "L'inferno");
            mezzi.add(temp);
        }
        for( i = 0; i<25; i++) {
            HashMap temp = new HashMap();
            temp.put("Linea", "666");
            temp.put("Capolinea", "666-666-666-666");
            mezzi.add(temp);
        }
    }
}
