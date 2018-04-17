package fr.eni.lokacar.BO;

import android.os.Parcel;
import android.os.Parcelable;

public class DetailsModele implements Parcelable{

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

    protected DetailsModele(Parcel in) {
        cNIT = in.readString();
        modeleCommercial = in.readString();
        designation = in.readString();
        boite = in.readString();
        gamme = in.readString();
        carrosserie = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cNIT);
        dest.writeString(modeleCommercial);
        dest.writeString(designation);
        dest.writeString(boite);
        dest.writeString(gamme);
        dest.writeString(carrosserie);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DetailsModele> CREATOR = new Creator<DetailsModele>() {
        @Override
        public DetailsModele createFromParcel(Parcel in) {
            return new DetailsModele(in);
        }

        @Override
        public DetailsModele[] newArray(int size) {
            return new DetailsModele[size];
        }
    };

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
