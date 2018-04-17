package fr.eni.lokacar.BO;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class Client implements Parcelable{
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


    protected Client(Parcel in) {
        iD = UUID.fromString(in.readString());
        nom = in.readString();
        prenom = in.readString();
        coordonee = in.readParcelable(Coordonnee.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iD.toString());
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeParcelable(coordonee, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

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

