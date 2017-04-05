package com.example.oliverchang.geogusser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    private Button startButton, leaderboardsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        startButton = (Button) findViewById(R.id.button_start);
        leaderboardsButton = (Button) findViewById(R.id.button_leaderboards);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });

        leaderboardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, LeaderboardActivity.class);
                startActivity(i);
            }
        });
    }
}
