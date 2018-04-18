package fr.eni.lokacar.DAL.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.eni.lokacar.BO.Client;
import fr.eni.lokacar.BO.Coordonnee;
import fr.eni.lokacar.BO.Location;
import fr.eni.lokacar.BO.Vehicule;
import fr.eni.lokacar.DAL.CreationBASE.ConstanteDB;
import fr.eni.lokacar.DAL.CreationBASE.LocaCarDB;
import fr.eni.lokacar.DAL.CreationBASE.VersionDB;

public class ClientDAO {


    private static final String NOM_BASE = VersionDB.NOM_BASE;
    private static final int version = VersionDB.VERSION;
    private SQLiteDatabase db;
    private LocaCarDB locaCarDB;


    private final String SELECTALL = " SELECT  " +
            "c." + ConstanteDB.CLI_ID + " ," +
            "c." + ConstanteDB.CLI_PRENOM +", " +
            "c."+ConstanteDB.CLI_NOM + ", " +
            "co."+ConstanteDB.COO_ADRESSE + ", " +
            "co." + ConstanteDB.COO_MAIL + ", " +
            "co." + ConstanteDB.COO_TEL + ", " +
            "co."+ConstanteDB.COO_VILLE +
            "  FROM " + ConstanteDB.CLIENTS + " c "
            + "INNER JOIN "+ ConstanteDB.COORDONNEES +" co  ON c."+ConstanteDB.CLI_ID+"=co."+ConstanteDB.COO_ID;


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

    public List<Client> selectAll(){

        openForRead();
        Cursor cursor  = db.rawQuery(SELECTALL, new String[]{});
        List<Client> listClient = new ArrayList<Client>();

        while (cursor.moveToNext()){
            setClientFromDB(cursor, listClient);
        }

        return listClient;

    }

    private void setClientFromDB(Cursor cursor, List<Client> listClient) {

        Client client = new Client();
        Coordonnee coordonnee = new Coordonnee();

        client.setiD(UUID.fromString(cursor.getString(cursor.getColumnIndex(ConstanteDB.CLI_ID))));
        client.setNom(cursor.getString(cursor.getColumnIndex(ConstanteDB.CLI_NOM)));
        client.setPrenom(cursor.getString(cursor.getColumnIndex(ConstanteDB.CLI_PRENOM)));

        coordonnee.setAdresse(cursor.getString(cursor.getColumnIndex(ConstanteDB.COO_ADRESSE)));
        coordonnee.setVille(cursor.getString(cursor.getColumnIndex(ConstanteDB.COO_VILLE)));
        coordonnee.setTelephone(cursor.getString(cursor.getColumnIndex(ConstanteDB.COO_TEL)));
        coordonnee.setEmail(cursor.getString(cursor.getColumnIndex(ConstanteDB.COO_MAIL)));
        client.setCoordonee(coordonnee);

        listClient.add(client);
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
