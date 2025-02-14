package com.example.appointmentapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.appointmentapp.helper.DatabaseHelper;
import com.example.appointmentapp.model.User;

public class LoginActivity extends AppCompatActivity {
    //Declare XML
    TextView textLoginAlert;
    EditText inputUsername;
    Button buttonLogin;

    //Declare Java
    DatabaseHelper db;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Assign XML
        textLoginAlert = findViewById(R.id.textLoginAlert);
        textLoginAlert.setVisibility(View.GONE);
        inputUsername = findViewById(R.id.inputUsername);
        buttonLogin = findViewById(R.id.buttonLogin);

        //Assign Java
        db = new DatabaseHelper(LoginActivity.this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get username value fron editText
                username = inputUsername.getText().toString();
                //if user input and username dont exist
                if (!inputUsername.getText().toString().isEmpty() && !db.isValueExist(username)){
                    textLoginAlert.setVisibility(View.GONE);
                    Intent intent = new Intent(LoginActivity.this, UserManagementActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
                //if user input and username exist
                else if (!inputUsername.getText().toString().isEmpty() && db.isValueExist(username)){
                    textLoginAlert.setVisibility(View.GONE);
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
                //NOTE: Jika database sudah jadi, cek apakah username baru atau tidak
                //jika username baru maka intent ke user management
                //jika username lama maka masuk dashboard
                else{
                    textLoginAlert.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}