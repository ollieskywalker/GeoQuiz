package com.example.oliverchang.geogusser;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;

import static android.support.v7.appcompat.R.styleable.View;


public class QuestionActivity extends Activity {
    private static final String URL = "https://restcountries.eu/rest/v2/all";
    private String countryName = "";
    private boolean key = false;
    public static int score = 0;
    private EditText answer;
    private TextView scoreBoard = MapsActivity.scoreBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        scoreBoard.setText("Score: " + score);
        answer = (EditText) findViewById(R.id.answer_editText);
        Button submit = (Button) findViewById(R.id.answer_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                countryName = i.getStringExtra("EXTRA_NAME");

                if (countryName.equals(answer.getText().toString())) {
                    Toast.makeText(QuestionActivity.this, "Winner!", Toast.LENGTH_SHORT).show();
                    score++;
                    scoreBoard.setText("Score: " + score);
                } else {
                    Toast.makeText(QuestionActivity.this, "Loser!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}