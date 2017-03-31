package com.example.csaper6.educationalquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private EditText login, password;
    private Button loginButton, signupButton;
    public static ArrayList<BackendlessUser>users = new ArrayList<BackendlessUser>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (EditText)findViewById(R.id.editText_username);
        password = (EditText)findViewById(R.id.editText_password);

        loginButton = (Button)findViewById(R.id.button_login);

        signupButton = (Button)findViewById(R.id.button_signup);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        Backendless.initApp(this,"DD160D2B-E717-9333-FFD9-5CF3A5B48F00", "1174758F-5F88-B161-FFAB-679498D37800", "v1" );

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Backendless.UserService.login(login.getText().toString(),password.getText().toString(), createCallback());
            }
        });

    }

    private AsyncCallback<BackendlessUser> createCallback(){
        return new AsyncCallback<BackendlessUser>(){

            protected void doInBackground(Void...voids){

            }

            @Override
            public void handleResponse(BackendlessUser response){
                //users.add(response);
                response.setProperty("Score", 123);
                Intent mainIntent = new Intent(LoginActivity.this,MenuActivity.class);
                LoginActivity.this.startActivity(mainIntent);

            }

            @Override
            public void handleFault(BackendlessFault fault){
                Toast.makeText(LoginActivity.this, "" + fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
    }
}
