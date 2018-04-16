package fr.eni.lokacar.BO;

public class Client {
    private int iD;
    private String nom;
    private String prenom;
    private Coordonnee coordonee;

    public Client() {
    }

    public Client(int iD, String nom, String prenom) {
        this.iD = iD;
        this.nom = nom;
        this.prenom = prenom;
    }
    public Client( String nom, String prenom) {

        this.nom = nom;
        this.prenom = prenom;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
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
}
