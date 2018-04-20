package fr.eni.lokacar.DAL.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

import fr.eni.lokacar.BO.Agence;
import fr.eni.lokacar.BO.Client;
import fr.eni.lokacar.BO.Coordonnee;
import fr.eni.lokacar.BO.Gerant;
import fr.eni.lokacar.BO.LocalisationVehicule;
import fr.eni.lokacar.BO.PhotoVehicule;
import fr.eni.lokacar.DAL.CreationBASE.ConstanteDB;
import fr.eni.lokacar.DAL.CreationBASE.LocaCarDB;
import fr.eni.lokacar.DAL.CreationBASE.VersionDB;
import fr.eni.lokacar.Tools.PasswordTools;

public class PhotoVehiculeDAO {

    private static final String NOM_BASE = VersionDB.NOM_BASE;
    private static final int version = VersionDB.VERSION;
    private SQLiteDatabase db;
    private LocaCarDB locaCarDB;

    private String SELECT_PHOTO_VEHICULE_BY_IMMAT_LOCALISATION_DERNIERE =
                    "SELECT "
                    + "p." + ConstanteDB.PhV_DESCRIPTION
                    + ", p." + ConstanteDB.PhV_IMMAT_V
                    + ", p." + ConstanteDB.PhV_DERNIERE
                    + ", p." + ConstanteDB.PhV_ID
                    + ", p." + ConstanteDB.PhV_Localisation
                    + ", p." + ConstanteDB.PhV_PATH
                    + ", p."+ConstanteDB.PhV_ID_LOC
                    + " FROM " + ConstanteDB.PHOTO_VOITURES + " p "
                    + " WHERE p." + ConstanteDB.PhV_IMMAT_V + "=? "
                    + " AND p." + ConstanteDB.PhV_Localisation + "=?"
                    + " AND p."+ ConstanteDB.PhV_DERNIERE + "=?";




    public void openForWrite(){
        db = locaCarDB.getWritableDatabase();
    }

    public void openForRead(){
        db = locaCarDB.getReadableDatabase();
    }

    public PhotoVehiculeDAO(Context context) {
        locaCarDB = new LocaCarDB(context, NOM_BASE, null, version);
    }

    public void insertPhotoVehicule( PhotoVehicule photoVehicule ){

        ContentValues c = getContentValues(photoVehicule);
        openForWrite();
        db.insert(ConstanteDB.PHOTO_VOITURES, null, c);

    }



    public PhotoVehicule selectPhotoVehiculeByImmatLocalisationDerniere(String immat, String localisation) {

        openForRead();
        Cursor c = db.rawQuery(SELECT_PHOTO_VEHICULE_BY_IMMAT_LOCALISATION_DERNIERE, new String[]{immat,localisation,"1"});
        PhotoVehicule photoVehicule = new PhotoVehicule();
        if (c.moveToNext()) {

            photoVehicule.setLocalisationVehiculeEnum(LocalisationVehicule.valueOf(c.getString(c.getColumnIndex(ConstanteDB.PhV_Localisation))));

            if (c.getInt(c.getColumnIndex(ConstanteDB.PhV_DERNIERE))==0)
                photoVehicule.setDerniere(false);
            else
                photoVehicule.setDerniere(true);

            photoVehicule.setDescription(c.getString(c.getColumnIndex(ConstanteDB.PhV_DESCRIPTION)));
            photoVehicule.setId(c.getInt(c.getColumnIndex(ConstanteDB.PhV_ID)));
            photoVehicule.setPath(c.getString(c.getColumnIndex(ConstanteDB.PhV_PATH)));

        }

        return photoVehicule;


    }


    public boolean update(PhotoVehicule photoVehicule) {

        openForWrite();

        db.update(ConstanteDB.PHOTO_VOITURES, getContentValues(photoVehicule), ConstanteDB.PhV_PATH + " = ?", new String[] {photoVehicule.getPath()});

        return true;


    }

    private ContentValues getContentValues(PhotoVehicule photoVehicule) {
        ContentValues c = new ContentValues();

        if (photoVehicule.getId() == 0){

            Random rand = new Random();
            photoVehicule.setId(rand.nextInt(100000)+1);
        }

        c.put(ConstanteDB.PhV_ID_LOC, photoVehicule.getLocation().getiD().toString());
        c.put(ConstanteDB.PhV_IMMAT_V , photoVehicule.getLocation().getVehicule().getImmatriculation());
        c.put(ConstanteDB.PhV_Localisation, photoVehicule.getLocalisationVehiculeEnum().toString());
        c.put(ConstanteDB.PhV_ID, photoVehicule.getId());
        c.put(ConstanteDB.PhV_PATH,photoVehicule.getPath());
        c.put(ConstanteDB.PhV_DERNIERE, photoVehicule.isDerniere());
        c.put(ConstanteDB.PhV_DESCRIPTION,photoVehicule.getDescription());



        return c;
    }
}
