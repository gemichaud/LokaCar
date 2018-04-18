package fr.eni.lokacar.DAL.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.session.PlaybackState;
import android.support.annotation.NonNull;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.eni.lokacar.BO.Client;
import fr.eni.lokacar.BO.Coordonnee;
import fr.eni.lokacar.DAL.CreationBASE.ConstanteDB;
import fr.eni.lokacar.DAL.CreationBASE.LocaCarDB;
import fr.eni.lokacar.DAL.CreationBASE.VersionDB;

public class ClientDAO {


    private static final String NOM_BASE = VersionDB.NOM_BASE;
    private static final int version = VersionDB.VERSION;
    private SQLiteDatabase db;
    private LocaCarDB locaCarDB;
    private String SELECT_CLIENT_BY_ID =
            "SELECT "
                    + "c." + ConstanteDB.CLI_ID
                    + ", c." + ConstanteDB.CLI_PRENOM
                    + ", c." + ConstanteDB.CLI_NOM
                    + ", co." + ConstanteDB.COO_ADRESSE
                    + ", co." + ConstanteDB.COO_MAIL
                    + ", co." + ConstanteDB.COO_TEL
                    + ", co." + ConstanteDB.COO_VILLE
                    + " FROM " + ConstanteDB.CLIENTS + " c "
                    + " INNER JOIN " + ConstanteDB.COORDONNEES + " co  ON c." + ConstanteDB.CLI_ID + "= co." + ConstanteDB.COO_ID
                    + " WHERE C." + ConstanteDB.CLI_ID + "=?";



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


    public List<Client> selectAll() {

        openForRead();
        Cursor c = db.query(ConstanteDB.CLIENTS, new String[]{
                        ConstanteDB.CLI_ID,
                        ConstanteDB.CLI_NOM,
                        ConstanteDB.CLI_PRENOM,
                },
                null,
                null,
                null,
                null,
                ConstanteDB.CLI_NOM);
        List<Client> clients = getClients(c);

        return clients;
    }

    @NonNull
    private List<Client> getClients(Cursor c) {
        List<Client> clients = new ArrayList<>();
        while (c.moveToNext()) {
            Client cli = new Client();
            cli.setiD(UUID.fromString(c.getString(c.getColumnIndex(ConstanteDB.CLI_ID))));
            cli.setPrenom(c.getString(c.getColumnIndex(ConstanteDB.CLI_PRENOM)));
            cli.setNom(c.getString(c.getColumnIndex(ConstanteDB.CLI_NOM)));
            clients.add(cli);

        }
        return clients;
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

    public Client selectClientById(UUID id) {

        openForRead();
        Cursor c = db.rawQuery(SELECT_CLIENT_BY_ID, new String[]{id.toString()});
        Client cli = new Client();
        if (c.moveToNext()) {
            cli.setPrenom(c.getString(c.getColumnIndex(ConstanteDB.CLI_PRENOM)));
            cli.setNom(c.getString(c.getColumnIndex(ConstanteDB.CLI_NOM)));
            cli.setiD(id);

            Coordonnee coo = new Coordonnee();
            coo.setVille(c.getString(c.getColumnIndex(ConstanteDB.COO_VILLE)));
            coo.setTelephone(c.getString(c.getColumnIndex(ConstanteDB.COO_TEL)));
            coo.setAdresse(c.getString(c.getColumnIndex(ConstanteDB.COO_ADRESSE)));
            coo.setEmail(c.getString(c.getColumnIndex(ConstanteDB.COO_MAIL)));

            cli.setCoordonee(coo);

        }

        return cli;


    }

    public boolean update(Client cliUpdate) {

        openForWrite();
        ContentValues cv = getContentValueClient(cliUpdate);
        db.beginTransaction();
        boolean isOK = true;

        try {
            cv.remove(ConstanteDB.COO_ID);
            db.update(ConstanteDB.CLIENTS, cv, ConstanteDB.CLI_ID + " = '" + cliUpdate.getiD().toString() + "'", null);

            ContentValues cvcoo = getContentValueCoordonnee(cliUpdate);
            cvcoo.remove(ConstanteDB.COO_ID);
            db.update(ConstanteDB.COORDONNEES, cvcoo, ConstanteDB.COO_ID + " = '" + cliUpdate.getiD().toString() + "'", null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            isOK = false;
        } finally {

            db.endTransaction();
            return isOK;
        }


    }

    public List<Client> searchByName(String name) {
        openForRead();
        Cursor c = db.query(true, ConstanteDB.CLIENTS, new String[]{
                        ConstanteDB.CLI_ID,
                        ConstanteDB.CLI_NOM,
                        ConstanteDB.CLI_PRENOM}, ConstanteDB.CLI_NOM + " LIKE ?",
                new String[]{"%" + name + "%"}, null, null, ConstanteDB.CLI_NOM,
                null);

        return getClients(c);
    }
}
