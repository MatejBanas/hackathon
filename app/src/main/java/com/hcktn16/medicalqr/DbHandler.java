package com.hcktn16.medicalqr;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class DbHandler {

    public static void readPatientId() throws ParseException {
        // Create
//        ParseObject patients = ParseObject.create("Patients");
//        patients.put("first_name","Test");
//        patients.put("last_name","testiiiing");
//        patients.put("patientId","654-xxx");
//        patients.saveInBackground();

        // Read
        ParseQuery<ParseObject> pQuery = ParseQuery.getQuery("Patients");
        pQuery.whereEqualTo("patientId","123abc");


        ParseObject patient =  pQuery.getFirst();
        String firstName = patient.getString("first_name");
        String lastName = patient.getString("last_name");

        ParseQuery<ParseObject> eQuery = ParseQuery.getQuery("Events");
        eQuery.whereEqualTo("patientId","123abc");
        eQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                if (e == null && list != null && list.size() != 0) {
                    for (ParseObject event : list) {

                        String finalStr = firstName + lastName + event.getString("examination") + event.getDate("date") +
                                event.getString("doctor");
                        Log.d("EXAM", finalStr);
                    }
                } else {
                    assert e != null;
                    e.printStackTrace();
                }
            }
        });
    }
}
