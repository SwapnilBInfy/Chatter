package com.example.swapnilbasu.chatter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.swapnilbasu.chatter.LineGraph;

public class ChartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_chart);
    }


    public void lineGraphHandler (View view){
        LineGraph line = new LineGraph();
        Intent lineIntent = line.getIntent( this);
        startActivity(lineIntent);

    }

    public void barGraphHandler (View view){

        BarGraph bar = new BarGraph();
        Intent barIntent = bar.getIntent(this);
        startActivity(barIntent);

    }

    public void pieGraphHandler (View view){

    }

    public void scatterGraphHandler (View view){

    }

}