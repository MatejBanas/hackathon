package com.hcktn16.medicalqr;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

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

        final EditText text1 = (EditText) findViewById(R.id.editText);
        final EditText text2 = (EditText) findViewById(R.id.editText2);
        final EditText text3 = (EditText) findViewById(R.id.editText3);
        final EditText text4 = (EditText) findViewById(R.id.editText4);

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


        text4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {
                text1.setText(name);
                text2.setText(ambulance);
                text3.setText(wardAndFloor);
                text4.setText(adress);

            }
        });
    }

}
