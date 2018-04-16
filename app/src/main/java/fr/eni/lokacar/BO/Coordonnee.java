package fr.eni.lokacar.BO;

import java.lang.ref.SoftReference;

public class Coordonnee {

    private String telephone;
    private String email;
    private String adresse;
    private String ville;


    public Coordonnee() {
    }

    public Coordonnee(String telephone, String email, String adresse, String ville) {
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
        this.ville = ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
