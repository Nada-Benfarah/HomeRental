package com.example.homerental.Annonce;



public class Annonce {
    private String titre;
    private String description;
    private String localisation;
    private String prix;
    private String type;
    private String wifi;
    private String nbBed;
    private String nbBath;



    private String imageData; // Cette chaîne contient les données de l'image encodées en base64

    // Constructeur par défaut nécessaire pour Firebase
    public Annonce() {}

    // Constructeur avec les champs nécessaires


    public Annonce(String titre, String description, String localisation, String prix, String type, String wifi, String nbBed, String nbBath, String imageData) {
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.prix = prix;
        this.type = type;
        this.wifi = wifi;
        this.nbBed = nbBed;
        this.nbBath = nbBath;
        this.imageData = imageData;
    }

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

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getNbBed() {
        return nbBed;
    }

    public void setNbBed(String nbBed) {
        this.nbBed = nbBed;
    }

    public String getNbBath() {
        return nbBath;
    }

    public void setNbBath(String nbBath) {
        this.nbBath = nbBath;
    }

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


    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }



}
