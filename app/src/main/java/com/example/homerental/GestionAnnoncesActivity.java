package com.example.homerental;

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
import com.example.homerental.Adapter.AdminAnnonceAdapter;
import com.example.homerental.Annonce.Annonce;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GestionAnnoncesActivity extends AppCompatActivity {

    ListView annoncesListView;
    List<Annonce> annoncesList;
    AdminAnnonceAdapter annonceAdapter;
    String selectedType = "Tous";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_annonces);

        annoncesListView = findViewById(R.id.annonces1ListView);
        annoncesList = new ArrayList<>();
        annonceAdapter = new AdminAnnonceAdapter(this, annoncesList);
        annoncesListView.setAdapter(annonceAdapter);



        // Récupérer une référence à la base de données Firebase
        DatabaseReference annoncesRef = FirebaseDatabase.getInstance().getReference().child("annonces");

        // Ajouter un écouteur de valeur pour récupérer les annonces
        annoncesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                annoncesList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Annonce annonce = snapshot.getValue(Annonce.class);
                    annonce.setId(snapshot.getKey());
                        annoncesList.add(annonce);

                }
                annonceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Gérer les erreurs de base de données
            }
        });

        // Ajouter un écouteur de clic sur les éléments de la liste
        annoncesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupérer l'annonce sélectionnée
                Annonce selectedAnnonce = annoncesList.get(position);
                StaticVariables.selectedAnnonce = selectedAnnonce;
                Intent intent = new Intent(GestionAnnoncesActivity.this, DetailActivity.class);
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
    public void onBack(View view) {
        startActivity(new Intent(this, Admin.class));
    }

}
