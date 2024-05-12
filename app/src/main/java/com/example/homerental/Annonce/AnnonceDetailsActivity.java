package com.example.homerental.Annonce;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.homerental.R;

public class AnnonceDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce_details);

        // Récupérer les données de l'annonce sélectionnée
        Annonce selectedAnnonce = getIntent().getParcelableExtra("annonce");

        // Afficher les détails de l'annonce dans les vues appropriées
        TextView titreTextView = findViewById(R.id.titreTextView);
        titreTextView.setText(selectedAnnonce.getTitre());

        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(selectedAnnonce.getDescription());

        TextView localisationTextView = findViewById(R.id.localisationTextView);
        localisationTextView.setText(selectedAnnonce.getLocalisation());

        TextView prixTextView = findViewById(R.id.prixTextView);
        prixTextView.setText(selectedAnnonce.getPrix());



        TextView typeTextView = findViewById(R.id.typeTextView);
        typeTextView.setText(selectedAnnonce.getType());

        // Afficher l'image de l'annonce s'il y en a une
        ImageView imageView = findViewById(R.id.imageView);
        Bitmap imageBitmap = decodeBase64ToBitmap(selectedAnnonce.getImageData());
        if (imageBitmap != null) {
            imageView.setImageBitmap(imageBitmap);
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
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
