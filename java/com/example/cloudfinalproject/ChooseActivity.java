package com.example.cloudfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cloudfinalproject.Doctor.Signup_Doctor;
import com.google.firebase.analytics.FirebaseAnalytics;

public class ChooseActivity extends AppCompatActivity {

    CardView patientCard;
    CardView doctorCard;
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        patientCard = findViewById(R.id.patientCard);
        doctorCard = findViewById(R.id.doctorCard);

        doctorCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseActivity.this, Signup_Doctor.class));
               btnEvent("doctorCard","doctors","cardView");
            }

        });

        patientCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseActivity.this,Signup_patient.class));
                btnEvent("patientCard","doctors","cardView");
            }
        });
    }
    public  void btnEvent(String id,String name,String content){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, content);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}