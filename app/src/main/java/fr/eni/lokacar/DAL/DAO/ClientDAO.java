package fr.eni.lokacar.DAL.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.UUID;

import fr.eni.lokacar.BO.Client;
import fr.eni.lokacar.DAL.CreationBASE.ConstanteDB;
import fr.eni.lokacar.DAL.CreationBASE.LocaCarDB;
import fr.eni.lokacar.DAL.CreationBASE.VersionDB;

public class ClientDAO {


    private static final String NOM_BASE = VersionDB.NOM_BASE;
    private static final int version = VersionDB.VERSION;
    private SQLiteDatabase db;
    private LocaCarDB locaCarDB;

    public void openForWrite(){
        db = locaCarDB.getWritableDatabase();
    }

    public void openForRead(){
        db = locaCarDB.getReadableDatabase();
    }

    public ClientDAO(Context context) {
        locaCarDB = new LocaCarDB(context, NOM_BASE, null, version);
    }


    public void insertClient(Client c){

        openForWrite();
        c.setiD(UUID.randomUUID());

        ContentValues cClient = getContentValueClient(c);
        ContentValues cCoordonnee = getContentValueCoordonnee(c);

        db.beginTransaction();

        try{
            db.insert(ConstanteDB.CLIENTS, null, cClient);
            db.insert(ConstanteDB.COORDONNEES,null, cCoordonnee);
            db.setTransactionSuccessful();

        }catch (Exception e){

        }

        db.endTransaction();
    }

    private ContentValues getContentValueCoordonnee(Client c) {
        ContentValues cv = new ContentValues();
        cv.put(ConstanteDB.COO_ID, c.getiD().toString());
        cv.put(ConstanteDB.COO_ADRESSE, c.getCoordonee().getAdresse());
        cv.put(ConstanteDB.COO_MAIL, c.getCoordonee().getEmail());
        cv.put(ConstanteDB.COO_TEL, c.getCoordonee().getTelephone());
        cv.put(ConstanteDB.COO_VILLE, c.getCoordonee().getVille());

        return cv;

    }


    private ContentValues getContentValueClient(Client c){
        ContentValues cv = new ContentValues();
        cv.put(ConstanteDB.CLI_ID,c.getiD().toString());
        cv.put(ConstanteDB.CLI_NOM, c.getNom());
        cv.put(ConstanteDB.CLI_PRENOM, c.getPrenom());

        return cv;

    }
}
