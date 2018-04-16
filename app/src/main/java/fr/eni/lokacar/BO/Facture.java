package fr.eni.lokacar.BO;

import java.util.Date;

public class Facture {

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
