package fr.eni.lokacar.BO;

public class DetailsModele {

    private String cNIT;
    private String modeleCommercial;
    private String designation;
    private String boite;
    private String gamme;
    private String carrosserie;

    public DetailsModele() {
    }

    public DetailsModele(String cNIT, String modeleCommercial, String designation, String boite, String gamme, String carrosserie) {
        this.cNIT = cNIT;
        this.modeleCommercial = modeleCommercial;
        this.designation = designation;
        this.boite = boite;
        this.gamme = gamme;
        this.carrosserie = carrosserie;
    }

    public String getcNIT() {
        return cNIT;
    }

    public void setcNIT(String cNIT) {
        this.cNIT = cNIT;
    }

    public String getModeleCommercial() {
        return modeleCommercial;
    }

    public void setModeleCommercial(String modeleCommercial) {
        this.modeleCommercial = modeleCommercial;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getBoite() {
        return boite;
    }

    public void setBoite(String boite) {
        this.boite = boite;
    }

    public String getGamme() {
        return gamme;
    }

    public void setGamme(String gamme) {
        this.gamme = gamme;
    }

    public String getCarrosserie() {
        return carrosserie;
    }

    public void setCarrosserie(String carrosserie) {
        this.carrosserie = carrosserie;
    }

    @Override
    public String toString() {
        return "DetailsModele{" +
                "cNIT='" + cNIT + '\'' +
                ", modeleCommercial='" + modeleCommercial + '\'' +
                ", designation='" + designation + '\'' +
                ", boite='" + boite + '\'' +
                ", gamme='" + gamme + '\'' +
                ", carrosserie='" + carrosserie + '\'' +
                '}';
    }
}
