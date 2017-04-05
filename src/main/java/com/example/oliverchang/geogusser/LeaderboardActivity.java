package com.example.oliverchang.geogusser;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        FragmentManager fm = getSupportFragmentManager();
        if(fm.findFragmentByTag("SuperheroFragment")== null)
            fm.beginTransaction()
                    .add(R.id.player_list_container, new PlayerFragment(), "Play Fragment")
                    .commit();
    }
}
