package com.example.csaper6.educationalquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {
    private int finalScore;
    private TextView finalDisplay;
    private Button playAgain, leaderBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        finalDisplay = (TextView)findViewById(R.id.textView_FinalScore);
        playAgain = (Button)findViewById(R.id.button_playgain);
        leaderBoard = (Button) findViewById(R.id.button_leaderboard);

        Intent i = getIntent();
        finalScore = i.getIntExtra("SCORE" ,0);

        finalDisplay.setText("Score: "+finalScore);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FinishActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });


        leaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FinishActivity.this, LeaderboardActivity.class);
                i.putExtra("FINAL_SCORE",finalScore);
                startActivity(i);
            }
        });

    }
}
