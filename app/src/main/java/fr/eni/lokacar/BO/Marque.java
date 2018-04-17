package fr.eni.lokacar.BO;

import android.os.Parcel;
import android.os.Parcelable;

public class Marque implements Parcelable{

    private int id;
    private String nom;

    public Marque() {
    }

    public Marque(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    protected Marque(Parcel in) {
        id = in.readInt();
        nom = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nom);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Marque> CREATOR = new Creator<Marque>() {
        @Override
        public Marque createFromParcel(Parcel in) {
            return new Marque(in);
        }

        @Override
        public Marque[] newArray(int size) {
            return new Marque[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Marque{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
