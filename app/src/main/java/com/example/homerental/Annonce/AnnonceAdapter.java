package com.example.homerental.Annonce;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.homerental.R;

import java.util.List;

public class AnnonceAdapter extends ArrayAdapter<Annonce> {

     Context mContext;
     List<Annonce> mAnnonces;

    public AnnonceAdapter(Context context, List<Annonce> annonces) {
        super(context, 0, annonces);
        mContext = context;
        mAnnonces = annonces;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.annonce_item, parent, false);
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
        if (imageBitmap != null) {
            imageView.setImageBitmap(imageBitmap);
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

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
