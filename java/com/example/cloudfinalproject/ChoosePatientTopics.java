package com.example.cloudfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChoosePatientTopics extends AppCompatActivity {
    ListView listView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_patient_topics);


        button = findViewById(R.id.choose_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChoosePatientTopics.this,HomePatient.class));

            }
        });

        listView = findViewById(R.id.ChooseList);
        ArrayList<String> arrayList = getList();
        ArrayAdapter<String> arrayAdapter = new
                ArrayAdapter<>(ChoosePatientTopics.this, android.R.layout.simple_list_item_multiple_choice,
                arrayList);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            String items = (String) adapterView.getItemAtPosition(i);
            Toast.makeText(ChoosePatientTopics.this, "select by list of item" +items,
                    Toast.LENGTH_SHORT).show();
        });
    }

    private ArrayList<String> getList() {

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(" مرض الزهايمر ");
        arrayList.add("مرض باركنسون");
        arrayList.add(" مرض الخرف ");
        arrayList.add(" أمراض السكتة الدماغية ");
        arrayList.add(" أمراض الفشل الكلوي ");
        arrayList.add(" أمراض العصبية المستعصية ");
        arrayList.add(" أمراض الرئة المستعصية  ");
        arrayList.add(" أمراض  القلب المزمنة ");
        arrayList.add(" أمراض التليف الكيسي ");
        arrayList.add(" نقص المناعة المكتسب ");

        return arrayList;

    }
}