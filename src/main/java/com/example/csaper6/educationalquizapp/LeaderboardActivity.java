package com.example.csaper6.educationalquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.backendless.BackendlessUser;

import java.util.ArrayList;

public class LeaderboardActivity extends AppCompatActivity {
    private ArrayList<BackendlessUser>finals = LoginActivity.users;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        Intent i = getIntent();
        score = i.getIntExtra("FINAL_SCORE",0);

        //finals.get(0).setProperty("Score", score);

    }
}
