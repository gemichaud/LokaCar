package fr.eni.lokacar.BO;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.ref.SoftReference;

public class Coordonnee implements Parcelable {

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

    protected Coordonnee(Parcel in) {
        telephone = in.readString();
        email = in.readString();
        adresse = in.readString();
        ville = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(telephone);
        dest.writeString(email);
        dest.writeString(adresse);
        dest.writeString(ville);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Coordonnee> CREATOR = new Creator<Coordonnee>() {
        @Override
        public Coordonnee createFromParcel(Parcel in) {
            return new Coordonnee(in);
        }

        @Override
        public Coordonnee[] newArray(int size) {
            return new Coordonnee[size];
        }
    };

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
