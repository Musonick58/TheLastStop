package com.example.laststop.thelaststop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageButton;

import android.content.DialogInterface.*;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import androidclient.AsyncDownload;
import androidclient.SendRequest;

public class Main extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.id.busbut, options);

        AsyncDownload asd = new AsyncDownload();
        asd.execute(new Socket());
        ImageButton imgBus = (ImageButton)findViewById(R.id.busbut);
        ImageButton imgBat = (ImageButton)findViewById(R.id.batbut);
        final Intent line = new Intent(Main.this, linee.class);
        imgBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                line.putExtra("Trasporto", "Bus");
                ArrayList<String> l = new ArrayList<>();
                l.add("2");
                l.add("3");
                //l.add(new SendRequest().askLines("bus"));
                line.putStringArrayListExtra("lineearr", l);
                startActivity(line);
            }
        });
        imgBat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                line.putExtra("Trasporto", "Battelli");
                ArrayList<String> l = new ArrayList<>();
                l.add("1");
                l.add("5.1");
               // l.add(new SendRequest().askLines("navig"));
                line.putStringArrayListExtra("lineearr", l);
                startActivity(line);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
