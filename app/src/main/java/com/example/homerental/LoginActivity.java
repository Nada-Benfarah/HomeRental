package com.example.homerental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseUser; // Import manquant

import androidx.appcompat.app.AlertDialog;


import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Patterns;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    EditText edEmail, edPassword;
    Button btn;
    TextView tv, tvForgotPassword;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = findViewById(R.id.editTextLoginEmail);
        edPassword = findViewById(R.id.editTextLoginPassword);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textViewNewUser);
        tvForgotPassword = findViewById(R.id.tvForgotPass);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkField(edEmail);
                checkField(edPassword);

                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                if (valid) {
                    fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = fAuth.getCurrentUser();
                            DocumentReference userRef = fStore.collection("Users").document(user.getUid());
                            userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    boolean isBlocked = documentSnapshot.getBoolean("isBlocked");

                                    System.out.println(isBlocked);


                                    if (!isBlocked) {
                                        Toast.makeText(LoginActivity.this, "Loggedin Successfuly", Toast.LENGTH_SHORT).show();
                                        checkUserAccessLeval(authResult.getUser().getUid());
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        finish();
                                    } else {


                                        //fAuth.signOut();
                                        Toast.makeText(LoginActivity.this, "Your account is blocked. Please contact admin.", Toast.LENGTH_SHORT).show();

                                        Toast.makeText(LoginActivity.this, "Your account is blocked. Please contact admin.", Toast.LENGTH_SHORT).show();
                                        fAuth.signOut();

                                        Toast.makeText(LoginActivity.this, "Your account is blocked. Please contact admin.", Toast.LENGTH_SHORT).show();
                                        fAuth.signOut();


                                    }
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this, e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    });



                            fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(LoginActivity.this, "Loggedin Successfuly", Toast.LENGTH_SHORT).show();
                                    checkUserAccessLeval(authResult.getUser().getUid());
                                   

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(LoginActivity.this, e.getMessage(),  Toast.LENGTH_SHORT).show();
                                }
                            });

                }
            }
        });

        tv.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        }));


        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                //convert from xml to view object (classe java)
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_forgot, null);
                EditText emailBox = dialogView.findViewById(R.id.emailBox);
                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userEmail = emailBox.getText().toString();
                        if (TextUtils.isEmpty(userEmail) && !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                            Toast.makeText(LoginActivity.this, "Enter your registered email id", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        fAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Check your email", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Unable to send, failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                dialogView.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                if (dialog.getWindow() != null){
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                dialog.show();
            }
        });

    }




    private void checkUserAccessLeval(String uid) {
        DocumentReference df = fStore.collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","onSuccess: " + documentSnapshot.getData());

                if(documentSnapshot.getString("isAdmin") !=null){
                    startActivity(new Intent(getApplicationContext(),Admin.class));
                    finish();
                }

                if(documentSnapshot.getString("isUser") !=null){
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    finish();
                }
            }
        });
    }

    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()) {
            textField.setError("Please fill all details");
            valid = false;
        }else{
            valid=true;
        }
        return valid;
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            finish();
        }
    }
}
