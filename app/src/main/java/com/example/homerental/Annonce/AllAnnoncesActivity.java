package com.example.homerental.Annonce;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homerental.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllAnnoncesActivity extends AppCompatActivity {

    private ListView annoncesListView;
    private List<Annonce> annoncesList;
    private AnnonceAdapter annonceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_annonces);

        annoncesListView = findViewById(R.id.annoncesListView);
        annoncesList = new ArrayList<>();
        annonceAdapter = new AnnonceAdapter(this, annoncesList);
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
                    annoncesList.add(annonce);
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
