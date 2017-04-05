package com.example.oliverchang.geogusser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.oliverchang.geogusser.QuestionActivity.score;
import static com.example.oliverchang.geogusser.R.styleable.View;

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

        finalDisplay.setText("Score: "+ score);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FinishActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });


        leaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FinishActivity.this, LeaderboardActivity.class);
                i.putExtra("FINAL_SCORE",finalScore);
                startActivity(i);
            }
        });
    }
}
