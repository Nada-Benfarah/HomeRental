package com.example.homerental;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void onUserManagementClick(View view) {
        startActivity(new Intent(this, GestionUsersActivity.class));
    }

    public void onAdManagementClick(View view) {
        startActivity(new Intent(this, GestionAnnoncesActivity.class));
    }

    public void onUserStatsClick(View view) {
        startActivity(new Intent(this, StatUsersActivity.class));
    }

    public void onAdStatsClick(View view) {
        startActivity(new Intent(this, StatAnnoncesActivity.class));
    }

    public void onHomeClick(View view) {
        startActivity(new Intent(this, HomeActivity.class));

    }
    public void onProfileClick(View view) {
        startActivity(new Intent(this, Admin.class));

    }
    // Déconnexion de l'administrateur
    public void onLogoutClick(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();

    }
}