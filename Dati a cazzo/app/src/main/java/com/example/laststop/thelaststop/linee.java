package com.example.laststop.thelaststop;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import java.util.List;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.app.ActionBar;

public class linee extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linee);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.rel);
        String trasporto = getIntent().getExtras().getString("Trasporto");
        setTitle("Linee " + trasporto);

        List<String> lines = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, lines);
        ListView listView = (ListView) findViewById(R.id.listaLinee);
        listView.setAdapter(adapter);

        rl.setBackgroundColor(Color.parseColor("#616161"));

        lines.add("ciao0");
        lines.add("ciao1");
        lines.add("ciao2");
        lines.add("ciao3");
        lines.add("ciao4");
        lines.add("ciao5");
        lines.add("ciao6");
        lines.add("ciao7");
        lines.add("ciao8");
        lines.add("ciao9");
        lines.add("ciao10");
        lines.add("ciao11");
        lines.add("ciao12");
        lines.add("ciao13");
        lines.add("ciao14");
        lines.add("ciao15");
        lines.add("ciao16");
        lines.add("ciao17");
        lines.add("ciao18");
        lines.add("ciao19");
    }

}
