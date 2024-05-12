package com.example.homerental.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import com.example.homerental.Annonce.Annonce;
import com.example.homerental.Annonce1;
import com.example.homerental.Domain.ItemsDomain;
import com.example.homerental.HomeActivity;
import com.example.homerental.LoginActivity;
import com.example.homerental.Parametres;
import com.example.homerental.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity {
    private TextView titleTxt, addressTxt, bedTxt, bathTxt, wifiTxt, descriptionTxt;

    private ImageView pic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        initView();
        setVariable(StaticVariables.selectedAnnonce);


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

    @SuppressLint("DefaultLocale")
    private void setVariable(Annonce item){
        titleTxt.setText(item.getTitre());
        addressTxt.setText(item.getLocalisation());
        bedTxt.setText(String.format("%d Bed", item.getNbBed()));
        bathTxt.setText(String.format("%d Bath", item.getNbBath()));
        descriptionTxt.setText(item.getDescription());

        if(item.isWifi()){
            wifiTxt.setText("wifi");
        }else{
            wifiTxt.setText("No-wifi");
        }

        Bitmap imageBitmap = decodeBase64ToBitmap(item.getImageData());
        pic.setImageBitmap(imageBitmap);
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

    private Bitmap decodeBase64ToBitmap(String base64String) {
        try {
            byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}