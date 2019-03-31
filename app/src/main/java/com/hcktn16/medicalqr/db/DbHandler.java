package com.hcktn16.medicalqr.db;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbHandler {

    public static List<HashMap> readPatientId(String id) throws ParseException {
        List<HashMap> examinations = new ArrayList<>();
        HashMap<String,String> ex = new HashMap<>();

        // Read
        ParseQuery<ParseObject> pQuery = ParseQuery.getQuery("Patients");
        pQuery.whereEqualTo("patientId",id);


        ParseObject patient =  pQuery.getFirst();
        String firstName = patient.getString("first_name");
        String lastName = patient.getString("last_name");

        ParseQuery<ParseObject> eQuery = ParseQuery.getQuery("Events");
        eQuery.whereEqualTo("patientId",id);

        List<ParseObject> examsList = eQuery.find();

        for (ParseObject exam : examsList){
            ParseQuery<ParseObject> docQuery = ParseQuery.getQuery("Doctors");
            docQuery.whereEqualTo("doctorID",exam.get("doctorID"));
            String docName = docQuery.getFirst().getString("name");
            String docSurname = docQuery.getFirst().getString("surname");
            docName = docName + " " +docSurname;
//            String finalStr = firstName + lastName + exam.getString("examination") + exam.getDate("date") +
//                    docName + exam.getString("department");
         //   examinations.add(finalStr);
            ex.put("Patient Name", firstName + " " + lastName);
            ex.put("Examination", exam.getString("examination"));

            Format formatter = new SimpleDateFormat("HH:mm");
            String dateFormatted = formatter.format(exam.getDate("date"));
            ex.put("Date", dateFormatted);
            ex.put("Doctor",docName);
            ex.put("Department",exam.getString("department"));
            examinations.add(ex);
        }
        return examinations;
    }
}
