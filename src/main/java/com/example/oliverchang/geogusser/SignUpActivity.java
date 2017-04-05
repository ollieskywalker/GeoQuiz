package com.example.oliverchang.geogusser;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import weborb.client.ant.wdm.View;

public class SignUpActivity extends AppCompatActivity {

    private EditText nameSignUp, email, password, confirmPassword;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameSignUp = (EditText)findViewById(R.id.editText_nameSignUp);
        email = (EditText)findViewById(R.id.editText_emailSignUp);
        password = (EditText)findViewById(R.id.editText_passwordSignUp);
        confirmPassword = (EditText)findViewById(R.id.editText_confirmpassword);
        submit = (Button)findViewById(R.id.button_submit_user);

        Backendless.initApp(this,"DD160D2B-E717-9333-FFD9-5CF3A5B48F00", "1174758F-5F88-B161-FFAB-679498D37800", "v1" );

        submit.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String firstPassword = String.valueOf(password.getText());
                String secondPassword = String.valueOf(confirmPassword.getText());

                if(firstPassword.equals(secondPassword)){
                    BackendlessUser user = new BackendlessUser();
                    user.setEmail(email.getText().toString());
                    user.setPassword(confirmPassword.getText().toString());
                    user.setProperty("name",nameSignUp.getText().toString());

                    Backendless.UserService.register(user, createRegCallback());
                }
            }
        });

    }

    private AsyncCallback<BackendlessUser> createRegCallback() {
        return new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                Toast.makeText(SignUpActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();

                Intent i = new Intent();
                Bundle extras = new Bundle();
                extras.putString("EXTRA_USERNAME", nameSignUp.getText().toString());
                extras.putString("EXTRA_PASSWORD", password.getText().toString());
                setResult(Activity.RESULT_OK, i);
                finish();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(SignUpActivity.this, "" + fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
    }
}
