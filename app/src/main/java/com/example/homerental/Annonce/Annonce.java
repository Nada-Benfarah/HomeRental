package com.example.homerental.Annonce;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Annonce implements Parcelable {

    private String titre;
    private String description;
    private String localisation;
    private String prix;
    private String type;
    private String dateCreation;
    private String nombreDePlace;
    private String imageData; // Cette chaîne contient les données de l'image encodées en base64

    // Constructeur par défaut nécessaire pour Firebase
    public Annonce() {}

    // Constructeur avec les champs nécessaires
    public Annonce(String titre, String description, String localisation, String prix, String type, String dateCreation, String nombreDePlace, String imageData) {
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.prix = prix;
        this.type = type;
        this.dateCreation = dateCreation;
        this.nombreDePlace = nombreDePlace;
        this.imageData = imageData;
    }

    protected Annonce(Parcel in) {
        titre = in.readString();
        description = in.readString();
        localisation = in.readString();
        prix = in.readString();
        type = in.readString();
        dateCreation = in.readString();
        nombreDePlace = in.readString();
        imageData = in.readString();
    }

    public static final Creator<Annonce> CREATOR = new Creator<Annonce>() {
        @Override
        public Annonce createFromParcel(Parcel in) {
            return new Annonce(in);
        }

        @Override
        public Annonce[] newArray(int size) {
            return new Annonce[size];
        }
    };

    // Getters et setters pour chaque champ
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getNombreDePlace() {
        return nombreDePlace;
    }

    public void setNombreDePlace(String nombreDePlace) {
        this.nombreDePlace = nombreDePlace;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titre);
        dest.writeString(description);
        dest.writeString(localisation);
        dest.writeString(prix);
        dest.writeString(type);
        dest.writeString(dateCreation);
        dest.writeString(nombreDePlace);
        dest.writeString(imageData);
    }

}
