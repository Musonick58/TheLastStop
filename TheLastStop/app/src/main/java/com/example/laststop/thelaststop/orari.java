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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;


public class orari extends ActionBarActivity {
    private ArrayList<HashMap> list;
    private String orario = null;
    private Integer pos = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orari);
        StackPointerContainer.getInstance().addOrari(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String linea = getIntent().getExtras().getString("Linea");
        String fermata = getIntent().getExtras().getString("Fermata");
        final String capoln = getIntent().getExtras().getString("Capolinea");
        final String serviceType = getIntent().getExtras().getString("serviceType");
        final String aux1 = linea;
        final String aux2 = fermata;
        ArrayList<String> timeTable = getIntent().getExtras().getStringArrayList("timetable");
        ArrayList<String> ritardi = getIntent().getExtras().getStringArrayList("ritardi");
        setTitle("Linea " + linea + " fermata di " + fermata);

        ListView orari = (ListView)findViewById(R.id.listaOrari);

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
                String ora = parent.getAdapter().getItem(position).toString();
                Log.d("tipo:",parent.getAdapter().getItem(position).getClass().getName());
                HashMap<String,String> map = (HashMap) parent.getAdapter().getItem(position);
                Log.d("tipo:",map.toString());
                Log.d("tipo:",map.get("Ritardo"));
                Log.d("tipo:",map.get("Ora Partenza"));
                ora = map.get("Ora Partenza");
                Log.d("tipo:",map.get("Ora Arrivo"));
                nuovo.putExtra("Linea", aux1);
                nuovo.putExtra("Fermata", aux2);
                nuovo.putExtra("Ora", ora);
                nuovo.putExtra("Capolinea", capoln);
                nuovo.putExtra("serviceType", serviceType);
                startActivity(nuovo);
            }
        });
    }

    public ArrayList<String> calcolaRitardo(ArrayList<String> timetable, ArrayList<String> ritardi){
        Date data = new Date();
        Calendar ritardic = new GregorianCalendar ();
        Calendar partenza = new GregorianCalendar();
        Calendar timedifference;
        ArrayList<String> listaRitardi = new ArrayList<>();
        if(orario == null){ //primo caricamento, nessuno ritardo segnalato
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
            return listaRitardi;
        }
        else{ // ripopolamento con ritardo segnalato
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
            return listaRitardi;
        }
    }

    public void populate(ArrayList<String> arrivi, ArrayList<String> partenze){
        ArrayList<String> listaRitardi = calcolaRitardo(arrivi, partenze);
        list = new ArrayList<HashMap>();
        int i;
        for( i = 0; i < arrivi.size(); i++) {
            HashMap temp = new HashMap();
            temp.put("Ora Arrivo", arrivi.get(i));
            temp.put("Ora Partenza", partenze.get(i));
            temp.put("Ritardo", listaRitardi.get(i));
            list.add(temp);
        }
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

    public String systemTime() {
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
        this.orario = orario;
    }
}
