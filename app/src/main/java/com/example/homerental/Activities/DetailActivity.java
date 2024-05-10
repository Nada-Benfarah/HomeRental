package com.example.homerental.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import com.example.homerental.Annonce1;
import com.example.homerental.Domain.ItemsDomain;
import com.example.homerental.HomeActivity;
import com.example.homerental.LoginActivity;
import com.example.homerental.Parametres;
import com.example.homerental.R;
import com.google.firebase.auth.FirebaseAuth;



public class DetailActivity extends AppCompatActivity {
    private TextView titleTxt, addressTxt, bedTxt, bathTxt, wifiTxt, descriptionTxt;

    private ItemsDomain item;
    private ImageView pic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        initView();
        setVariable();



        ImageView logout=findViewById(R.id.imgLogout);
        ImageView profil=findViewById(R.id.imgProfile);
        ImageView home=findViewById(R.id.imgHom);
        ImageView annonce=findViewById(R.id.imgAnnonce);


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
                    startActivity(new Intent(DetailActivity.this, Parametres.class));

            }
        });


        annonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, Annonce.class));
                startActivity(new Intent(DetailActivity.this, Annonce1.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, HomeActivity.class));
            }
        });

    }

    private void setVariable(){
        item=(ItemsDomain) getIntent().getSerializableExtra("object");
        titleTxt.setText(item.getTitle());
        addressTxt.setText(item.getAddress());
        bedTxt.setText(item.getBed()+"Bed");
        bathTxt.setText(item.getBath()+"Bath");
        descriptionTxt.setText(item.getDescription());

        if(item.isWifi()){
            wifiTxt.setText("wifi");
        }else{
            wifiTxt.setText("No-wifi");
        }
        int drawableResourceId=getResources().getIdentifier(item.getPic(),"drawable", getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(pic);
    }
    private void initView(){
        titleTxt=findViewById(R.id.textView9);
        addressTxt=findViewById(R.id.textView15);
        bedTxt=findViewById(R.id.bedTxt);
        bathTxt=findViewById(R.id.bathTxt);
        wifiTxt=findViewById(R.id.wifiTxt);
        descriptionTxt=findViewById(R.id.textView12);
        pic=findViewById(R.id.pic);

    }
}