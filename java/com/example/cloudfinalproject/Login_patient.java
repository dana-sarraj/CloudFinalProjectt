package com.example.cloudfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cloudfinalproject.Doctor.Signup_Doctor;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.cloudfinalproject.module.PationtsModule;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;



public class Login_patient extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    EditText emailP;
    EditText passP;
    Button btn_login;
    TextView dont_have_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_patient);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        emailP = findViewById(R.id.emailLoginP);
        passP = findViewById(R.id.passLoginP);
        btn_login = findViewById(R.id.login);
        dont_have_account = findViewById(R.id.donthaveAccount);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String password = intent.getStringExtra("password");

        emailP.setText(name);
        passP.setText(password);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_patient.this,ChoosePatientTopics.class));
                btnEvent("patientLogin","patients","Button");


            }
        });
        dont_have_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_patient.this, Signup_patient.class));
                btnEvent("patientDoNotHaveAccount","patients","Button");

            }
        });

    }

    private void loginUser(){
        String email = emailP.getText().toString();
        String password = passP.getText().toString();

        if (TextUtils.isEmpty(email)){
            emailP.setError("Email cannot be empty");
            emailP.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            passP.setError("Password cannot be empty");
            passP.requestFocus();
        }else{
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Login_patient.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login_patient.this,ChoosePatientTopics.class));
                    }else{
                        Toast.makeText(Login_patient.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    public  void btnEvent(String id,String name,String content){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, content);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}