package fr.eni.lokacar.BO;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

 public class Gerant implements Parcelable{

    private UUID iD;
    private String nom;
    private String prenom;
    private String login;
    private   String motDePasse;


    public Gerant() {
    }

    public Gerant(String nom, String prenom, String login, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.motDePasse = motDePasse;
    }

    public Gerant(UUID iD, String nom, String prenom, String login, String motDePasse) {
        this.iD = iD;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.motDePasse = motDePasse;
    }

     protected Gerant(Parcel in) {
        iD = UUID.fromString(in.readString());
         nom = in.readString();
         prenom = in.readString();
         login = in.readString();
         motDePasse = in.readString();
     }

     @Override
     public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iD.toString());
         dest.writeString(nom);
         dest.writeString(prenom);
         dest.writeString(login);
         dest.writeString(motDePasse);
     }

     @Override
     public int describeContents() {
         return 0;
     }

     public static final Creator<Gerant> CREATOR = new Creator<Gerant>() {
         @Override
         public Gerant createFromParcel(Parcel in) {
             return new Gerant(in);
         }

         @Override
         public Gerant[] newArray(int size) {
             return new Gerant[size];
         }
     };

     public UUID getiD() {
        return iD;
    }

    public void setiD(UUID iD) {
        this.iD = iD;
    }

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
