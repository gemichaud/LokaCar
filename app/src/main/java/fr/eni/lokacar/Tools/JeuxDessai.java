package fr.eni.lokacar.Tools;

import android.content.Context;

import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
import fr.eni.lokacar.DAL.DAO.AgenceDAO;
import fr.eni.lokacar.DAL.DAO.DetailModelDAO;
import fr.eni.lokacar.DAL.DAO.GerantDAO;
import fr.eni.lokacar.DAL.DAO.LocationDAO;
import fr.eni.lokacar.DAL.DAO.MarqueDAO;
import fr.eni.lokacar.DAL.DAO.ModeleDAO;
import fr.eni.lokacar.DAL.DAO.VehiculeDAO;

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

        AgenceDAO agenceDAO = new AgenceDAO(c);
        agenceDAO.insertAgence(a);

    }

    public void insertMarque(Context c){
        this.marque = new Marque();
        marque.setId(new Random().nextInt(3000));
        marque.setNom("Citroen");

        MarqueDAO marqueDAO = new MarqueDAO(c);
        marqueDAO.insertMarque(marque);
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
        try {
            this.a = new GerantDAO(c).connect("test", "test");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
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
        loc.setDateFinPrevu(new Date());
        loc.setDateDebut(new Date());
        loc.setiD(UUID.randomUUID());
        insertVehicule(c);
        loc.setVehicule(vehicule);
        insertClient(c);
        loc.setClient(client);


        lstLoc.add(loc);
        lstLoc.add(loc);
        lstLoc.add(loc);
        lstLoc.add(loc);
        lstLoc.add(loc);


        return lstLoc;

    }

    public void insertV(Context c) throws NoSuchAlgorithmException {
        Vehicule v = new Vehicule();


        Modele m = new Modele();
        Marque ma = new Marque();
        ma.setId(new Random().nextInt(54646));
        ma.setNom("lada");

        DetailsModele dm = new DetailsModele();
        dm.setBoite("Manuel");
        dm.setCarburant("Essence");
        dm.setcNIT("pogjzriojhoi");
        dm.setDesignation("LADA");
        dm.setModeleCommercial("Lada ");
        dm.setGamme("Luxe");
        dm.setCarrosserie("berline");

        m.setcNIT(dm.getcNIT());
        m.setMarque(ma);
        m.setNom("lada");
        m.setDetailModele(dm);


        v.setAgence(new GerantDAO(c).connect("test", "test"));
        v.setEtat("LO");
        v.setImmatriculation("ozrheioroz");
        v.setModele(m);
        v.setKilometrage(56445);
        v.setPrixJournalier(564);

        try {
            MarqueDAO marqueDAO = new MarqueDAO(c);
            marqueDAO.insertMarque(ma);

            ModeleDAO modeleDAO = new ModeleDAO(c);
            modeleDAO.insertModele(m);

            DetailModelDAO detailModelDAO = new DetailModelDAO(c);
            detailModelDAO.insertDetailModel(dm);

            VehiculeDAO vehiculeDAO = new VehiculeDAO(c);
            vehiculeDAO.insertVehicule(v);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void insertLoc(Context c) {

        Location l = new Location();
        Client cli = new Client();
        cli.setiD(UUID.fromString("cfdb25b2-7575-4011-84d7-ae68c26656c5"));
        l.setClient(cli);
        l.setiD(UUID.randomUUID());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            l.setDateDebut(sdf.parse("2018-04-18"));

            l.setDateFinPrevu(sdf.parse("2018-04-25"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Vehicule v = new Vehicule();
        v.setImmatriculation("ozrheioroz");
        l.setVehicule(v);

        l.setStatut("EC");

        LocationDAO lDao = new LocationDAO(c);
        lDao.insertLocation(l);


    }

    public void insertDetailModel(Context c) {
        DetailsModele dm = new DetailsModele();
        dm.setBoite("Manuel");
        dm.setCarburant("Essence");
        dm.setcNIT("pogjzriojhoi");
        dm.setDesignation("LADA");
        dm.setModeleCommercial("Lada ");
        dm.setGamme("Luxe");
        dm.setCarrosserie("berline");

        DetailModelDAO detailModelDAO = new DetailModelDAO(c);
        detailModelDAO.insertDetailModel(dm);
    }





}
