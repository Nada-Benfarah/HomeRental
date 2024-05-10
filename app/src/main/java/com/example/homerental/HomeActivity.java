package com.example.homerental;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import com.example.homerental.Adapter.ItemsAdapter;

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



        initRecyclerView();
        ImageView home=findViewById(R.id.imgHome);
        ImageView annonce=findViewById(R.id.imgAnnounce);
        ImageView logout=findViewById(R.id.imageView74);
        ImageView profil=findViewById(R.id.imageView73);
        initRecyclerView();
        ImageView logout=findViewById(R.id.imageView74);
        ImageView profil=findViewById(R.id.imageView73);
        ImageView home=findViewById(R.id.imgHome);
        ImageView annonce=findViewById(R.id.imgAnnounce);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();

            }
        });


        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Annonce.class));

                startActivity(new Intent(getApplicationContext(),Parametres.class));
                finish();
                startActivity(new Intent(HomeActivity.this, Annonce1.class));

            }
        });




        annonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Annonce.class));
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
    }
    public void goToAnnonceActivity(View view) {
        startActivity(new Intent(HomeActivity.this, AnnonceActivity.class));
    }
    public void goToAllAnnonceActivity(View view) {
        startActivity(new Intent(HomeActivity.this, AllAnnoncesActivity.class));
    }



//    public void logoutUser(View v){
//        FirebaseAuth.getInstance().signOut();
//        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//        finish();
//
//    }
//
//    public void EditProfil(View v){
//        FirebaseAuth.getInstance().signOut();
//        startActivity(new Intent(getApplicationContext(),EditProfileActivity.class));
//        finish();
//
//    }



    private void initRecyclerView(){
        ArrayList<ItemsDomain> ItemsArraylist=new ArrayList<>();

        ItemsArraylist.add(new ItemsDomain("House with a great view", "san francisco, ca 9410", "This 2 bed /1 bath home boasts an enormous,\n"+
                "open-living plan, accented by striking \n"+
                "architectural features and high-end finishes .\n"+
                "feel inspired by open sight lines that \n+" +
                "cofferes cellings", 2,1,841456, "pic1", true));

        ItemsArraylist.add(new ItemsDomain("House with a great view", "san francisco, ca 9410", "This 2 bed /1 bath home boasts an enormous,\n"+
                "open-living plan, accented by striking \n"+
                "architectural features and high-end finishes .\n"+
                "feel inspired by open sight lines that \n+" +
                "cofferes cellings", 2,1,841456, "pic1", true));


        ItemsArraylist.add(new ItemsDomain("House with a great view", "san francisco, ca 9410", "This 2 bed /1 bath home boasts an enormous,\n"+
                "open-living plan, accented by striking \n"+
                "architectural features and high-end finishes .\n"+
                "feel inspired by open sight lines that \n+" +
                "cofferes cellings", 2,1,841456, "pic1", true));


        ItemsArraylist.add(new ItemsDomain("House with a great view", "san francisco, ca 9410", "This 2 bed /1 bath home boasts an enormous,\n"+
                "open-living plan, accented by striking \n"+
                "architectural features and high-end finishes .\n"+
                "feel inspired by open sight lines that \n+" +
                "cofferes cellings", 2,1,841456, "pic1", true));


        recyclerViewPopular=findViewById(R.id.viewPupolar);
        recyclerViewNew=findViewById(R.id.viewNew);

        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewNew.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        adapterNew=new ItemsAdapter(ItemsArraylist);
        adapterPopular=new ItemsAdapter(ItemsArraylist);

        recyclerViewNew.setAdapter(adapterNew);
        recyclerViewPopular.setAdapter(adapterPopular);


    }



}