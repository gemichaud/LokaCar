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
import fr.eni.lokacar.BO.Location;
import fr.eni.lokacar.BO.Vehicule;
import fr.eni.lokacar.DAL.CreationBASE.ConstanteDB;
import fr.eni.lokacar.DAL.CreationBASE.LocaCarDB;
import fr.eni.lokacar.DAL.CreationBASE.VersionDB;


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
            + " FROM " + ConstanteDB.LOCATIONS + " l"
            + " INNER JOIN " + ConstanteDB.CLIENTS + " c ON c." + ConstanteDB.CLI_ID + " = l." + ConstanteDB.LOC_CLI_ID
            + " INNER JOIN " + ConstanteDB.COORDONNEES + " coo  on c." + ConstanteDB.CLI_ID + " = coo." + ConstanteDB.COO_ID
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

        }

        l.setClient(cli);

        return l;


    }
}
