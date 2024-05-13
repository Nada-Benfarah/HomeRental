package com.example.homerental.Activities;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
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
    private TextView titleTxt, addressTxt, bedTxt, bathTxt, wifiTxt, descriptionTxt,prix,phone;

    private ImageView pic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        initView();
        if (StaticVariables.selectedAnnonce != null) {
            setVariable(StaticVariables.selectedAnnonce);
        }

        // Your other code for buttons initialization and click listeners...
    }

    @SuppressLint("DefaultLocale")
    private void setVariable(Annonce item) {
        if (item != null) {
            titleTxt.setText(item.getTitre());
            addressTxt.setText(item.getLocalisation());
            bedTxt.setText(String.format("%d Bed", item.getNbBed()));
            bathTxt.setText(String.format("%d Bath", item.getNbBath()));
            descriptionTxt.setText(item.getDescription());
            prix.setText(String.format("%.2f DT",item.getPrix()));
            phone.setText(String.format("Phone: %s",item.getPhone()));


            if(item.isWifi()) {
                wifiTxt.setText("wifi");
            } else {
                wifiTxt.setText("No-wifi");
            }

            Bitmap imageBitmap = decodeBase64ToBitmap(item.getImageData());
            pic.setImageBitmap(imageBitmap);
        } else {
            // Handle the case where item is null
        }
    }

    private void initView() {
        titleTxt = findViewById(R.id.textView9);
        addressTxt = findViewById(R.id.textView15);
        bedTxt = findViewById(R.id.bedTxt);
        bathTxt = findViewById(R.id.bathTxt);
        wifiTxt = findViewById(R.id.wifiTxt);
        descriptionTxt = findViewById(R.id.textView12);
        pic = findViewById(R.id.pic);
        prix = findViewById(R.id.prix);
        phone = findViewById(R.id.phone);

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
