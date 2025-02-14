package com.example.appointmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateAppointmentActivity extends AppCompatActivity {

    //Declare XML
    TextView textDataAlert, textParticipantsAlert, textAppointmentDate, textStartTime, textEndTime;
    EditText inputAppointmentTitle;
    Button buttonAddParticipants, buttonSetSchedule, buttonDone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        //Assign XML
        textDataAlert = findViewById(R.id.textDataAlert);
        textDataAlert.setVisibility(View.GONE);
        textParticipantsAlert = findViewById(R.id.textParticipantsAlert);
        textAppointmentDate = findViewById(R.id.textAppointmentDate);
        textStartTime = findViewById(R.id.textStartTime);
        textEndTime = findViewById(R.id.textEndTime);
        inputAppointmentTitle = findViewById(R.id.inputAppointmentTitle);
        buttonAddParticipants = findViewById(R.id.buttonAddParticipants);
        buttonSetSchedule = findViewById(R.id.buttonSetSchedule);
        buttonDone = findViewById(R.id.buttonDone);

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputAppointmentTitle.getText().toString().isEmpty()){
                    finish();
                }else{
                    textDataAlert.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}