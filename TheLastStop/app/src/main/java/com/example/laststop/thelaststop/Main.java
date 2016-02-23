package com.example.laststop.thelaststop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageButton;

import android.widget.Toast;

import java.util.ArrayList;

import androidclient.AsyncDownload;

public class Main extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StackPointerContainer.getInstance().addMain(this);
       // Boolean b = this==StackPointerContainer.getInstance().getMainPointer();
       // Log.d("pointer", b.toString());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.id.busbut, options);
        //text.setText(list.toString());
        //mString=asd.getJson();
        //Log.d("json",mString);
        //popup(StackPointerContainer.getInstance().getMainPointer());
        ImageButton imgBus = (ImageButton)findViewById(R.id.busbut);
        ImageButton imgBat = (ImageButton)findViewById(R.id.batbut);
        //TextView text = (TextView) findViewById(R.id.textView);
        //text.setText("ciao");
        final Intent line = new Intent(Main.this, linee.class);
        imgBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d("autobus:","ho cliccato bus");
                    AsyncDownload asd = new AsyncDownload();
                    line.putExtra("Trasporto", "Bus");
                    line.putExtra("serviceType","bus");
                    asd.execute("DataRequest:Lines:bus"); //DataRequest:Lines:bus
                    ArrayList<String> michelelist =  asd.get();
                    Boolean bool = michelelist==null;
                    Log.d("ziojack:", "michelelist==null? " + bool.toString());
                    if(michelelist!=null){
                        line.putStringArrayListExtra("lineearr", michelelist);
                        startActivity(line);
                    }else{
                        popup(StackPointerContainer.getInstance().getMainPointer(),costanti.CON_SERVER_ERR_MSG,costanti.CON_TOAST_ERR_MSG);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        imgBat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d("autobus:","ho cliccato battelli");
                    AsyncDownload asd = new AsyncDownload();
                    line.putExtra("Trasporto", "Battelli");
                    line.putExtra("serviceType","navig");
                    asd.execute("DataRequest:Lines:navig");
                    ArrayList<String> michelelist =  asd.get();
                    Boolean bool = michelelist==null;
                    Log.d("ziojack:","michelelist=" + bool.toString() );
                    if(michelelist!=null){
                        line.putStringArrayListExtra("lineearr", michelelist);
                        startActivity(line);
                    }else{
                        popup(StackPointerContainer.getInstance().getMainPointer(),costanti.CON_SERVER_ERR_MSG,costanti.CON_TOAST_ERR_MSG);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    public void popup(Activity pointer,String msg, String toast){
        final Activity anonymousClassPointer = pointer;
        final String toastMsg=toast;
        AlertDialog.Builder builder = new AlertDialog.Builder(pointer);
        builder.setMessage(msg);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(anonymousClassPointer, toastMsg, Toast.LENGTH_LONG).show();
            }
        });
       /* builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });*/
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
