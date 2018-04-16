package fr.eni.lokacar.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocaCarDB extends SQLiteOpenHelper {


    //CONSTANTE DM


    private static final String COL_ID_MM ="ID";
    private static final String COL_CNIT_MM="CNIT";
    private static final String COL_MARQUE_MM="MARQUE";
    private static final String COL_NOM_COMMERCIAL_MM="NOM_COMMERCIAL";
    private static final String COL_DES_MM="DESIGNATION";

    //CONSTANTE MM
    private static final String TABLE_MODEL_MARQUE = "model_marque_DB";
    private static final String TABLE_DETAIL_MODEL = "detail_Model_DB";
    private static final String COL_ID_DM ="ID";
    private static final String COL_ID_MM_DM="ID_MM";
    private static final String COL_CHAMPSV9="CHAMP_V9_DM";
    private static final String COL_BOITE_VITESSE_MM="BOITE_VITESSE_DM";
    private static final String COL_MASSE_MINI="MASSE_MINI";
    private static final String COL_MASSE_MAX="MASSE_MAX";
    private static final String COL_ESSAIHC="ESSAI_HC";
    private static final String COL_PUISS_ADM="PUISS_ADM";
    private static final String COL_TYPE_VARIANTE="TYPE_VARIANTE";
    private static final String COL_EMISSION_CO2="EMISSION_CO2";
    private static final String COL_CAROSS="CAROSSERIE";
    private static final String COL_HYBRIDE="HYBRIDE";
    private static final String COL_GAMME="GAMME";
    private static final String COL_CARBURANT="CARBURANT";
    private static final String COL_ESSAIHCNOX="ESSAI_HCNOX";
    private static final String COL_ESSAINOX="ESSAI_NOX";
    private static final String COL_PUISS_MAX="PUISS_MAX";
    private static final String COL_CONSO_URBAINE="CONSO_URBAINE";
    private static final String COL_CONSO_MIXTE="CONSO_MIXTE";
    private static final String COL_ESSAI_PARTI="ESSAI_PARTI";
    private static final String COL_DATE_MAJ="DATE_MAJ";
    private static final String COL_MODEL_COMMERCIAL="MODEL_COMMERCIAL";
    private static final String COL_ESSAI_CO2="ESSAI_CO2";
    private static final String COL_MODEL_DOSSIER="MODEL_DOSSIER";

    //CONSTANTE CREATION TABLE
    private static final String CREATE_DB_MM = "" +
            "CREATE TABLE " + TABLE_MODEL_MARQUE +
            "( " + COL_ID_MM + " INTEGER PRIMARY KEY ,"
            + COL_CNIT_MM + " TEXT NOT NULL ,"
            + COL_MARQUE_MM + " TEXT NOT NULL ,"
            + COL_NOM_COMMERCIAL_MM + " TEXT NOT NULL ,"
            + COL_DES_MM + " TEXT NOT NULL"
            +")";

    private static final String CREATE_DB_DM =  "CREATE TABLE " + TABLE_DETAIL_MODEL +" "
            +"( " + COL_ID_DM + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COL_ID_MM_DM + " INTEGER NOT NULL,"
            + COL_CHAMPSV9 + " TEXT , "
            + COL_BOITE_VITESSE_MM + " TEXT , "
            + COL_MASSE_MINI + " INTEGER , "
            + COL_MASSE_MAX + " INTEGER , "
            + COL_ESSAIHC + " INTEGER  , "
            + COL_PUISS_ADM + " INTEGER , "
            + COL_TYPE_VARIANTE + " TEXT , "
            + COL_EMISSION_CO2 + " INTEGER  , "
            + COL_CAROSS + " TEXT , "
            + COL_GAMME + " TEXT , "
            + COL_CARBURANT + " TEXT , "
            + COL_ESSAIHCNOX + " REAL , "
            + COL_ESSAINOX + " REAL , "
            + COL_PUISS_MAX + " INTEGER , "
            + COL_CONSO_URBAINE + " TEXT , "
            + COL_CONSO_MIXTE + " REAL , "
            + COL_ESSAI_PARTI + " REAL , "
            + COL_HYBRIDE + " TEXT ,"
            + COL_DATE_MAJ + " TEXT , "
            + COL_MODEL_COMMERCIAL + " TEXT , "
            + COL_ESSAI_CO2 + " REAL , "
            + COL_MODEL_DOSSIER + " TEXT , "
            + ""
            + "FOREIGN KEY( " + COL_ID_MM_DM +") REFERENCES  " +TABLE_MODEL_MARQUE +"( "+ COL_ID_MM + " ))";




    public LocaCarDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_MM);
        db.execSQL(CREATE_DB_DM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


