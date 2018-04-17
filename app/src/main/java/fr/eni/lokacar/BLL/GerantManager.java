package fr.eni.lokacar.BLL;

import android.content.Context;

import java.security.NoSuchAlgorithmException;

import fr.eni.lokacar.BO.Gerant;
import fr.eni.lokacar.DAL.DAO.GerantDAO;

public class GerantManager {

    public Gerant connect(Context c , String login, String password) throws NoSuchAlgorithmException {

        GerantDAO gDao = new GerantDAO(c);
        return gDao.connect(login, password);

    }


}
