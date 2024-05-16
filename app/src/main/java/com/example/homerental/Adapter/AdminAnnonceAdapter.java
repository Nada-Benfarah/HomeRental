package com.example.homerental.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.homerental.Annonce.Annonce;
import com.example.homerental.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdminAnnonceAdapter extends ArrayAdapter<Annonce> {

    private Context mContext;
    private List<Annonce> mAnnonces;

    public AdminAnnonceAdapter(Context context, List<Annonce> annonces) {
        super(context, 0, annonces);
        mContext = context;
        mAnnonces = annonces;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.admin_annonce_item, parent, false);
        }

        Annonce currentAnnonce = mAnnonces.get(position);

        TextView titre = listItem.findViewById(R.id.textViewTitre);
        titre.setText(currentAnnonce.getTitre());

        TextView description = listItem.findViewById(R.id.textViewDescription);
        description.setText(currentAnnonce.getDescription());

        TextView localisation = listItem.findViewById(R.id.textViewLocalisation);
        localisation.setText(currentAnnonce.getLocalisation());

        TextView prix = listItem.findViewById(R.id.textViewPrix);
        prix.setText(String.valueOf(currentAnnonce.getPrix()));

        TextView type = listItem.findViewById(R.id.textViewType);
        type.setText(currentAnnonce.getType());

        // Load image into ImageView
        ImageView imageView = listItem.findViewById(R.id.imageViewAnnonce);
        Bitmap imageBitmap = decodeBase64ToBitmap(currentAnnonce.getImageData());
        if (imageBitmap!= null) {
            imageView.setImageBitmap(imageBitmap);
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        // Ajouter les boutons pour autoriser/refuser l'annonce
        Button buttonAutoriser = listItem.findViewById(R.id.buttonAutoriser);
        Button buttonRefuser = listItem.findViewById(R.id.buttonRefuser);

        buttonAutoriser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentAnnonce.setAutorise(true);
                // Mettre à jour la base de données Firebase avec la nouvelle valeur de autorise
                DatabaseReference annoncesRef = FirebaseDatabase.getInstance().getReference().child("annonces");
                annoncesRef.child(currentAnnonce.getId()).child("autorise").setValue(true);
            }
        });

        buttonRefuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentAnnonce.setAutorise(false);
                // Mettre à jour la base de données Firebase avec la nouvelle valeur de autorise
                DatabaseReference annoncesRef = FirebaseDatabase.getInstance().getReference().child("annonces");
                annoncesRef.child(currentAnnonce.getId()).child("autorise").setValue(false);
            }
        });

        return listItem;
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