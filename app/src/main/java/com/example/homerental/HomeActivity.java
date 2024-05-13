package com.example.homerental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homerental.Adapter.ItemsAdapter;

import com.example.homerental.Annonce.AllAnnoncesActivity;
import com.example.homerental.Annonce.Annonce;
import com.example.homerental.Annonce.AnnonceActivity;
import com.example.homerental.Domain.ItemsDomain;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterPopular;
    private RecyclerView recyclerViewPopular;

    TextView nameUser;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initRecyclerView();
        ImageView logout = findViewById(R.id.imageView74);
        ImageView profil = findViewById(R.id.imageView73);
        ImageView home = findViewById(R.id.imgHome);
        ImageView annonce = findViewById(R.id.imgAnnounce);
        TextView nameUser = findViewById(R.id.textView6);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        fStore.collection("Users").document(fAuth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                nameUser.setText("Hi, "+documentSnapshot.getString("Username"));

            }
        });


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

                startActivity(new Intent(getApplicationContext(), Parametres.class));

                finish();
            }
        });


        annonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AllAnnoncesActivity.class));
                finish();
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

        ArrayList<Annonce> annoncesList = new ArrayList<>();
        annoncesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                annoncesList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Annonce annonce = snapshot.getValue(Annonce.class);
                    annonce.setId(snapshot.getKey());
                    try{
                        annoncesList.add(annonce);
                    }catch (Exception e) {

                    }

                }
                adapterPopular.notifyDataSetChanged();


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Gérer les erreurs de base de données
            }
        });


                recyclerViewPopular = findViewById(R.id.viewPupolar);

                recyclerViewPopular.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));

                adapterPopular = new ItemsAdapter(annoncesList);

                recyclerViewPopular.setAdapter(adapterPopular);


            }
        }

