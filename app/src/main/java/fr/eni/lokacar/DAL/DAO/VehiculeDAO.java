package fr.eni.lokacar.DAL.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.eni.lokacar.BO.Agence;
import fr.eni.lokacar.BO.Gerant;
import fr.eni.lokacar.BO.Vehicule;
import fr.eni.lokacar.DAL.CreationBASE.ConstanteDB;
import fr.eni.lokacar.DAL.CreationBASE.LocaCarDB;
import fr.eni.lokacar.Tools.PasswordTools;

public class VehiculeDAO {

    private static final String NOM_BASE = "lokacar.db";
    private static final int version = 1;
    private SQLiteDatabase db;
    private LocaCarDB locaCarDB;
    private final String SELECTALL = " SELECT  " +
            "v." + ConstanteDB.V_IMMAT + " ," +
            "v." + ConstanteDB.V_KM +", " +
            "v."+ConstanteDB.V_CNIT_VE + ", " +
            "v."+ConstanteDB.V_PRIX_JOUR + ", " +
            "v." + ConstanteDB.V_STATUT + ", " +
            "v."+ConstanteDB.V_AGENCE_ID +" as agenceID "+
            "  FROM " + ConstanteDB.VEHICULES + " g ";

    private Context context;


    public void openForWrite(){
        db = locaCarDB.getWritableDatabase();
    }

    public void openForRead(){
        db = locaCarDB.getReadableDatabase();
    }

    public VehiculeDAO(Context context) {
        locaCarDB = new LocaCarDB(context, NOM_BASE, null, version);
        this.context = context;
    }

    public void insertVehicule( Vehicule v ){

        ContentValues c = getContentValues(v);
        openForWrite();
        db.insert(ConstanteDB.VEHICULES, null, c);

    }


    public List<Vehicule> selectAll(){
        openForRead();
        Cursor c  = db.rawQuery(SELECTALL, new String[]{});
        List<Vehicule> listVehicule = new ArrayList<>();

        ModeleDAO modeleDAO = new ModeleDAO(context);

        while (c.moveToNext()){
            Vehicule vehicule = new Vehicule();

            vehicule.setImmatriculation(c.getString(c.getColumnIndex(ConstanteDB.V_IMMAT)));
            vehicule.setKilometrage(c.getInt(c.getColumnIndex(ConstanteDB.V_KM)));
            vehicule.setEtat(c.getString(c.getColumnIndex(ConstanteDB.V_STATUT)));
            vehicule.setPrixJournalier(c.getInt(c.getColumnIndex(ConstanteDB.V_PRIX_JOUR)));
            vehicule.setModele(modeleDAO.findModelById(c.getString(c.getColumnIndex(ConstanteDB.V_CNIT_VE))));

            listVehicule.add(vehicule);
        }
        c.close();
        return listVehicule;
    }



    private ContentValues getContentValues(Vehicule v) {
        ContentValues c = new ContentValues();

        c.put(ConstanteDB.V_IMMAT, v.getImmatriculation());
        c.put(ConstanteDB.V_AGENCE_ID , v.getAgence().getiD().toString());
        c.put(ConstanteDB.V_CNIT_VE, v.getModele().getcNIT());
        c.put(ConstanteDB.V_KM,v.getKilometrage());
        c.put(ConstanteDB.V_PRIX_JOUR,v.getPrixJournalier());
        c.put(ConstanteDB.V_STATUT,v.getEtat());

        return c;
    }
}
