package com.hcktn16.medicalqr;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.ParseException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonInfo = findViewById(R.id.buttonInfo);
        Button buttonLogInWithQR = findViewById(R.id.buttonLogInWithQR);

        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoFromMain();
            }
        });
        buttonLogInWithQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInWithQRFromMain();
            }
        });




    }



    private void logInWithQRFromMain(){
        Intent intent = new Intent(MainActivity.this, LogInWithQRActivity.class);
        startActivity(intent);

    }

    private void infoFromMain(){
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        startActivity(intent);

        // Create DB
//        PatientDatabase database = Room.databaseBuilder(this, PatientDatabase.class, "patient_db")
//                .allowMainThreadQueries()
//                .build();
//
//        // Insert to DB
//        PatientDao patientDao= database.patientDao();
//        Patient patient = new Patient();
//        patient.setFirstName("Matej");
//        patient.setLastName("Banas");
//
//        patientDao.insert(patient);
//
//        // Debug
//        List<Patient> patientList = patientDao.getPatients();
//
//        Log.d("PATIENT",patientList.get(0).toString());

        try {
            DbHandler.readPatientId();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
