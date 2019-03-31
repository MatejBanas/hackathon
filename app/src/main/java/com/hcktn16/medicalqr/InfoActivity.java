package com.hcktn16.medicalqr;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class InfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Button buttonDoktor = findViewById(R.id.buttonDoktor);

        buttonDoktor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDoktor();
            }
        });


    }

    private void showDoktor(){
        Intent intent = new Intent(InfoActivity.this, DoctorActivity.class);
        startActivity(intent);

    }
}
