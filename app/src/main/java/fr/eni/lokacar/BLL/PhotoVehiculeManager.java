package fr.eni.lokacar.BLL;

import android.content.Context;

import fr.eni.lokacar.BO.Location;
import fr.eni.lokacar.BO.PhotoVehicule;
import fr.eni.lokacar.DAL.DAO.PhotoVehiculeDAO;

public class PhotoVehiculeManager {


    public void insert (Context c, PhotoVehicule photoVehicule){

        PhotoVehiculeDAO photoVehiculeDAO = new PhotoVehiculeDAO(c);

        photoVehiculeDAO.insertPhotoVehicule(photoVehicule);


    }

    public PhotoVehicule selectByImmatLocDerniere(Context c, PhotoVehicule photoVehicule){

        PhotoVehiculeDAO photoVehiculeDAO = new PhotoVehiculeDAO(c);

        return photoVehiculeDAO.selectPhotoVehiculeByImmatLocalisationDerniere(photoVehicule.getLocation().getVehicule().getImmatriculation(), photoVehicule.getLocalisationVehiculeEnum().toString());

    }

    public void update (Context c, PhotoVehicule photoVehicule){

            photoVehicule.setDerniere(false);
            PhotoVehiculeDAO photoVehiculeDAO = new PhotoVehiculeDAO(c);
            photoVehiculeDAO.update(photoVehicule);

    }

    public void updateOldAndInsertNew(Context c, PhotoVehicule photoVehicule){
        update(c,photoVehicule);
        insert(c,photoVehicule);
    }
}
