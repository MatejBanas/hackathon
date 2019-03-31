package com.hcktn16.medicalqr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;import android.widget.ListView;

public class ListActivity extends AppCompatActivity
{
    // Array of strings...
    ListView simpleList;
    String countryList[] = {"KOKOT", "PICA", "U", "HOLICA", "ASFADASDASD", "NewZealand"};

    @Override   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);      setContentView(R.layout.activity_listactivity);
        simpleList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, countryList);
        simpleList.setAdapter(arrayAdapter);
    }
}