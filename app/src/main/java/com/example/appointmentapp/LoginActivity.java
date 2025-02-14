package com.example.appointmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    //Declare XML
    TextView textLoginAlert;
    EditText inputUsername;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Assign XML
        textLoginAlert = findViewById(R.id.textLoginAlert);
        textLoginAlert.setVisibility(View.GONE);
        inputUsername = findViewById(R.id.inputUsername);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputUsername.getText().toString().isEmpty()){
                    textLoginAlert.setVisibility(View.VISIBLE);
                }
                //NOTE: Jika database sudah jadi, cek apakah username baru atau tidak
                //jika username baru maka intent ke user management
                //jika username lama maka masuk dashboard
                else{
                    textLoginAlert.setVisibility(View.GONE);
                    Intent intent = new Intent(LoginActivity.this, UserManagementActivity.class); //NOTE: Sementara ke UserManagement dulu
                    startActivity(intent);
                }
            }
        });
    }
}