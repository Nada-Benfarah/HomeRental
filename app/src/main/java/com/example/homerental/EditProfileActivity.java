package com.example.homerental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.ArchTaskExecutor;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    EditText edUsername, edEmail, edPassword;
    Button saveButton;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    DatabaseReference reference;
    String emailUser, usernameUser, passwordUser;

    FirebaseUser user;
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
        user= fAuth.getCurrentUser();

        showData();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> userInfo = new HashMap<>();
                userInfo.put("Username",edUsername.getText().toString());
                userInfo.put("Email",edEmail.getText().toString());
                fStore.collection("Users").document(fAuth.getUid()).update(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(),"Data Changed",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });



    }

    public void showData() {

      fStore.collection("Users").document(user.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                edUsername.setText(documentSnapshot.getString("Username"));
                edEmail.setText(documentSnapshot.getString("Email"));
            }
        });

    }

}
