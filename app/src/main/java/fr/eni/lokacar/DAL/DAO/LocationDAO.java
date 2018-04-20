package fr.eni.lokacar.DAL.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


import fr.eni.lokacar.BO.Client;

import fr.eni.lokacar.BO.Coordonnee;
import fr.eni.lokacar.BO.DetailsModele;
import fr.eni.lokacar.BO.Facture;
import fr.eni.lokacar.BO.Location;
import fr.eni.lokacar.BO.Marque;
import fr.eni.lokacar.BO.Modele;
import fr.eni.lokacar.BO.Vehicule;
import fr.eni.lokacar.DAL.CreationBASE.ConstanteDB;
import fr.eni.lokacar.DAL.CreationBASE.LocaCarDB;
import fr.eni.lokacar.DAL.CreationBASE.VersionDB;
import fr.eni.lokacar.DetailLocationActivity;


public class LocationDAO {


    private static final String NOM_BASE = VersionDB.NOM_BASE;
    private static final int version = VersionDB.VERSION;
    private SQLiteDatabase db;
    private LocaCarDB locaCarDB;
    private final String SELECTALL = " SELECT  " +
            "l." + ConstanteDB.LOC_ID + " ," +
            "l." + ConstanteDB.LOC_DDEBUT +", " +
            "l."+ConstanteDB.LOC_DFIN + ", " +
            "l."+ConstanteDB.LOC_IMMAT_V + ", " +
            "l." + ConstanteDB.LOC_CLI_ID + ", " +
            "l." + ConstanteDB.LOC_STATUT + ", " +
            "c."+ConstanteDB.CLI_NOM +", "+
            "c."+ConstanteDB.CLI_PRENOM +", "+
            "v."+ConstanteDB.V_AGENCE_ID +" as agenceID,"+
            "v."+ConstanteDB.V_CNIT_VE +", "+
            "v."+ConstanteDB.V_KM +", "+
            "v."+ConstanteDB.V_PRIX_JOUR +", "+
            "v." + ConstanteDB.V_STATUT + ", " +
            "v." + ConstanteDB.V_IMMAT +
            "  FROM " + ConstanteDB.LOCATIONS + " l "
            + "INNER JOIN "+ ConstanteDB.CLIENTS +" c  ON c."+ConstanteDB.CLI_ID+"=l."+ConstanteDB.LOC_CLI_ID
            + " INNER JOIN "+ ConstanteDB.VEHICULES +" v  ON v."+ConstanteDB.V_IMMAT+"=l."+ConstanteDB.LOC_IMMAT_V;


    private final String SELECTCURRENT = " SELECT  " +
            "l." + ConstanteDB.LOC_ID + " ," +
            "l." + ConstanteDB.LOC_DDEBUT +", " +
            "l."+ConstanteDB.LOC_DFIN + ", " +
            "l."+ConstanteDB.LOC_IMMAT_V + ", " +
            "l." + ConstanteDB.LOC_CLI_ID + ", " +
            "l." + ConstanteDB.LOC_STATUT + ", " +
            "c."+ConstanteDB.CLI_NOM +", "+
            "c."+ConstanteDB.CLI_PRENOM +", "+
            "v."+ConstanteDB.V_AGENCE_ID +" as agenceID,"+
            "v."+ConstanteDB.V_CNIT_VE +", "+
            "v."+ConstanteDB.V_KM +", "+
            "v."+ConstanteDB.V_PRIX_JOUR +", "+
            "v." + ConstanteDB.V_STATUT + ", " +
            "v." + ConstanteDB.V_IMMAT +
            "  FROM " + ConstanteDB.LOCATIONS + " l "
            + "INNER JOIN "+ ConstanteDB.CLIENTS +" c  ON c."+ConstanteDB.CLI_ID+"=l."+ConstanteDB.LOC_CLI_ID
            + " INNER JOIN "+ ConstanteDB.VEHICULES +" v  ON v."+ConstanteDB.V_IMMAT+"=l."+ConstanteDB.LOC_IMMAT_V
            + " WHERE l." + ConstanteDB.LOC_STATUT + "=? ";


    private final String SELECT_LOC_DETAIL = "SELECT " +
            " c." + ConstanteDB.CLI_ID
            + ", c." + ConstanteDB.CLI_NOM
            + ", c." + ConstanteDB.CLI_PRENOM
            + ", coo." + ConstanteDB.COO_VILLE
            + ", coo." + ConstanteDB.COO_TEL
            + ", coo." + ConstanteDB.COO_MAIL
            + ", coo." + ConstanteDB.COO_ADRESSE
            + ", mo." + ConstanteDB.MO_CNIT
            + ", mo." + ConstanteDB.MO_ID_MARQUE
            + ", mo." + ConstanteDB.MO_PATH_PHOTO
            + ", mo." + ConstanteDB.MO_NOM + " as NOM_MODEL"
            + ", dm." + ConstanteDB.DM_BOITE
            + ", dm." + ConstanteDB.DM_CARBURANT
            + ", dm." + ConstanteDB.DM_CARROSSERIE
            + ", dm." + ConstanteDB.DM_DESIGNATION
            + ", dm." + ConstanteDB.DM_GAMME
            + ", dm." + ConstanteDB.DM_MODEL_COM
            + ", ma." + ConstanteDB.MA_NOM + " as NOM_MARQUE"
            + " FROM " + ConstanteDB.LOCATIONS + " l"
            + " INNER JOIN " + ConstanteDB.CLIENTS + " c ON c." + ConstanteDB.CLI_ID + " = l." + ConstanteDB.LOC_CLI_ID
            + " INNER JOIN " + ConstanteDB.COORDONNEES + " coo  on c." + ConstanteDB.CLI_ID + " = coo." + ConstanteDB.COO_ID
            + " INNER JOIN " + ConstanteDB.VEHICULES + " v  on v." + ConstanteDB.V_IMMAT + " = l." + ConstanteDB.LOC_IMMAT_V
            + " INNER JOIN " + ConstanteDB.MODELES + " mo  on v." + ConstanteDB.MO_CNIT + " = mo." + ConstanteDB.MO_CNIT
            + " INNER JOIN " + ConstanteDB.DETAILS_MODELES + " dm  on dm." + ConstanteDB.DM_CNIT + " = mo." + ConstanteDB.MO_CNIT
            + " INNER JOIN " + ConstanteDB.MARQUES + " ma  on ma." + ConstanteDB.MA_ID + " = mo." + ConstanteDB.MO_ID_MARQUE
            + " WHERE l." + ConstanteDB.LOC_ID + " = ?";


    public void openForWrite(){
        db = locaCarDB.getWritableDatabase();
    }

    public void openForRead(){
        db = locaCarDB.getReadableDatabase();
    }

    public LocationDAO(Context context) {
        locaCarDB = new LocaCarDB(context, NOM_BASE, null, version);
    }

    public void insertLocation(Location location ){

        location.setiD(UUID.randomUUID());
        ContentValues c = getContentValues(location);
        openForWrite();
        db.insert(ConstanteDB.LOCATIONS, null, c);

    }



    public List<Location> selectAll(){

        openForRead();
        Cursor cursor  = db.rawQuery(SELECTALL, new String[]{});
        List<Location> listLocation = new ArrayList<Location>();

        while (cursor.moveToNext()){
            setLocationFromDB(cursor, listLocation);
        }

        return listLocation;

    }


    public List<Location> selectCurrentLocation(){
        String etat = "EC";



        openForRead();
        Cursor cursor = db.rawQuery(SELECTCURRENT, new String[]{etat});
        List<Location> listLocation = new ArrayList<Location>();

        while (cursor.moveToNext()){
            setLocationFromDB(cursor, listLocation);
        }

        return listLocation;

    }

    private void setLocationFromDB(Cursor cursor, List<Location> listLocation) {
        Vehicule vehicule = new Vehicule();
        Client client = new Client();
        Location location = new Location();

        client.setiD(UUID.fromString(cursor.getString(cursor.getColumnIndex(ConstanteDB.CLI_ID))));
        client.setNom(cursor.getString(cursor.getColumnIndex(ConstanteDB.CLI_NOM)));
        client.setPrenom(cursor.getString(cursor.getColumnIndex(ConstanteDB.CLI_PRENOM)));

        vehicule.setEtat(cursor.getString(cursor.getColumnIndex(ConstanteDB.V_STATUT)));
        vehicule.setImmatriculation(cursor.getString(cursor.getColumnIndex(ConstanteDB.V_IMMAT)));
        vehicule.setKilometrage(cursor.getInt(cursor.getColumnIndex(ConstanteDB.V_KM)));
        vehicule.setPrixJournalier(cursor.getInt(cursor.getColumnIndex(ConstanteDB.V_PRIX_JOUR)));

        location.setiD(UUID.fromString(cursor.getString(cursor.getColumnIndex(ConstanteDB.LOC_ID))));
        location.setDateDebut(new Timestamp(Long.valueOf(cursor.getString(cursor.getColumnIndex(ConstanteDB.LOC_DDEBUT)))));
        location.setDateFinPrevu(new Timestamp(Long.valueOf(cursor.getString(cursor.getColumnIndex(ConstanteDB.LOC_DFIN)))));
        location.setStatut(cursor.getString(cursor.getColumnIndex(ConstanteDB.LOC_STATUT)));
        location.setClient(client);
        location.setVehicule(vehicule);
        listLocation.add(location);
    }

    private ContentValues getContentValues(Location location) {
        ContentValues c = new ContentValues();

        c.put(ConstanteDB.LOC_ID,location.getiD().toString());
        c.put(ConstanteDB.LOC_CLI_ID, location.getClient().getiD().toString());
        c.put(ConstanteDB.LOC_DDEBUT , location.getDateDebut().getTime());
        c.put(ConstanteDB.LOC_DFIN, location.getDateFinPrevu().getTime());
        c.put(ConstanteDB.LOC_IMMAT_V, location.getVehicule().getImmatriculation());
        c.put(ConstanteDB.LOC_STATUT, location.getStatut());

        return c;
    }

    public Location getDetail(Location l) {

        openForRead();
        Cursor c = db.rawQuery(SELECT_LOC_DETAIL, new String[]{l.getiD().toString()});
        Modele mo = new Modele();
        Client cli = new Client();
        if (c.moveToNext()) {
            cli.setPrenom(c.getString(c.getColumnIndex(ConstanteDB.CLI_PRENOM)));
            cli.setNom(c.getString(c.getColumnIndex(ConstanteDB.CLI_NOM)));
            cli.setiD(UUID.fromString(c.getString(c.getColumnIndex(ConstanteDB.CLI_ID))));

            Coordonnee coo = new Coordonnee();
            coo.setVille(c.getString(c.getColumnIndex(ConstanteDB.COO_VILLE)));
            coo.setTelephone(c.getString(c.getColumnIndex(ConstanteDB.COO_TEL)));
            coo.setAdresse(c.getString(c.getColumnIndex(ConstanteDB.COO_ADRESSE)));
            coo.setEmail(c.getString(c.getColumnIndex(ConstanteDB.COO_MAIL)));

            cli.setCoordonee(coo);

            Marque ma = new Marque();
            ma.setNom(c.getString(c.getColumnIndex("NOM_MARQUE")));

            DetailsModele dm = new DetailsModele();
            dm.setGamme(c.getString(c.getColumnIndex(ConstanteDB.DM_GAMME)));
            dm.setCarrosserie(c.getString(c.getColumnIndex(ConstanteDB.DM_CARROSSERIE)));
            dm.setCarburant(c.getString(c.getColumnIndex(ConstanteDB.DM_CARBURANT)));
            dm.setModeleCommercial(c.getString(c.getColumnIndex(ConstanteDB.DM_MODEL_COM)));
            dm.setDesignation(c.getString(c.getColumnIndex(ConstanteDB.DM_DESIGNATION)));
            dm.setcNIT(c.getString(c.getColumnIndex(ConstanteDB.MO_CNIT)));


            mo.setNom(c.getString(c.getColumnIndex("NOM_MODEL")));
            mo.setcNIT(c.getString(c.getColumnIndex(ConstanteDB.MO_CNIT)));

            mo.setDetailModele(dm);
            mo.setMarque(ma);


        }
        l.getVehicule().setModele(mo);
        l.setClient(cli);

        return l;


    }

    public boolean terminateFacture(Facture f) {

        boolean isOK = true;
        openForWrite();
        db.beginTransaction();
        try {
            ContentValues cf = getContentValueFacture(f);
            ContentValues cv = getContentValueVehicule(f.getLocation().getVehicule());
            ContentValues cl = getContentValues(f.getLocation());

            db.insert(ConstanteDB.FACTURES, null, cf);
            db.update(ConstanteDB.VEHICULES, cv, ConstanteDB.V_IMMAT + " = '" + f.getLocation().getVehicule().getImmatriculation() + "'", null);
            db.update(ConstanteDB.LOCATIONS, cl, ConstanteDB.LOC_ID + " = '" + f.getLocation().getiD().toString() + "'", null);

            db.setTransactionSuccessful();

        } catch (Exception e) {
            isOK = false;
            e.printStackTrace();
        }
        db.endTransaction();
        return isOK;
    }

    private ContentValues getContentValueFacture(Facture f) {

        ContentValues c = new ContentValues();
        c.put(ConstanteDB.FACT_ID, f.getLocation().getiD().toString());
        c.put(ConstanteDB.FACT_DFIN, f.getDateFinReel().getTime());
        c.put(ConstanteDB.FACT_MONTANT, f.getMontant());
        return c;
    }

    private ContentValues getContentValueVehicule(Vehicule v) {
        ContentValues c = new ContentValues();
//        c.put(ConstanteDB.V_AGENCE_ID, v.getAgence().getiD().toString());
        c.put(ConstanteDB.V_CNIT_VE, v.getModele().getcNIT());
        c.put(ConstanteDB.V_IMMAT, v.getImmatriculation());
        c.put(ConstanteDB.V_STATUT, v.getEtat());
        c.put(ConstanteDB.V_KM, v.getKilometrage());
        c.put(ConstanteDB.V_PRIX_JOUR, v.getPrixJournalier());

        return c;
    }
}
