package com.hcktn16.medicalqr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.zxing.Result;
import com.hcktn16.medicalqr.db.DbHandler;
import com.hcktn16.medicalqr.objects.Examinaton;
import com.parse.ParseException;

import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class LogInWithQRActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView zXingScannerView;
    private static List<Examinaton> examinations;

    public static List<Examinaton> getExaminations() {
        return examinations;
    }

    public void setExaminations(List<Examinaton> examinations) {
        this.examinations = examinations;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginwithqr);
    }

    public void scan(View view){
        zXingScannerView =new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(LogInWithQRActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void handleResult(Result result) {
        //Toast.makeText(getApplicationContext(),result.getText(),Toast.LENGTH_SHORT).show();
        //zXingScannerView.resumeCameraPreview(this);

        String id = result.getText();
        try {
           List<Examinaton> exams =  DbHandler.readPatientId(id);
           setExaminations(exams);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patientInfoToMenu();

    }

    private void patientInfoToMenu(){
        Intent intent = new Intent(LogInWithQRActivity.this, ListActivity.class);
        startActivity(intent);
    }

}
