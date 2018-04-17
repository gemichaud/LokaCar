package fr.eni.lokacar.BO;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Facture implements Parcelable{

    private Date dateFinReel;
    private Location location;
    private double montant;

    public Facture() {
    }

    public Facture(Date dateFinReel, Location location, double montant) {
        this.dateFinReel = dateFinReel;
        this.location = location;
        this.montant = montant;
    }

    protected Facture(Parcel in) {
        location = in.readParcelable(Location.class.getClassLoader());
        montant = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(location, flags);
        dest.writeDouble(montant);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Facture> CREATOR = new Creator<Facture>() {
        @Override
        public Facture createFromParcel(Parcel in) {
            return new Facture(in);
        }

        @Override
        public Facture[] newArray(int size) {
            return new Facture[size];
        }
    };

    public Date getDateFinReel() {
        return dateFinReel;
    }

    public void setDateFinReel(Date dateFinReel) {
        this.dateFinReel = dateFinReel;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "dateFinReel=" + dateFinReel +
                ", location=" + location +
                ", montant=" + montant +
                '}';
    }
}
