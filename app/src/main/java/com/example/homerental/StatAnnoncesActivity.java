package com.example.homerental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homerental.Annonce.Annonce;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class StatAnnoncesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_annonces);

        // Récupérer une référence à la base de données Firebase
        DatabaseReference annoncesRef = FirebaseDatabase.getInstance().getReference().child("annonces");

        // Écouter les changements dans la base de données pour mettre à jour le PieChart
        annoncesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Annonce> annoncesList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Annonce annonce = snapshot.getValue(Annonce.class);
                    annoncesList.add(annonce);
                }
                updatePieChart(annoncesList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Gérer les erreurs de base de données
            }
        });
    }

    private void updatePieChart(List<Annonce> annonces) {
        PieChart pieChart = findViewById(R.id.annoncePieChart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(android.R.color.white);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.animateY(1000);

        int authorizedCount = 0;
        int unauthorizedCount = 0;

        for (Annonce annonce : annonces) {
            if (annonce.getAutorise()) {
                authorizedCount++;
            } else {
                unauthorizedCount++;
            }
        }

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(authorizedCount, "Autorisées"));
        entries.add(new PieEntry(unauthorizedCount, "Non autorisées"));

        PieDataSet dataSet = new PieDataSet(entries, "Statistiques des annonces");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh
    }
    public void onBack1(View view) {

        startActivity(new Intent(this, Admin.class));
    }

}
