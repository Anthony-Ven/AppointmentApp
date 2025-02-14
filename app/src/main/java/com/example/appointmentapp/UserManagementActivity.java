package com.example.appointmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appointmentapp.helper.DatabaseHelper;

public class UserManagementActivity extends AppCompatActivity {

    //Declare XML
    TextView textDataAlert;
    EditText inputName, inputUsername;
    Spinner spinnerTimezone;
    Button buttonSave;

    //Declare Java
    DatabaseHelper db;
    String name, username, preferredTimezone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        //Assign XML
        textDataAlert = findViewById(R.id.textDataAlert);
        textDataAlert.setVisibility(View.GONE);
        inputUsername = findViewById(R.id.inputUsername);
        inputName = findViewById(R.id.inputName);
        spinnerTimezone = findViewById(R.id.spinnerTimezone);
        buttonSave = findViewById(R.id.buttonSave);

        //Get intent Data
        username = getIntent().getStringExtra("username");
        inputUsername.setText(username);

        //Assign Java
        db = new DatabaseHelper(UserManagementActivity.this);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                //if all data filled proceed to Dashboard
                if (!inputName.getText().toString().isEmpty()){
                    db.addUser(username, name, preferredTimezone);
                    Toast.makeText(UserManagementActivity.this, name, Toast.LENGTH_LONG).show();
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

    //Get data from XML
    void getData(){
        username = inputUsername.getText().toString().trim();
        name = inputName.getText().toString().trim();
        preferredTimezone = spinnerTimezone/*.getSelectedItem()*/.toString().trim();
    }
}