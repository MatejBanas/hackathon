package com.hcktn16.medicalqr;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Console;

public class MainActivity extends AppCompatActivity {



    private String swag = "TESTTTT";

    public String getSwag() {
        return swag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonInfo = findViewById(R.id.buttonInfo);
        Button buttonLogInWithQR = findViewById(R.id.buttonLogInWithQR);

        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoFromMain();
            }
        });
        buttonLogInWithQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInWithQRFromMain();
            }
        });

        


    }



    private void logInWithQRFromMain(){
        Intent intent = new Intent(MainActivity.this, LogInWithQRActivity.class);
        startActivity(intent);

    }

    private void infoFromMain(){
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        startActivity(intent);
    }
}
