package com.example.homerental;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homerental.Adapter.UsersAdapter;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

public class GestionUsersActivity extends AppCompatActivity {


    RecyclerView recyclerViewUsers;
    ArrayList<DocumentSnapshot> userList; // Utilise DocumentSnapshot directement
    UsersAdapter usersAdapter;
    FirebaseFirestore fStore;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_users);

        // Initialisation de Firestore
        fStore = FirebaseFirestore.getInstance();

        // Initialisation de la liste des utilisateurs
        userList = new ArrayList<>();

        // Initialisation du RecyclerView
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));

        // Initialisation de l'adaptateur
        usersAdapter = new UsersAdapter(this, userList);

        // Attribution de l'adaptateur au RecyclerView
        recyclerViewUsers.setAdapter(usersAdapter);

        // Charger les utilisateurs depuis Firestore
        loadUsersFromFirestore();
    }

    private void loadUsersFromFirestore() {
        fStore.collection("Users").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult()) {
                            // Add document to userList
                            userList.add(document);
                        }
                        usersAdapter.notifyDataSetChanged();
                    } else {
                        // Catch specific exceptions
                        Exception e = task.getException();
                        if (e instanceof FirebaseFirestoreException) {
                            Log.e("FirestoreError", "Error loading users from Firestore", e);
                            displayErrorMessage("Error loading users from Firestore");
                        } else if (e instanceof NullPointerException) {
                            Log.e("NullPointerError", "Null value encountered while loading users", e);
                            displayErrorMessage("Error loading users: null value encountered");
                        } else {
                            Log.e("Error", "Unknown error loading users", e);
                            displayErrorMessage("Error loading users: unknown error");
                        }
                    }
                });
    }

    private void displayErrorMessage(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
