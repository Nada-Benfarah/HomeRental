package com.example.homerental.Annonce;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.homerental.R;

public class AnnonceDetails extends AppCompatActivity {

    private TextView titreTextView, descriptionTextView, localisationTextView, prixTextView, typeTextView;
    private TextView bedTxt, bathTxt, wifiTxt;
    private ImageView imageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail1);

        // Récupérer les données de l'annonce sélectionnée depuis l'intent
        Annonce selectedAnnonce = getIntent().getParcelableExtra("annonce");

        titreTextView = findViewById(R.id.titreTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        localisationTextView = findViewById(R.id.localisationTextView);
        prixTextView = findViewById(R.id.prixTextView);
        typeTextView = findViewById(R.id.typeTextView);
        imageView = findViewById(R.id.imageView);
        bedTxt = findViewById(R.id.bedTxt);
        bathTxt = findViewById(R.id.bathTxt);
        wifiTxt = findViewById(R.id.wifiTxt);

        if (selectedAnnonce != null) {
            titreTextView.setText(selectedAnnonce.getTitre());
            descriptionTextView.setText(selectedAnnonce.getDescription());
            localisationTextView.setText(selectedAnnonce.getLocalisation());
            prixTextView.setText(String.valueOf(selectedAnnonce.getPrix()));
            typeTextView.setText(selectedAnnonce.getType());

            // Set the values for bed, bath, and wifi TextViews
            bedTxt.setText(String.format("%d Bed", selectedAnnonce.getNbBed()));
            bathTxt.setText(String.format("%d Bath", selectedAnnonce.getNbBath()));
            wifiTxt.setText(selectedAnnonce.isWifi() ? "Wifi" : "No Wifi");

            // Afficher l'image de l'annonce s'il y en a une
            Bitmap imageBitmap = decodeBase64ToBitmap(selectedAnnonce.getImageData());
            if (imageBitmap != null) {
                imageView.setImageBitmap(imageBitmap);
                imageView.setVisibility(View.VISIBLE);
            } else {
                imageView.setVisibility(View.GONE);
            }
        } else {
            // Gérer le cas où l'annonce sélectionnée est null
        }
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
