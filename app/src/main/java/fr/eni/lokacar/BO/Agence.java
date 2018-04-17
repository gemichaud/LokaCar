package fr.eni.lokacar.BO;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class Agence implements Parcelable {

    private UUID iD;
    private String ville;
    private String Adresse;
    private Gerant gerant;


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

    protected Agence(Parcel in) {
        iD = UUID.fromString(in.readString());
        ville = in.readString();
        Adresse = in.readString();
        gerant = in.readParcelable(Gerant.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iD.toString());
        dest.writeString(ville);
        dest.writeString(Adresse);
        dest.writeParcelable(gerant, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Agence> CREATOR = new Creator<Agence>() {
        @Override
        public Agence createFromParcel(Parcel in) {
            return new Agence(in);
        }

        @Override
        public Agence[] newArray(int size) {
            return new Agence[size];
        }
    };

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
