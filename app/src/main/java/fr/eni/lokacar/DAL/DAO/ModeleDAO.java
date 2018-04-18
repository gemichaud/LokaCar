package fr.eni.lokacar.DAL.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import fr.eni.lokacar.BO.DetailsModele;
import fr.eni.lokacar.BO.Marque;
import fr.eni.lokacar.BO.Modele;
import fr.eni.lokacar.DAL.CreationBASE.ConstanteDB;
import fr.eni.lokacar.DAL.CreationBASE.LocaCarDB;
import fr.eni.lokacar.DAL.CreationBASE.VersionDB;

public class ModeleDAO {

    private static final String NOM_BASE = VersionDB.NOM_BASE;
    private static final int version = VersionDB.VERSION;
    private SQLiteDatabase db;
    private LocaCarDB locaCarDB;
    private Context context;


    public void openForWrite(){
        db = locaCarDB.getWritableDatabase();
    }

    public void openForRead(){
        db = locaCarDB.getReadableDatabase();
    }

    public ModeleDAO(Context context) {
        locaCarDB = new LocaCarDB(context, NOM_BASE, null, version);
        this.context = context;
    }



    public List<Modele> selectAll(){

        openForRead();
        List<Modele> lst = new ArrayList<Modele>();

        Cursor c = db.query(ConstanteDB.MODELES,
                getColNames(),
                null,
                null,
                null,
                null,
                null);


        while (c.moveToNext()) {
            Modele modele = this.getModele(c);
            lst.add(modele);
        };

        c.close();

        return lst;

    }

    @NonNull
    private String[] getColNames() {
        return new String[] {
                ConstanteDB.MO_CNIT,
                ConstanteDB.MO_ID_MARQUE,
                ConstanteDB.MO_NOM
        };
    }


    public void insertModele( Modele modele ){

        ContentValues c = getContentValues(modele);
        openForWrite();
        db.insert(ConstanteDB.MODELES, null, c);

    }

    private ContentValues getContentValues(Modele modele) {
        ContentValues c = new ContentValues();
        c.put(ConstanteDB.MO_CNIT , modele.getcNIT());
        c.put(ConstanteDB.MO_ID_MARQUE, modele.getMarque().getId());
        c.put(ConstanteDB.MO_NOM, modele.getNom());

        return  c;
    }




    public Modele findModelById(String cNIT){
        openForRead();

        Modele modele = null;
        //Where
        String whereClause = ConstanteDB.MO_CNIT + " = ? ";

        //Where args
        String[] whereArgs = new String[] {
                cNIT

        };

        Cursor c = db.query(ConstanteDB.MODELES,
                getColNames(),
                whereClause,
                whereArgs,
                null,
                null,
                null,
                null
        );

        if (c.moveToNext()){
            modele = this.getModele(c);

        }
        return modele;
    }



    private Modele getModele(Cursor c){
        Modele modele = new Modele();

        modele.setNom(c.getString(c.getColumnIndex(ConstanteDB.MO_NOM)));
        modele.setcNIT(c.getString(c.getColumnIndex(ConstanteDB.MO_CNIT)));

        MarqueDAO marqueDao = new MarqueDAO(context);
        Marque marque = marqueDao.findMarqueById(c.getInt(c.getColumnIndex(ConstanteDB.MO_ID_MARQUE)));

        modele.setMarque(marque);
        modele.setDetailModele(getDetailsModele(modele.getcNIT()));

        return modele;
    }


    private DetailsModele getDetailsModele(String cNIT){
        openForRead();


        DetailsModele detailsModele = null;
        //Where
        String whereClause = ConstanteDB.DM_CNIT + " = ? ";

        //Where args
        String[] whereArgs = new String[] {
                cNIT

        };

        Cursor c = db.query(ConstanteDB.DETAILS_MODELES,
                getColNamesDM(),
                whereClause,
                whereArgs,
                null,
                null,
                null,
                null
        );

        if (c.moveToNext()){
            detailsModele = this.getDetailModele(c);

        }
        return detailsModele;
    }

    private String[] getColNamesDM() {

        return new String[] {
                ConstanteDB.DM_BOITE,
                ConstanteDB.DM_CARBURANT,
                ConstanteDB.DM_CARROSSERIE,
                ConstanteDB.DM_DESIGNATION,
                ConstanteDB.DM_CNIT,
                ConstanteDB.DM_GAMME,
                ConstanteDB.DM_MODEL_COM
        };

    }


    private DetailsModele getDetailModele(Cursor c){
        DetailsModele detailsModele = new DetailsModele();

        detailsModele.setModeleCommercial(c.getString(c.getColumnIndex(ConstanteDB.DM_MODEL_COM)));
        detailsModele.setGamme(c.getString(c.getColumnIndex(ConstanteDB.DM_GAMME)));
        detailsModele.setDesignation(c.getString(c.getColumnIndex(ConstanteDB.DM_DESIGNATION)));
        detailsModele.setcNIT(c.getString(c.getColumnIndex(ConstanteDB.DM_CNIT)));
        detailsModele.setCarrosserie(c.getString(c.getColumnIndex(ConstanteDB.DM_CARROSSERIE)));
        detailsModele.setCarburant(c.getString(c.getColumnIndex(ConstanteDB.DM_CARBURANT)));
        detailsModele.setBoite(c.getString(c.getColumnIndex(ConstanteDB.DM_BOITE)));


        return detailsModele;
    }
}
