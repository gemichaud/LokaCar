package fr.eni.lokacar.BO;

import java.util.UUID;

 public class Gerant {

    public UUID iD;
    public String nom;
    public String prenom;
    public String login;
    public  String motDePasse;


    public Gerant() {
    }

    public Gerant(String nom, String prenom, String login, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.motDePasse = motDePasse;
    }

    public Gerant(UUID iD, String nom, String prenom, String login, String motDePasse) {
        this.iD = iD;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.motDePasse = motDePasse;
    }

    public UUID getiD() {
        return iD;
    }

    public void setiD(UUID iD) {
        this.iD = iD;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
