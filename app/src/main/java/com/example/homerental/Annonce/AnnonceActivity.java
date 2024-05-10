package com.example.homerental.Annonce;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homerental.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AnnonceActivity extends AppCompatActivity {

    EditText titre, description, localisation, prix, type, dateCreation, nombreDePlace;
    Button btn, btnChooseImage;
    DatabaseReference annonceRef;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.immmeuble_item);

        // Initialize views
        titre = findViewById(R.id.editTextTitre);
        description = findViewById(R.id.editTextDescription);
        localisation = findViewById(R.id.editTextLocalisation);
        prix = findViewById(R.id.editTextPrix);
        type = findViewById(R.id.editTextType);
        dateCreation = findViewById(R.id.editTextDateCreation);
        nombreDePlace = findViewById(R.id.editTextNombreDePlace);
        btn = findViewById(R.id.buttonAdd);
        btnChooseImage = findViewById(R.id.buttonChooseImage);

        // Set listener for the add button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        // Set listener for the choose image button
        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            // Afficher l'image sélectionnée dans l'ImageView
            ImageView imageView = findViewById(R.id.imageViewSelectedImage);
            imageView.setImageURI(filePath);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    private void insertData() {
        Map<String,Object> map = new HashMap<>();
        map.put("titre", titre.getText().toString());
        map.put("description", description.getText().toString());
        map.put("localisation", localisation.getText().toString());
        map.put("prix", prix.getText().toString());
        map.put("type", type.getText().toString());
        map.put("dateCreation", dateCreation.getText().toString());
        map.put("nombreDePlace", nombreDePlace.getText().toString());
        map.put("isBlocked", true);


        // Check if an image is selected
        if (filePath != null) {
            try {
                // Convert image URI to base64 string
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageData = baos.toByteArray();
                String base64Image = Base64.encodeToString(imageData, Base64.DEFAULT);

                // Store base64 image string in the Realtime Database
                map.put("imageData", base64Image);

                // Save the data to Realtime Database
                FirebaseDatabase.getInstance().getReference().child("annonces").push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(AnnonceActivity.this,"Data inserted successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AnnonceActivity.this,"Error while inserting",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Veuillez sélectionner une image", Toast.LENGTH_SHORT).show();
        }
    }
}
