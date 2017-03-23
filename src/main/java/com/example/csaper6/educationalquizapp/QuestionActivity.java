package com.example.csaper6.educationalquizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class QuestionActivity extends Activity {
    private static final String URL= "https://restcountries.eu/rest/v2/all";
    private String countryName = "";
    private boolean key = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        final EditText answer = (EditText)findViewById(R.id.answer_editText);
        Button submit = (Button)findViewById(R.id.answer_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getIntent();
                countryName = i.getStringExtra("EXTRA_NAME");
                
                if(countryName.equals(answer.getText().toString())){
                    Toast.makeText(QuestionActivity.this, "Winner!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(QuestionActivity.this, "Loser!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        JSONParser parser = new JSONParser();

        parser.execute(URL);


    }

    private class JSONParser extends AsyncTask<String, Void, String> {
        private static final String TAG = "TAG";
        String countryJSON = "";

        @Override
        protected String doInBackground(String... urls) {
            try {
                java.net.URL url = new URL(urls[0]);
                URLConnection connection = url.openConnection();

                InputStream inputStream = connection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while((line = reader.readLine()) != null){
                    countryJSON += line;
                }
                return countryJSON;

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("onAsyncClass" , "NOT WORKING");

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ArrayList<Country> countries = new ArrayList<Country>();

            if(s != null){
                try {
                    JSONArray countriesJSON = new JSONArray(countryJSON);

                        //Log.d(TAG, "onCreate: " + countriesJSON.optJSONObject(i).optString("name"));

                        String name = countriesJSON.optJSONObject(0).optString("name");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
