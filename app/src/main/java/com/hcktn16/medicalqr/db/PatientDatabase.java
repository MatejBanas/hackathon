package com.hcktn16.medicalqr.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.hcktn16.medicalqr.dao.PatientDao;
import com.hcktn16.medicalqr.model.Patient;

@Database(entities = {Patient.class}, version = 1, exportSchema = false)
public abstract class PatientDatabase extends RoomDatabase {
    public abstract PatientDao patientDao() ;
}