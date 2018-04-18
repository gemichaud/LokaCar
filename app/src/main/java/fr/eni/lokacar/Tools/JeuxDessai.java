package fr.eni.lokacar.Tools;

import android.content.Context;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import fr.eni.lokacar.BO.Agence;
import fr.eni.lokacar.BO.Client;
import fr.eni.lokacar.BO.Coordonnee;
import fr.eni.lokacar.BO.DetailsModele;
import fr.eni.lokacar.BO.Gerant;
import fr.eni.lokacar.BO.Location;
import fr.eni.lokacar.BO.Marque;
import fr.eni.lokacar.BO.Modele;
import fr.eni.lokacar.BO.Vehicule;
import fr.eni.lokacar.DAL.DAO.GerantDAO;
import fr.eni.lokacar.DAL.DAO.MarqueDAO;
import fr.eni.lokacar.DAL.DAO.ModeleDAO;

public class JeuxDessai {

    Agence a;
    Gerant g;
    Vehicule vehicule;
    Location location;
    Modele modele;
    Marque marque;
    DetailsModele detailsModele;
    Client client;
    Coordonnee coordonnee;

    public void insertGerant(Context c ){

        this.g = new Gerant();
        g.setMotDePasse("test");
        g.setLogin("test");
        g.setiD(UUID.randomUUID());
        g.setPrenom("test");
        g.setNom("test");

        GerantDAO gDao = new GerantDAO(c);
        gDao.insertGerant(g);
        insertAgence(c, g);

    }

    public void insertAgence(Context c , Gerant g){
        this.a = new Agence();
        a.setiD( UUID.randomUUID());
        a.setAdresse("3 rue de toto");
        a.setVille("Nantes");
        a.setGerant(g);

    }

    public void insertMarque(Context c){
        this.marque = new Marque();
        marque.setId(1);
        marque.setNom("Citroen");

       /* MarqueDAO marqueDAO = new MarqueDAO(c);
        marqueDAO.insertMarque(marque);*/
    }

    public void insertDetailModele(Context c){
        this.detailsModele = new DetailsModele();
        detailsModele.setBoite("M");
        detailsModele.setCarrosserie("MONOSPACE");
        detailsModele.setcNIT("azerty1234");
        detailsModele.setDesignation("Citroen Picasso");
        detailsModele.setGamme("Familiale");
        detailsModele.setModeleCommercial("Citroen Picasso");


    }


    public void insertModele(Context c){
        this.modele = new Modele();
        modele.setcNIT("azerty1234");
        modele.setNom("Picasso");
        insertDetailModele(c);
        modele.setDetailModele(detailsModele);
        insertMarque(c);
        modele.setMarque(marque);
    }

    public void insertVehicule(Context c){
        this.vehicule = new Vehicule();
        vehicule.setPrixJournalier(50);
        vehicule.setEtat("LB");
        vehicule.setKilometrage(54023);
        vehicule.setImmatriculation("DE456EF");
        vehicule.setAgence(a);
        insertModele(c);
        vehicule.setModele(modele);

    }

    public void insertCoordonnee(Context c){
        this.coordonnee = new Coordonnee();
        coordonnee.setAdresse("2 rue du stade");
        coordonnee.setEmail("toto.duchmol@mail.com");
        coordonnee.setTelephone("0623154578");
        coordonnee.setVille("Moncuq");
    }

    public void insertClient(Context c){
        this.client = new Client();
        client.setiD(UUID.randomUUID());
        client.setPrenom("Toto");
        client.setNom("Duchmol");
        insertCoordonnee(c);
        client.setCoordonee(coordonnee);
    }

    public List<Location> getLocations(Context c){

        List<Location> lstLoc = new ArrayList<Location>();


        Location loc = new Location();
        loc.setStatut("EC");
        loc.setDateDebut(new Date());
        loc.setDateFinPrevu(new Date());
        loc.setiD(UUID.randomUUID());
        insertVehicule(c);
        loc.setVehicule(vehicule);
        insertClient(c);

        System.out.println("Date debut"+loc.getDateDebut());
        System.out.println("Date fin"+loc.getDateFinPrevu());

        loc.setClient(client);


        lstLoc.add(loc);
        lstLoc.add(loc);
        lstLoc.add(loc);
        lstLoc.add(loc);
        lstLoc.add(loc);


        return lstLoc;

    }





}
