package com.example.cloudfinalproject.Doctor;

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

import com.example.cloudfinalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login_doctor extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    EditText emailD;
    EditText passD;
    Button btn_login;
    TextView dont_have_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_doctor);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        emailD = findViewById(R.id.emailLoginD);
        passD = findViewById(R.id.passLoginD);
        btn_login = findViewById(R.id.loginDOC);
        dont_have_account = findViewById(R.id.donthaveAccountDOC);




        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String password = intent.getStringExtra("password");

        emailD.setText(name);
        passD.setText(password);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Login_doctor.this, HomeDoctor.class));

            }
        });

        dont_have_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_doctor.this, Signup_Doctor.class));
                btnEvent("DoctorLogin","Doctors","Button");
            }
        });

    }

    private void loginUser(){
        String email = emailD.getText().toString();
        String password = passD.getText().toString();

        if (TextUtils.isEmpty(email)){
            emailD.setError("Email cannot be empty");
            emailD.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            passD.setError("Password cannot be empty");
            passD.requestFocus();
        }else{
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){


                        Toast.makeText(Login_doctor.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login_doctor.this, HomeDoctor.class));
                    }else{
                        Toast.makeText(Login_doctor.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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