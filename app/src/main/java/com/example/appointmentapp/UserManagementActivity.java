package com.example.appointmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserManagementActivity extends AppCompatActivity {

    //Declare XML
    TextView textDataAlert;
    EditText inputName;
    Spinner spinnerTimezone;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        //Assign XML
        textDataAlert = findViewById(R.id.textDataAlert);
        textDataAlert.setVisibility(View.GONE);
        inputName = findViewById(R.id.inputName);
        spinnerTimezone = findViewById(R.id.spinnerTimezone);
        buttonSave = findViewById(R.id.buttonSave);

        //Assign Java
        String prefereedTimezone = spinnerTimezone.toString();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputName.getText().toString().isEmpty() && !prefereedTimezone.isEmpty()){
                    textDataAlert.setVisibility(View.GONE);
                    Intent intent = new Intent(UserManagementActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    textDataAlert.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}