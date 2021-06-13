package com.example.finalapplication;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {
//    EditText npmEditText, passwordEditText;
//    Button signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText npmEditText = (EditText) findViewById(R.id.editTextNPM);
        final EditText passwordEditText = findViewById(R.id.editTextPassword);
        Button signIn = findViewById(R.id.SignInButton);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(npmEditText.getText().toString());
            }
        });
    }
}