package fr.eni.lokacar.BO;

public class Modele {

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
