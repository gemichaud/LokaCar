package fr.eni.lokacar.BO;

import java.util.UUID;

public class Agence {

    public UUID iD;
    public String ville;
    public String Adresse;
    public Gerant gerant;


    public Agence() {
    }

    public Agence(UUID iD, String ville, String adresse, Gerant gerant) {
        this.iD = iD;
        this.ville = ville;
        Adresse = adresse;
        this.gerant = gerant;
    }

    public Agence(String ville, String adresse, Gerant gerant) {
        this.ville = ville;
        Adresse = adresse;
        this.gerant = gerant;
    }

    public UUID getiD() {
        return iD;
    }

    public void setiD(UUID iD) {
        this.iD = iD;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public Gerant getGerant() {
        return gerant;
    }

    public void setGerant(Gerant gerant) {
        this.gerant = gerant;
    }
}
