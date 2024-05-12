package com.example.homerental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.homerental.Adapter.ItemsAdapter;
import com.example.homerental.Annonce.AllAnnoncesActivity;
import com.example.homerental.Annonce.AnnonceActivity;
import com.example.homerental.Domain.ItemsDomain;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterPopular, adapterNew;
    private RecyclerView recyclerViewPopular, recyclerViewNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialiser RecyclerView
        initRecyclerView();

        // Définir un OnClickListener pour le bouton d'annonce
        findViewById(R.id.layoutAppartement).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créez un Intent pour démarrer AllAnnoncesActivity
                Intent intent = new Intent(HomeActivity.this, AllAnnoncesActivity.class);

                // Ajoutez l'information sur le layout spécifique à l'Intent en tant qu'extra
                intent.putExtra("layoutId", R.layout.annonce_item_appartement);

                // Démarrez AllAnnoncesActivity avec l'Intent configuré
                startActivity(intent);
            }
        });



        // Définir un OnClickListener pour le bouton de toutes les annonces


        // Définir les OnClickListener pour les autres éléments
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
                startActivity(new Intent(HomeActivity.this, Annonce1.class));
            }
        });

        annonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Annonce1.class));
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

    public void goToAllAnnonceActivity(View view) {
        startActivity(new Intent(HomeActivity.this, AllAnnoncesActivity.class));
    }

    private void initRecyclerView() {
        ArrayList<ItemsDomain> itemsArrayList = new ArrayList<>();

        // Ajouter des éléments à la liste
        // ...

        recyclerViewPopular = findViewById(R.id.viewPupolar);
        recyclerViewNew = findViewById(R.id.viewNew);

        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNew.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterNew = new ItemsAdapter(itemsArrayList);
        adapterPopular = new ItemsAdapter(itemsArrayList);

        recyclerViewNew.setAdapter(adapterNew);
        recyclerViewPopular.setAdapter(adapterPopular);
    }
}
