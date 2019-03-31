package com.hcktn16.medicalqr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;import android.widget.ListView;
import android.widget.TextView;

import com.hcktn16.medicalqr.objects.Examinaton;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity
{
    private ListView simpleList;
    private List<String> arrayList = new ArrayList<>();
    private String name;

    @Override   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);      setContentView(R.layout.activity_listactivity);
        parseData();
        simpleList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, arrayList);
        simpleList.setAdapter(arrayAdapter);

        TextView textView = findViewById(R.id.textView);
        textView.setText("Meno pacienta: " + name);
    }

    private void parseData() {
        for (Examinaton ex : LogInWithQRActivity.getExaminations()){
            Format formatter = new SimpleDateFormat("HH:mm");
            String dateFormatted = formatter.format(ex.getDate());
            arrayList.add(ex.getExamination() + "\n" + dateFormatted + "   " + ex.getDoor());
            this.name = ex.getPatientName();
        }

    }
}