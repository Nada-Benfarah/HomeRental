package com.example.homerental.Annonce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homerental.Activities.DetailActivity;
import com.example.homerental.Activities.StaticVariables;
import com.example.homerental.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllAnnoncesActivity extends AppCompatActivity {

    ListView annoncesListView;
    private List<Annonce> annoncesList;
    private AnnonceAdapter annonceAdapter;
    private String selectedType = "Tous";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_annonces);

        annoncesListView = findViewById(R.id.annoncesListView);
        annoncesList = new ArrayList<>();
        annonceAdapter = new AnnonceAdapter(this, annoncesList);
        annoncesListView.setAdapter(annonceAdapter);

        // Initialiser les boutons de filtrage
        Button buttonAll = findViewById(R.id.buttonAll);
        Button buttonAppartement = findViewById(R.id.buttonAppartement);
        Button buttonVilla = findViewById(R.id.buttonVilla);

        // Récupérer une référence à la base de données Firebase
        DatabaseReference annoncesRef = FirebaseDatabase.getInstance().getReference().child("annonces");

        // Ajouter un écouteur de valeur pour récupérer les annonces
        annoncesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                annoncesList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Annonce annonce = snapshot.getValue(Annonce.class);
                    if (annonce.getAutorise()) {
                        annoncesList.add(annonce);
                    }
                }
                annonceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Gérer les erreurs de base de données
            }
        });

        // Ajouter un écouteur de clic sur les boutons de filtrage
        buttonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterByType("Tous");
            }
        });

        buttonAppartement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterByType("Apartment");
            }
        });

        buttonVilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterByType("Villa");
            }
        });

        // Ajouter un écouteur de clic sur les éléments de la liste
        annoncesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupérer l'annonce sélectionnée
                Annonce selectedAnnonce = annoncesList.get(position);
                StaticVariables.selectedAnnonce = selectedAnnonce;
                Intent intent = new Intent(AllAnnoncesActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void filterByType(String type) {
        selectedType = type;
        // Rafraîchir la liste des annonces en appliquant le filtrage
        DatabaseReference annoncesRef = FirebaseDatabase.getInstance().getReference().child("annonces");
        annoncesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                annoncesList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Annonce annonce = snapshot.getValue(Annonce.class);
                    if (annonce.getAutorise() && (selectedType.equals("Tous") || annonce.getType().equals(selectedType))) {
                        annoncesList.add(annonce);
                    }
                }
                annonceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Gérer les erreurs de base de données
            }
        });
    }
}
