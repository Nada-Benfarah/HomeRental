package com.example.homerental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import com.example.homerental.Adapter.ItemsAdapter;

import com.example.homerental.Annonce.AllAnnoncesActivity;
import com.example.homerental.Annonce.Annonce;
import com.example.homerental.Annonce.AnnonceActivity;
import com.example.homerental.Domain.ItemsDomain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterPopular, adapterNew;
    private RecyclerView recyclerViewPopular, recyclerViewNew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initRecyclerView();
        ImageView logout = findViewById(R.id.imageView74);
        ImageView profil = findViewById(R.id.imageView73);
        ImageView home = findViewById(R.id.imgHome);
        ImageView annonce = findViewById(R.id.imgAnnounce);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();

            }
        });


        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Annonce.class));

                startActivity(new Intent(getApplicationContext(), Parametres.class));
                finish();
                startActivity(new Intent(HomeActivity.this, Annonce1.class));

            }
        });


        annonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AllAnnoncesActivity.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
            }
        });


    }

    public void goToAnnonceActivity(View view) {
        startActivity(new Intent(HomeActivity.this, AnnonceActivity.class));
    }


    private void initRecyclerView() {

        DatabaseReference annoncesRef = FirebaseDatabase.getInstance().getReference().child("annonces");

        ArrayList<ItemsDomain> annoncesList = new ArrayList<>();
        annoncesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                annoncesList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Annonce annonce = snapshot.getValue(Annonce.class);
                    try{
                        System.out.println(annonce);
                        int nbBed = Integer.parseInt(annonce.getNbBed());
                        int nbBath = Integer.parseInt(annonce.getNbBath());
                        int prix = (int )Double.parseDouble(annonce.getPrix());
                        boolean wifi = Boolean.getBoolean(annonce.getWifi());
                        annoncesList.add(new ItemsDomain(annonce.getTitre(), annonce.getLocalisation(), annonce.getDescription(), nbBed
                                , nbBath, prix, annonce.getImageData()
                                , wifi ));
                    }catch (Exception e) {

                    }

                }
                adapterNew.notifyDataSetChanged();
                adapterPopular.notifyDataSetChanged();


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Gérer les erreurs de base de données
            }
        });


                recyclerViewPopular = findViewById(R.id.viewPupolar);
                recyclerViewNew = findViewById(R.id.viewNew);

                recyclerViewPopular.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));
                recyclerViewNew.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));

                adapterNew = new ItemsAdapter(annoncesList);
                adapterPopular = new ItemsAdapter(annoncesList);

                recyclerViewNew.setAdapter(adapterNew);
                recyclerViewPopular.setAdapter(adapterPopular);


            }
        }

