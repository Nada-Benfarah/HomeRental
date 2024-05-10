package com.example.homerental.Annonce;



public class Annonce {
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



}
