package fr.eni.lokacar.BO;

import java.util.UUID;

public class Client {
    private UUID iD;
    private String nom;
    private String prenom;
    private Coordonnee coordonee;

    public Client() {
    }

    public Client(UUID iD, String nom, String prenom) {
        this.iD = iD;
        this.nom = nom;
        this.prenom = prenom;
    }
    public Client( String nom, String prenom) {

        this.nom = nom;
        this.prenom = prenom;
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public UUID getiD() {
        return iD;
    }

    public void setiD(UUID iD) {
        this.iD = iD;
    }

    public Coordonnee getCoordonee() {
        return coordonee;
    }

    public void setCoordonee(Coordonnee coordonee) {
        this.coordonee = coordonee;
    }
}

