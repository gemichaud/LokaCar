package fr.eni.lokacar.DAL.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fr.eni.lokacar.BO.Agence;
import fr.eni.lokacar.BO.Gerant;
import fr.eni.lokacar.DAL.CreationBASE.ConstanteDB;
import fr.eni.lokacar.DAL.CreationBASE.LocaCarDB;

public class AgenceDAO {

    private static final String NOM_BASE = "lokacar.db";
    private static final int version = 1;
    private SQLiteDatabase db;
    private LocaCarDB locaCarDB;


    public void openForWrite(){
        db = locaCarDB.getWritableDatabase();
    }

    public void openForRead(){
        db = locaCarDB.getReadableDatabase();
    }

    public AgenceDAO(Context context) {
        locaCarDB = new LocaCarDB(context, NOM_BASE, null, version);
    }




    public void insertAgence( Agence a ){

        ContentValues c = getContentValues(a);
        openForWrite();
        db.insert(ConstanteDB.AGENCES, null, c);

    }

    private ContentValues getContentValues(Agence a) {
        ContentValues c = new ContentValues();
        c.put(ConstanteDB.A_ID , a.getiD().toString());
        c.put(ConstanteDB.A_ADRESSE, a.getAdresse() );
        c.put(ConstanteDB.A_VILLE, a.getVille());
        c.put(ConstanteDB.A_ID_GERANT , a.getGerant().getiD().toString());

        return  c;
    }

}
