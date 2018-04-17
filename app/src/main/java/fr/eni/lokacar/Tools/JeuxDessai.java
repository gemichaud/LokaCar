package fr.eni.lokacar.Tools;

import android.content.Context;

import java.util.UUID;

import fr.eni.lokacar.BO.Agence;
import fr.eni.lokacar.BO.Gerant;
import fr.eni.lokacar.DAL.DAO.GerantDAO;

public class JeuxDessai {

    public void insertGerant(Context c ){

        Gerant g = new Gerant();
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
        Agence a = new Agence();
        a.setiD( UUID.randomUUID());
        a.setAdresse("3 rue de toto");
        a.setVille("Nantes");
        a.setGerant(g);

    }



}
