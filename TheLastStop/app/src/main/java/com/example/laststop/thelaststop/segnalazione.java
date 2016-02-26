package com.example.laststop.thelaststop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import androidclient.AsyncDownload;

public class segnalazione extends ActionBarActivity {

    private String rit;
    final String dati = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("metodo", "Sono entrato su onCreate di segnalazione.java");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segnalazione);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StackPointerContainer.getInstance().addSegnalazione(this);
        Button invia = (Button) findViewById(R.id.invia);
        TextView info = (TextView) findViewById(R.id.descrizione);
        final String linea = getIntent().getExtras().getString("Linea");
        final String fermata = getIntent().getExtras().getString("Fermata");
        final String servizio = getIntent().getExtras().getString("serviceType");
        final String capoln = getIntent().getExtras().getString("Capolinea");
        final String ora = getIntent().getExtras().getString("Ora");

        info.setText("Segnalazione ritardo sulla linea " + linea + " fermata di " + fermata + " delle ore " + ora + ". L'ora della segnalazione sara' rilevata automaticamente");

        invia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncDownload asd = new AsyncDownload();
                // Es -> DataRequest:SetDelay:linea:capolinea:fermata:servizio:ora(hh.mm.00):ritardo(hh.mm.00)
                String ritardo = systemTime();
                String richiesta = "DataRequest:SetDelay:" + linea + ":" + capoln + ":" + fermata + ":" + servizio + ":" + ora.replace(":", ".") + ":" + ritardo;
                asd.execute(richiesta);

                try {
                    if (asd.get().get(0).equals("nothing to send")) {
                        Log.d("michele ritardo", "Segnalato ritardo " + systemTime());
                        Log.d("michele tag", dati);
                        StackPointerContainer.getInstance().getMainPointer().popup(StackPointerContainer.getInstance().getSegnalazionePointer(), "Segnalazione " + systemTime().substring(0, 4) + " Inviata", "Ti ringraziamo per il tuo tempo...sei un grande!");
                    } else
                        StackPointerContainer.getInstance().getMainPointer().popup(StackPointerContainer.getInstance().getSegnalazionePointer(), costanti.CON_SERVER_ERR_MSG, costanti.CON_TOAST_ERR_MSG);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent aggiorna = new Intent(getApplicationContext(), aggiornamento.class);
                //Extra da passare alla prossima pagina per le prossime fermate
                aggiorna.putExtra("serviceTipe", servizio);
                aggiorna.putExtra("Linea", linea);
                aggiorna.putExtra("Capolinea", capoln);
                aggiorna.putExtra("Fermata", fermata);
                aggiorna.putExtra("Orario", ora);
                AsyncDownload asd2 = new AsyncDownload();

                try{
                    String richiesta2 = "DataRequest:NextStops:" + linea + ":" + capoln + ":" + fermata + ":" + ora.replace(":", ".") + ":" + servizio;
                    asd2.execute(richiesta2);
                    ArrayList<String> timetable = asd2.get();
                    aggiorna.putExtra("TimeTable", timetable);
                    startActivity(aggiorna);
                }catch(Exception e){
                    StackPointerContainer.getInstance().getMainPointer().popup(StackPointerContainer.getInstance().getFermatePointer(),costanti.CON_SERVER_ERR_MSG,costanti.CON_TOAST_ERR_MSG);
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("metodo", "Sono entrato su onOptionsItemSelected di segnalazione.java");
        switch (item.getItemId()) {
            case android.R.id.home:
                StackPointerContainer.getInstance().getOrariPointer().setDelay(rit);
                this.onBackPressed();
                return true;
            default:
                return true;
        }
    }

    public static String systemTime() {
        Log.d("metodo", "Sono entrato su systemTime di segnalazione.java");
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm");
        // you can get seconds by adding  "...:ss" to it
        date.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));

        String ritardo = date.format(currentLocalTime) + ".00";
        //int currentHour = cal.get(Calendar.HOUR);
        //int currentMinutes = cal.get(Calendar.MINUTE);
        //int currentSeconds = cal.get(Calendar.SECOND);
        ritardo = ritardo.replace(':', '.');
        Log.d("orario corrente", ritardo);
        return ritardo;
    }

    /* @Override
    public void onBackPressed() {
        ArrayList<String> timeTable = getIntent().getExtras().getStringArrayList("timetable");
        ArrayList<String> ritardi = getIntent().getExtras().getStringArrayList("ritardi");
        StackPointerContainer.getInstance().getOrariPointer().populate(timeTable, ritardi);
    } */

}