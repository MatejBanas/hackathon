package com.hcktn16.medicalqr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListActivity extends AppCompatActivity
{
    // Array of strings...
    private ListView simpleList;
    private List<String> countryList = new ArrayList<>();
    private String name;

    @Override   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);      setContentView(R.layout.activity_listactivity);
        parseData();
        simpleList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, countryList);
        simpleList.setAdapter(arrayAdapter);

        TextView textView = findViewById(R.id.textView);
        textView.setText(name);
    }

    private void parseData() {
        LogInWithQRActivity kokotkosazajebem = new LogInWithQRActivity();
        for (HashMap<String, String> map : kokotkosazajebem.getExaminations()){
            String name = map.get("Patient Name");
            String exam = map.get("Examination");
            String hour = map.get("Date");
            countryList.add(exam + "       " + hour);
            this.name = name;
        }

    }
}