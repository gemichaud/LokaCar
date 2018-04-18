package fr.eni.lokacar.DAL.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fr.eni.lokacar.BO.DetailsModele;
import fr.eni.lokacar.DAL.CreationBASE.ConstanteDB;
import fr.eni.lokacar.DAL.CreationBASE.LocaCarDB;
import fr.eni.lokacar.DAL.CreationBASE.VersionDB;

public class DetailModelDAO {

    private static final String NOM_BASE = VersionDB.NOM_BASE;
    private static final int version = VersionDB.VERSION;
    private SQLiteDatabase db;
    private LocaCarDB locaCarDB;
    private Context context;


    public void openForWrite() {
        db = locaCarDB.getWritableDatabase();
    }

    public void openForRead() {
        db = locaCarDB.getReadableDatabase();
    }

    public DetailModelDAO(Context context) {
        locaCarDB = new LocaCarDB(context, NOM_BASE, null, version);
        this.context = context;
    }


    public void insertDetailModel(DetailsModele dm) {
        openForWrite();

        ContentValues c = getContentValue(dm);
        db.insert(ConstanteDB.DETAILS_MODELES, null, c);

    }

    private ContentValues getContentValue(DetailsModele dm) {

        ContentValues cv = new ContentValues();
        cv.put(ConstanteDB.DM_BOITE, dm.getBoite());
        cv.put(ConstanteDB.DM_CARBURANT, dm.getCarburant());
        cv.put(ConstanteDB.DM_CARROSSERIE, dm.getCarrosserie());
        cv.put(ConstanteDB.DM_CNIT, dm.getcNIT());
        cv.put(ConstanteDB.DM_DESIGNATION, dm.getDesignation());
        cv.put(ConstanteDB.DM_GAMME, dm.getGamme());
        cv.put(ConstanteDB.DM_MODEL_COM, dm.getModeleCommercial());

        return cv;
    }
}
