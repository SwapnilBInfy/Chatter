package com.example.swapnilbasu.chatter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.example.swapnilbasu.chatter.R;




public class NewActivity extends AppCompatActivity {

    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);

        button = (Button) findViewById(R.id.makeGraphBtn);
        button.setOnClickListener (new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), com.example.swapnilbasu.chatter.GraphActivity.class);
                startActivity(i);
            }
        });


        button2 = (Button) findViewById(R.id.makeChartBtn);
        button2.setOnClickListener (new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), com.example.swapnilbasu.chatter.ChartActivity.class);
                startActivity(i);
            }
        });

    }
}