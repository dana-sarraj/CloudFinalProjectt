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
import com.example.cloudfinalproject.module.PationtsModule;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;

import org.checkerframework.common.returnsreceiver.qual.This;

public class Signup_patient extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    private FirebaseAnalytics mFirebaseAnalytics;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    Button Sign_btn;
    EditText fullname;
    EditText birth_date;
    EditText address1;
    EditText emailaddress;
    TextView haveAccount;

    EditText phone_number;
    EditText pass;
    EditText confirmpass;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_patient);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Sign_btn = findViewById(R.id.register);
        fullname = findViewById(R.id.nameTxt);
        birth_date = findViewById(R.id.birthTxt);
        address1 = findViewById(R.id.addressTxt);
        emailaddress = findViewById(R.id.emailTxt);
        phone_number = findViewById(R.id.ponetxt);
        pass = findViewById(R.id.password);
        confirmpass = findViewById(R.id.confirmPass);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        haveAccount=findViewById(R.id.haveAccount);


        Sign_btn.setOnClickListener(view ->{
            createUser();
        });
        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup_patient.this, Login_patient.class));
                btnEvent("patientSignup","patients","Button");
            }
        });


    }

    private void createUser(){
        String email = emailaddress.getText().toString();
        String password = pass.getText().toString();
        String address = address1.getText().toString();
        String birthdate= birth_date.getText().toString();
        String name = fullname.getText().toString();
        String confrimpassword = confirmpass.getText().toString();
        String phone = phone_number.getText().toString();

        if (TextUtils.isEmpty(email)){
            emailaddress.setError("Email cannot be empty");
            emailaddress.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            pass.setError("Password cannot be empty");
            pass.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            fullname.setError("Password cannot be empty");
            fullname.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            address1.setError("Password cannot be empty");
            address1.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            phone_number.setError("Password cannot be empty");
            phone_number.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            confirmpass.setError("Password cannot be empty");
            confirmpass.requestFocus();
        }else{
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        startActivity(new Intent(Signup_patient.this, HomePatient.class));
//                        doctorModule pationtsModule = new doctorModule(name, address, birthdate, email, phone, password, confrimpassword);
//                        databaseReference.child(name).setValue(pationtsModule);
                        String deviceToken= FirebaseInstanceId.getInstance().getToken();
                        String currentUserID=firebaseAuth.getCurrentUser().getUid();
                        databaseReference.child("Patients").child(currentUserID).setValue("");
                        databaseReference.child("Patients").child(currentUserID).child("device_token").setValue(deviceToken);
                        firebaseFirestore.collection("Patients").document(FirebaseAuth.getInstance().getUid()).set(
                                new PationtsModule(name,address,birthdate,email,phone,password,confrimpassword)
                        );

                        progressDialog.cancel();
                    }else{
                        Toast.makeText(Signup_patient.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    }
                    }
//
//                        Toast.makeText(Signup_patient.this, "User registered successfully", Toast.LENGTH_SHORT).show();
//
//                        firebaseFirestore.collection("Patients").document(FirebaseAuth.getInstance().getUid()).set(
//                                new PationtsModule(name,address,birthdate,email,phone,password,confrimpassword)
//                        );
//
//                        Intent intent = new Intent(Signup_patient.this,ChoosePatientTopics.class);
//                        intent.putExtra("name",email);
//                        intent.putExtra("password",password);
//                        startActivity(intent);
//
//
//                    }else{
//                        Toast.makeText(Signup_patient.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                    }

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