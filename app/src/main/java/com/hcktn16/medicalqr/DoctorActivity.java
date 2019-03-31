package com.hcktn16.medicalqr;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class DoctorActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ParseQuery<ParseObject> dQuery = ParseQuery.getQuery("Doctors");
        dQuery.whereEqualTo("doctorID","1");

        ParseObject doctor = null;
        try {
            doctor = dQuery.getFirst();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String name = doctor.getString("name") + " " + doctor.getString("surname");
        String ambulance = doctor.getString("ambulance");
        String wardAndFloor = doctor.getString("ward") + ", " + doctor.getString("floor");
        String adress = doctor.getString("adress");

        TextView newName = (TextView) findViewById(R.id.textView2);
        newName.setText(name);

        TextView newAmbulance = (TextView) findViewById(R.id.textView3);
        newAmbulance.setText(ambulance);

        TextView newWardAndFloor = (TextView) findViewById(R.id.textView4);
        newWardAndFloor.setText(wardAndFloor);

        TextView newAdress = (TextView) findViewById(R.id.textView5);
        newAdress.setText(adress);

    }


}
