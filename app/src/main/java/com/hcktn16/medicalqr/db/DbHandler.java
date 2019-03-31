package com.hcktn16.medicalqr.db;

import com.hcktn16.medicalqr.objects.Examinaton;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.List;

public class DbHandler {

    public static List<Examinaton> readPatientId(String id) throws ParseException {
        List<Examinaton> examinations = new ArrayList<>();

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

            Examinaton examinaton = new Examinaton(firstName + " " + lastName,
                    exam.getString("examination"),exam.getDate("date"),
                    docName,exam.getString("department"), exam.getString("door"));
//            ex.put("Patient Name", firstName + " " + lastName);
//            ex.put("Examination", exam.getString("examination"));
//
//            Format formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
//            String dateFormatted = formatter.format(exam.getDate("date"));
//            ex.put("Date", dateFormatted);
//            ex.put("Doctor",docName);
//            ex.put("Department",exam.getString("department"));
            examinations.add(examinaton);
        }
        return examinations;
    }
}
