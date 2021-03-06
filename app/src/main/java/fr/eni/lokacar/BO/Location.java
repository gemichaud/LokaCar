package fr.eni.lokacar.BO;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class Location implements Parcelable{

    private UUID iD;
    private Date dateDebut;
    private Date dateFinPrevu;
    private String statut;
    private Client client;
    private Vehicule vehicule;

    public Location() {
    }

    public Location(UUID iD, Date dateDebut, Date dateFinPrevu, String statut, Client client,Vehicule vehicule) {
        this.iD = iD;
        this.dateDebut = dateDebut;
        this.dateFinPrevu = dateFinPrevu;
        this.statut = statut;
        this.client = client;
        this.vehicule = vehicule;
    }


    protected Location(Parcel in) {
        iD = UUID.fromString(in.readString());
        dateDebut = new Timestamp(in.readLong());
        dateFinPrevu = new Timestamp(in.readLong());
        statut = in.readString();
        client = in.readParcelable(Client.class.getClassLoader());
        vehicule = in.readParcelable(Vehicule.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iD.toString());
        dest.writeLong(dateDebut.getTime());
        dest.writeLong(dateFinPrevu.getTime());
        dest.writeString(statut);
        dest.writeParcelable(client, flags);
        dest.writeParcelable(vehicule, flags);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public UUID getiD() {
        return iD;
    }

    public void setiD(UUID iD) {
        this.iD = iD;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFinPrevu() {
        return dateFinPrevu;
    }

    public void setDateFinPrevu(Date dateFinPrevu) {
        this.dateFinPrevu = dateFinPrevu;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    @Override
    public String toString() {
        return "Location{" +
                "iD=" + iD +
                ", dateDebut=" + dateDebut +
                ", dateFinPrevu=" + dateFinPrevu +
                ", statut='" + statut + '\'' +
                ", client=" + client +
                ", vehicule=" + vehicule.toString() +
                '}';
    }
}
