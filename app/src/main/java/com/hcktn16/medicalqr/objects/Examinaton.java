package com.hcktn16.medicalqr.objects;

import java.util.Date;

public class Examinaton {
    private String patientName;
    private String examination;
    private Date date;
    private String doctor;
    private String department;

    public Examinaton(String patientName, String examination, Date date, String doctor, String department) {
        this.patientName = patientName;
        this.examination = examination;
        this.date = date;
        this.doctor = doctor;
        this.department = department;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getExamination() {
        return examination;
    }

    public void setExamination(String examination) {
        this.examination = examination;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
