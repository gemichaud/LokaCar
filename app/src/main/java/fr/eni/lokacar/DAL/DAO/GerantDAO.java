package fr.eni.lokacar.DAL.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import fr.eni.lokacar.BO.Gerant;
import fr.eni.lokacar.DAL.CreationBASE.ConstanteDB;
import fr.eni.lokacar.DAL.CreationBASE.LocaCarDB;
import fr.eni.lokacar.Tools.PasswordTools;

public class GerantDAO {

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

    public GerantDAO(Context context) {
        locaCarDB = new LocaCarDB(context, NOM_BASE, null, version);
    }

    public void insertGerant( Gerant g ){

        ContentValues c = getContentValues(g);
        openForWrite();
        db.insert(ConstanteDB.GERANTS, null, c);

    }


    public Gerant connect(String login, String password) throws NoSuchAlgorithmException {

        openForRead();
        Cursor c  = db.query(ConstanteDB.GERANTS ,new String[]{
                        ConstanteDB.G_ID,
                        ConstanteDB.G_LOGIN,
                        ConstanteDB.G_NOM,
                        ConstanteDB.G_PRENOM,
                        ConstanteDB.G_PASSWORD},
                ConstanteDB.G_LOGIN+ " = " + "'"+login+"'" ,
                null,
                null,
                null,
                null);

        Gerant g = new Gerant();
        if (c.moveToNext()){
            if(PasswordTools.checkLogin(password,c.getString(c.getColumnIndex(ConstanteDB.G_PASSWORD))))
            {


                g.setPrenom(c.getString(c.getColumnIndex(ConstanteDB.G_PRENOM)));
                g.setNom(c.getString(c.getColumnIndex(ConstanteDB.G_NOM)));
                g.setiD(UUID.fromString(c.getString(c.getColumnIndex(ConstanteDB.G_ID))));
                g.setLogin(c.getString(c.getColumnIndex(ConstanteDB.G_LOGIN)));
            }
        }

        if( g.getLogin()== null){
            System.out.println("Pas OK");
            return null;
        }
        else {
            System.out.println("OK");
            return g;
        }
    }




    private ContentValues getContentValues(Gerant g) {
        ContentValues c = new ContentValues();


        c.put(ConstanteDB.G_ID, UUID.randomUUID().toString());
        c.put(ConstanteDB.G_LOGIN , g.getLogin());
        c.put(ConstanteDB.G_NOM, g.getNom());
        c.put(ConstanteDB.G_PRENOM, g.getPrenom());
        try {
            c.put(ConstanteDB.G_PASSWORD, PasswordTools.hashPass(g.getMotDePasse()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return c;
    }
}
