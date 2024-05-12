package com.example.homerental.Annonce;


import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Annonce implements Parcelable {
    private String id;
    private String titre;
    private String description;
    private String localisation;
    private double prix;
    private String type;
    private boolean wifi = false;
    private int nbBed;
    private int nbBath;

    private String phone;



    private String imageData; // Cette chaîne contient les données de l'image encodées en base64

    // Constructeur par défaut nécessaire pour Firebase
    public Annonce() {}

    // Constructeur avec les champs nécessaires


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    protected Annonce(Parcel in) {
        id = in.readString();
        titre = in.readString();
        description = in.readString();
        localisation = in.readString();
        prix = in.readDouble();
        type = in.readString();
        imageData = in.readString();
        nbBed = in.readInt();
        nbBath = in.readInt();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            wifi = in.readBoolean();
        }
        this.phone = in.readString();

    }
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(titre);
        dest.writeString(description);
        dest.writeString(localisation);
        dest.writeDouble(prix);
        dest.writeString(type);
        dest.writeString(imageData);
        dest.writeInt(nbBath);
        dest.writeInt(nbBed);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            dest.writeBoolean(wifi);
        }
        dest.writeString(phone);
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

    @Override
    public String toString() {
        return "Annonce{" +
                "titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", localisation='" + localisation + '\'' +
                ", prix='" + prix + '\'' +
                ", type='" + type + '\'' +
                ", wifi='" + wifi + '\'' +
                ", nbBed='" + nbBed + '\'' +
                ", nbBath='" + nbBath + '\'' +
                ", imageData='" + imageData + '\'' +
                '}';
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public int getNbBed() {
        return nbBed;
    }

    public void setNbBed(int nbBed) {
        this.nbBed = nbBed;
    }

    public int getNbBath() {
        return nbBath;
    }

    public void setNbBath(int nbBath) {
        this.nbBath = nbBath;
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


}
