package com.example.homerental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Admin extends AppCompatActivity {
    PieChart pieChart;
    FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        pieChart = findViewById(R.id.userPieChart);

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        // Récupérer les données des utilisateurs depuis Firestore
        mFirestore.collection("Users").addSnapshotListener((queryDocumentSnapshots, e) -> {
            if (e != null) {
                Log.e("Firestore", "Erreur lors de l'écoute des modifications :", e);
                return;
            }

            int blockedUsers = 0;
            int nonBlockedUsers = 0;

            for (DocumentSnapshot document : queryDocumentSnapshots) {
                Boolean isBlocked = document.getBoolean("isBlocked");
                if (isBlocked != null) {
                    if (isBlocked) {
                        blockedUsers++;
                    } else {
                        nonBlockedUsers++;
                    }
                } else {
                    Log.e("Firestore", "La valeur de 'isBlocked' est null pour le document : " + document.getId());
                }
            }

            Log.d("Firestore", "Données récupérées avec succès : Bloqués = " + blockedUsers + ", Non bloqués = " + nonBlockedUsers);
            updateChart(blockedUsers, nonBlockedUsers);
        });

    }

    // Mettre à jour le graphique avec les données récupérées
    private void updateChart(int blockedUsers, int nonBlockedUsers) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(blockedUsers, "Bloqués"));
        entries.add(new PieEntry(nonBlockedUsers, "Non bloqués"));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(new int[]{android.R.color.holo_green_dark , android.R.color.holo_blue_dark}, this);
        dataSet.setValueTextSize(15f);
        dataSet.setValueFormatter(new PercentFormatter(pieChart));

        PieData data = new PieData(dataSet);

        pieChart.setData(data);
        pieChart.getDescription().setEnabled(false);
        pieChart.setEntryLabelColor(android.R.color.black);
        pieChart.setEntryLabelTextSize(12f);
        pieChart.setCenterText("Statistiques des utilisateurs");
        pieChart.setCenterTextSize(18f);
        pieChart.animateY(1000);

        // Personnalisation de la légende
        pieChart.getLegend().setEnabled(true);
        pieChart.getLegend().setTextSize(14f);
        pieChart.getLegend().setFormSize(14f);
        pieChart.getLegend().setFormToTextSpace(5f);

        pieChart.invalidate();
    }

    public void redirectToUserManagement(View view) {
        Intent intent = new Intent(this, GestionUsersActivity.class);
        startActivity(intent);
    }

}
