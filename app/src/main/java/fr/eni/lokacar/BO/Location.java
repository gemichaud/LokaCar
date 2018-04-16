package fr.eni.lokacar.BO;

import java.util.Date;
import java.util.UUID;

class Location {

    private UUID iD;
    private Date dateDebut;
    private Date dateFinPrevu;
    private String statut;
    private Client client;

    public Location() {
    }

    public Location(UUID iD, Date dateDebut, Date dateFinPrevu, String statut) {
        this.iD = iD;
        this.dateDebut = dateDebut;
        this.dateFinPrevu = dateFinPrevu;
        this.statut = statut;
    }

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

    @Override
    public String toString() {
        return "Location{" +
                "iD=" + iD +
                ", dateDebut=" + dateDebut +
                ", dateFinPrevu=" + dateFinPrevu +
                ", statut='" + statut + '\'' +
                '}';
    }


}
