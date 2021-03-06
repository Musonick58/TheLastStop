package com.example.laststop.thelaststop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;

import com.example.laststop.thelaststop.Funzioni;
import androidclient.AsyncDownload;


public class orari extends ActionBarActivity {
    private ArrayList<HashMap> list;
    private String orario = null;
    private Integer pos = null;
    String linea;
    String fermata;
    String capoln;
    String serviceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("metodo","Sono entrato su onCreate di orari.java");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orari);
        StackPointerContainer.getInstance().addOrari(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        linea = getIntent().getExtras().getString("Linea");
        fermata = getIntent().getExtras().getString("Fermata");
        capoln = getIntent().getExtras().getString("Capolinea");
        serviceType = getIntent().getExtras().getString("serviceType");
        final String aux1 = linea;
        final String aux2 = fermata;
        ArrayList<String> timeTable = getIntent().getExtras().getStringArrayList("timetable");
        ArrayList<String> ritardi = getIntent().getExtras().getStringArrayList("ritardi");
        setTitle("Linea " + linea + " fermata di " + fermata);

        ListView orari = (ListView)findViewById(R.id.listaOrari);
        Log.d("ALESSIA TIMETABLE", timeTable.toString());
        Log.d("ALESSIA RITARDI", ritardi.toString());
        populate(timeTable, ritardi);

        myListAdapter adapter = new myListAdapter(this,list);
        /*adapter.getView();*/
        orari.setAdapter(adapter);

        /* aggiungere listener su click della lista */
        orari.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                Intent nuovo = new Intent (getApplicationContext(),segnalazione.class);
                String ora;
                Log.d("tipo:",parent.getAdapter().getItem(position).getClass().getName());
                HashMap<String,String> map = (HashMap) parent.getAdapter().getItem(position);
                Log.d("tipo:",map.toString());
                Log.d("tipo:",map.get("Ritardo"));
                Log.d("tipo:",map.get("Ora Partenza"));
                ora = map.get("Ora Arrivo");
                Log.d("tipo:",map.get("Ora Arrivo"));
                nuovo.putExtra("Linea", aux1);
                nuovo.putExtra("Fermata", aux2);
                nuovo.putExtra("Ora", ora);
                nuovo.putExtra("Capolinea", capoln);
                nuovo.putExtra("serviceType", serviceType);
                nuovo.putExtra("Posizione", position);
                startActivity(nuovo);
            }
        });
    }

    /*public ArrayList<String> calcolaRitardo(ArrayList<String> timetable, ArrayList<String> ritardi){
        Log.d("metodo","Sono entrato su calcolaRitardo di orari.java");
        Date data = new Date();
        Calendar ritardic = new GregorianCalendar ();
        Calendar partenza = new GregorianCalendar();
        Calendar timedifference;
        ArrayList<String> listaRitardi = new ArrayList<>();
        if(orario == null){ //primo caricamento, nessuno ritardo segnalato
            Log.d("calcolaRitardo","primo populate, orario == null -> " + ((Boolean) (orario == null)).toString());
            for(int i = 0; i < timetable.size(); i++){
                String temporario = timetable.get(i);
                String[] partenzahms = temporario.split(":");
                String tempritardo = ritardi.get(i);
                String[] ritardohms = tempritardo.split(":");

                ritardic.set (Calendar.HOUR_OF_DAY, Integer.parseInt(ritardohms[0]));
                ritardic.set (Calendar.MINUTE, Integer.parseInt(ritardohms[1]));
                ritardic.set (Calendar.SECOND, Integer.parseInt(ritardohms[2]));
                ritardic.set(Calendar.MILLISECOND, 0);

                partenza.set (Calendar.HOUR_OF_DAY, Integer.parseInt(partenzahms[0]));
                partenza.set (Calendar.MINUTE, Integer.parseInt(partenzahms[1]));
                partenza.set (Calendar.SECOND, Integer.parseInt(partenzahms[2]));
                partenza.set(Calendar.MILLISECOND, 0);

                long partenzamillis = partenza.getTimeInMillis();
                long ritardomillis = ritardic.getTimeInMillis();
                long timediff;
                if(ritardomillis > partenzamillis){
                    timediff = ritardomillis - partenzamillis;
                }
                else{
                    timediff = 0;
                }

                timedifference = Calendar.getInstance();
                timedifference.setTimeInMillis(timediff);
                int minutes = timedifference.get(Calendar.MINUTE);
                listaRitardi.add(Integer.toString(minutes));
            }
            Log.d("listaRitardi // CalcRit",listaRitardi.toString());
            return listaRitardi;
        }
        else{ // ripopolamento con ritardo segnalato
            Log.d("calcolaRitardo", "populate successivo, orario != null -> " + ((Boolean) (orario != null)).toString());
            String[] ritsplittato = orario.split(".");
            Calendar ritsegnalato = new GregorianCalendar();
            int ritminuti = Integer.parseInt(ritsplittato[1]);
            for(int i = pos; i < timetable.size(); i++){
                String temporario = timetable.get(i);
                String[] partenzahms = temporario.split(":");
                String tempritardo = ritardi.get(i);
                String[] ritardohms = tempritardo.split(":");

                ritardic.set (Calendar.HOUR_OF_DAY, Integer.parseInt(ritardohms[0]));
                ritardic.set (Calendar.MINUTE, Integer.parseInt(ritardohms[1]));
                ritardic.set (Calendar.SECOND, Integer.parseInt(ritardohms[2]));
                ritardic.set(Calendar.MILLISECOND, 0);

                partenza.set (Calendar.HOUR_OF_DAY, Integer.parseInt(partenzahms[0]));
                partenza.set (Calendar.MINUTE, Integer.parseInt(partenzahms[1]));
                partenza.set (Calendar.SECOND, Integer.parseInt(partenzahms[2]));
                partenza.set(Calendar.MILLISECOND, 0);

                ritsegnalato.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ritsplittato[0]));
                ritsegnalato.set (Calendar.MINUTE, Integer.parseInt(ritsplittato[1]));
                ritsegnalato.set(Calendar.SECOND, Integer.parseInt(ritsplittato[2]));
                ritsegnalato.set(Calendar.MILLISECOND, 0);

                long partenzamillis = partenza.getTimeInMillis();
                long ritardomillis = ritardic.getTimeInMillis();
                long ritsegnalatordomillis = ritsegnalato.getTimeInMillis();
                long timediff;
                timediff = ritardomillis - partenzamillis + ritsegnalatordomillis;
                timedifference = Calendar.getInstance();
                timedifference.setTimeInMillis(timediff);
                int minutes = timedifference.get(Calendar.MINUTE);
                listaRitardi.add(Integer.toString(minutes));
            }
            Log.d("listaRitardi // CalcRit",listaRitardi.toString());
            return listaRitardi;
        }
    }*/

    public void populate(ArrayList<String> arrivi, ArrayList<String> partenze){
        Log.d("metodo","Sono entrato su populate di orari.java");
        list = new ArrayList<HashMap>();
        int i;
        for( i = 0; i < arrivi.size(); i++) {
            HashMap temp = new HashMap();
            temp.put("Ora Arrivo", arrivi.get(i).substring(0, 5));
            temp.put("Ora Partenza", partenze.get(i).substring(0, 5));
            temp.put("Ritardo", "" + Funzioni.diffOrari(arrivi.get(i), partenze.get(i)));
            list.add(temp);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("metodo","Sono entrato su onOptionsItemSelected di orari.java");
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public String systemTime() {
        Log.d("metodo","Sono entrato su systemTime di orari.java");
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


    public void setDelay(String orario){
        Log.d("metodo","Sono entrato su setDelay di orari.java");
        this.orario = orario;
    }


}
