package com.example.csaper6.educationalquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.backendless.Backendless;

public class LoginActivity extends AppCompatActivity {
    private EditText login, signup;
    private Button loginButton, signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signupButton = (Button)findViewById(R.id.button_signup);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        Backendless.initApp(this,"DD160D2B-E717-9333-FFD9-5CF3A5B48F00", "1174758F-5F88-B161-FFAB-679498D37800", "v1" );

    }
}
