package com.example.homerental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.auth.FirebaseAuth;


public class ForgotPass extends AppCompatActivity {


//        EditText edEmail;
//        Button btnReset;
//        TextView tvBack;
//
//    ProgressBar progressBar;
//    FirebaseAuth mAuth;
//    String strEmail;
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_login);
//
//            edEmail = findViewById(R.id.edEmail);
//            btnReset = findViewById(R.id.buttonReset);
//            tvBack = findViewById(R.id.tvBack);
//            mAuth = FirebaseAuth.getInstance();
//
//
//
//
//            btnReset.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    strEmail = edEmail.getText().toString().trim();
//                    if (!TextUtils.isEmpty(strEmail)) {
//                        ResetPassword();
//                    } else {
//                        edEmail.setError("Email field can't be empty");
//                    }
//                }
//            });
//
//
//            //Back Button Code
//            tvBack.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onBackPressed();
//                }
//            });
//
//        }
//
//    private void ResetPassword() {
//        progressBar.setVisibility(View.VISIBLE);
//        btnReset.setVisibility(View.INVISIBLE);
//
//        mAuth.sendPasswordResetEmail(strEmail)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(ForgotPass.this, "Reset Password link has been sent to your registered Email", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(ForgotPass.this, LoginActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(ForgotPass.this, "Error :- " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                        progressBar.setVisibility(View.INVISIBLE);
//                        btnReset.setVisibility(View.VISIBLE);
//                    }
//                });
//    }
}