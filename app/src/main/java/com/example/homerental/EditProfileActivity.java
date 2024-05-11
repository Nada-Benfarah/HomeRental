package com.example.homerental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.ArchTaskExecutor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    EditText edUsername, edEmail, edPassword;
    Button saveButton;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    DatabaseReference reference;
    String emailUser, usernameUser, passwordUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        reference = FirebaseDatabase.getInstance().getReference("users");
        edUsername = findViewById(R.id.editTextRegUsername);
        edPassword = findViewById(R.id.editTextRegPassword);
        edEmail = findViewById(R.id.editTextRegEmail);
        saveButton = findViewById(R.id.buttonSave);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        showData();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNameChanged() || isPasswordChanged() || isEmailChanged()) {
                    Toast.makeText(EditProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfileActivity.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private boolean isNameChanged() {
        if (!usernameUser.equals(edUsername.getText().toString())) {

            usernameUser = edUsername.getText().toString();
            reference.child(usernameUser).child("name").setValue(edUsername.getText().toString());


            return true;
        } else {
            return false;
        }
    }

    private boolean isEmailChanged() {
        if (!emailUser.equals(edEmail.getText().toString())) {

            emailUser = edEmail.getText().toString();
            reference.child(usernameUser).child("email").setValue(edEmail.getText().toString());

            return true;
        } else {
            return false;
        }
    }

    private boolean isPasswordChanged() {
        if (!passwordUser.equals(edPassword.getText().toString())) {

            passwordUser = edPassword.getText().toString();
            reference.child(usernameUser).child("password").setValue(edPassword.getText().toString());


            passwordUser = edPassword.getText().toString();
            reference.child(usernameUser).child("password").setValue(edPassword.getText().toString());

            return true;
        } else {
            return false;
        }
    }

    public void showData() {
        Intent intent = getIntent();
        usernameUser = intent.getStringExtra("username");
        emailUser = intent.getStringExtra("email");
        passwordUser = intent.getStringExtra("password");
        edUsername.setText(usernameUser);
        edEmail.setText(emailUser);
        edPassword.setText(passwordUser);
    }

    public static boolean isValid(String passwordhere) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (passwordhere.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < passwordhere.length(); p++) {
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < passwordhere.length(); r++) {
                if (Character.isDigit(passwordhere.charAt(r))) {
                    f2 = 1;
                }
            }
            for (int s = 0; s < passwordhere.length(); s++) {
                char c = passwordhere.charAt(s);
                if (c >= 33 && c <= 46 || c == 64) {
                    f3 = 1;
                }
            }
            return f1 == 1 && f2 == 1 && f3 == 1;
        }
    }
}
