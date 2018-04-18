package fr.eni.lokacar.DAL.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import fr.eni.lokacar.BO.Agence;
import fr.eni.lokacar.BO.Marque;
import fr.eni.lokacar.DAL.CreationBASE.ConstanteDB;
import fr.eni.lokacar.DAL.CreationBASE.LocaCarDB;

public class MarqueDAO {

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

    public MarqueDAO(Context context) {
        locaCarDB = new LocaCarDB(context, NOM_BASE, null, version);
    }



    public List<Marque> selectAll(){

        openForRead();
        List<Marque> lst = new ArrayList<Marque>();




        Cursor c = db.query(ConstanteDB.MARQUES,
                getColNames(),
                null,
                null,
                null,
                null,
                null);


        while (c.moveToNext()) {
           Marque marque = this.getMarque(c);
           lst.add(marque);
        };

        c.close();

        return lst;

    }





    public Marque findMarqueById(int id){
        openForRead();

        Marque marque = null;
        //Where
        String whereClause = ConstanteDB.MA_ID + " = ? ";

        //Where args
        String[] whereArgs = new String[] {
                String.valueOf(id)

        };

        Cursor c = db.query(ConstanteDB.MARQUES,
                getColNames(),
                whereClause,
                whereArgs,
                null,
                null,
                null,
                null
                );

        if (c.moveToNext()){
            marque = this.getMarque(c);

        }
        return marque;
    }

    @NonNull
    private String[] getColNames() {
        return new String[] {
                    ConstanteDB.MA_ID,
                    ConstanteDB.MA_NOM
            };
    }


    public void insertMarque( Marque marque ){

        ContentValues c = getContentValues(marque);
        openForWrite();
        db.insert(ConstanteDB.MARQUES, null, c);

    }

    private ContentValues getContentValues(Marque marque) {
        ContentValues c = new ContentValues();
        c.put(ConstanteDB.MA_ID , marque.getId());
        c.put(ConstanteDB.MA_NOM, marque.getNom());

        return  c;
    }


    private Marque getMarque(Cursor c){
        Marque marque = new Marque();

        marque.setId(c.getInt(c.getColumnIndex(ConstanteDB.MA_ID)));
        marque.setNom(c.getString(c.getColumnIndex(ConstanteDB.MA_NOM)));
        return marque;
    }
}
