package com.example.laststop.thelaststop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import java.util.List;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidclient.SendRequest;
import andoridserver.androidData.*;
import android.content.DialogInterface.*;

public class linee extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linee);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String trasporto = getIntent().getExtras().getString("Trasporto");
        setTitle("Linee " + trasporto);

        List<String> lines = new ArrayList<>(); //TODO: da riempire da John con nome delle linee(es.31,32,ecc)


        if (trasporto.equals("Bus")){

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lines);
        ListView listView = (ListView) findViewById(R.id.listaLinee);
        listView.setAdapter(adapter);
    }

    public void newErrDialog() {
        AlertDialog.Builder err = new AlertDialog.Builder(this);
        err.setMessage("Controlla la tua connessione!");
        err.setTitle("Errore");
        err.setCancelable(false);
        err.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = err.create();
        alert.show();
    }
}
