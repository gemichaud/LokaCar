package fr.eni.lokacar.BO;

import android.os.Parcel;
import android.os.Parcelable;

public class Modele implements Parcelable {

    private String cNIT;
    private String nom;
    private Marque marque;
    private DetailsModele detailModele;

    public Modele() {
    }

    public Modele(String cNIT, String nom, Marque marque, DetailsModele detailModele) {
        this.cNIT = cNIT;
        this.nom = nom;
        this.marque = marque;
        this.detailModele = detailModele;
    }

    protected Modele(Parcel in) {
        cNIT = in.readString();
        nom = in.readString();
        marque = in.readParcelable(Marque.class.getClassLoader());
        detailModele = in.readParcelable(DetailsModele.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cNIT);
        dest.writeString(nom);
        dest.writeParcelable(marque, flags);
        dest.writeParcelable(detailModele, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Modele> CREATOR = new Creator<Modele>() {
        @Override
        public Modele createFromParcel(Parcel in) {
            return new Modele(in);
        }

        @Override
        public Modele[] newArray(int size) {
            return new Modele[size];
        }
    };

    public String getcNIT() {
        return cNIT;
    }

    public void setcNIT(String cNIT) {
        this.cNIT = cNIT;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public DetailsModele getDetailModele() {
        return detailModele;
    }

    public void setDetailModele(DetailsModele detailModele) {
        this.detailModele = detailModele;
    }

    @Override
    public String toString() {
        return "Modele{" +
                "cNIT='" + cNIT + '\'' +
                ", nom='" + nom + '\'' +
                ", marque=" + marque.toString() +
                ", detailModele=" + detailModele.toString() +
                '}';
    }
}
