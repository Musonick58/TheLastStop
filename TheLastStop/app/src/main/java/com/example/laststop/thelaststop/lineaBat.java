package com.example.laststop.thelaststop;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.laststop.thelaststop.R;

public class lineaBat extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linea_bat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
