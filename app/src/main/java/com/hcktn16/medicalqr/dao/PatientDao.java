package com.hcktn16.medicalqr.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.hcktn16.medicalqr.model.Patient;

import java.util.List;

@Dao
public interface PatientDao {

    @Query("SELECT * FROM patients")
    List<Patient> getPatients();

    @Query("SELECT * FROM patients WHERE patientId IN (:patientIds)")
    List<Patient> loadAllByIds(int[] patientIds);

    @Query("SELECT * FROM patients WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Patient findByName(String first, String last);

    @Insert
    public void insert(Patient... patient);

    @Insert
    void insertAll(Patient... patients);

    @Delete
    void delete(Patient patient);
}