package com.hcktn16.medicalqr;

import android.arch.persistence.room.Room;

import android.content.Context;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hcktn16.medicalqr.dao.PatientDao;
import com.hcktn16.medicalqr.db.PatientDatabase;
import com.hcktn16.medicalqr.model.Patient;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create DB
        PatientDatabase database = Room.databaseBuilder(this, PatientDatabase.class, "patient_db")
                .allowMainThreadQueries()
                .build();

        // Insert to DB
        PatientDao patientDao= database.patientDao();
        Patient patient = new Patient();
        patient.setFirstName("Matej");
        patient.setLastName("Banas");

        patientDao.insert(patient);

        // Debug
        List<Patient> patientList = patientDao.getPatients();

        Log.d("PATIENT",patientList.get(0).toString());
        
    }



}
