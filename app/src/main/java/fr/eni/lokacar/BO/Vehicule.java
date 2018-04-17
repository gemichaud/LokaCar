package fr.eni.lokacar.BO;

import android.os.Parcel;
import android.os.Parcelable;

public class Vehicule implements Parcelable {

    private String immatriculation;
    private String etat;
    private int kilometrage;
    private Modele modele;
    private Agence agence;

    public Vehicule() {
    }

    public Vehicule(String immatriculation, String etat, int kilometrage, Modele modele, Agence agence) {
        this.immatriculation = immatriculation;
        this.etat = etat;
        this.kilometrage = kilometrage;
        this.modele = modele;
        this.agence = agence;
    }

    protected Vehicule(Parcel in) {
        immatriculation = in.readString();
        etat = in.readString();
        kilometrage = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(immatriculation);
        dest.writeString(etat);
        dest.writeInt(kilometrage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Vehicule> CREATOR = new Creator<Vehicule>() {
        @Override
        public Vehicule createFromParcel(Parcel in) {
            return new Vehicule(in);
        }

        @Override
        public Vehicule[] newArray(int size) {
            return new Vehicule[size];
        }
    };

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "immatriculation='" + immatriculation + '\'' +
                ", etat='" + etat + '\'' +
                ", kilometrage=" + kilometrage +
                ", modele=" + modele.toString() +
                ", agence=" + agence.toString() +
                '}';
    }
}
