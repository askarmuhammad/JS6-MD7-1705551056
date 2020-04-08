package com.example.msi.latihan1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CardView cardInfo, cardHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardInfo = (CardView)findViewById(R.id.info);
        cardHelp = (CardView)findViewById(R.id.help);

        cardInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Iinfo = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(Iinfo);
            }
        });

        cardHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Ihelp = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(Ihelp);
            }
        });
    }
}

