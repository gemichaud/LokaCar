package fr.eni.lokacar.DAL.CreationBASE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocaCarDB extends SQLiteOpenHelper {





    //CONSTANTE CREATION TABLE
    private static final String CREATE_TA_MA =

            "CREATE TABLE " + ConstanteDB.MARQUES + " " +
            "( " + ConstanteDB.MA_ID + " TEXT PRIMARY KEY , "
            + ConstanteDB.MA_NOM + " TEXT NOT NULL )";

    private static final String CREATE_TA_DM =
            "CREATE TABLE " + ConstanteDB.DETAILS_MODELES
            + "( " + ConstanteDB.DM_CNIT + " TEXT PRIMARY KEY , "
            + ConstanteDB.DM_MODEL_COM + " TEXT NOT NULL, "
            + ConstanteDB.DM_DESIGNATION + " TEXT NOT NULL ,"
            + ConstanteDB.DM_BOITE + " TEXT NOT NULL,"
            + ConstanteDB.DM_GAMME + " TEXT NOT NULL,"
            + ConstanteDB.DM_CARBURANT + " TEXT NOT NULL,"
            + ConstanteDB.DM_CARROSSERIE + " TEXT NOT NULL)";

    private static final String CREATE_TA_MO =

            "CREATE TABLE " + ConstanteDB.MODELES
            +" ( " + ConstanteDB.MO_CNIT + " TEXT PRIMARY KEY ,"
            + ConstanteDB.MO_NOM + " TEXT NOT NULL ,"
            + ConstanteDB.MO_ID_MARQUE + " TEXT NOT NULL , "
            + ConstanteDB.MO_PATH_PHOTO + " TEXT  NOT NULL,"
            + " FOREIGN KEY( " + ConstanteDB.MO_CNIT  +") REFERENCES  " +ConstanteDB.DETAILS_MODELES +"( "+  ConstanteDB.DM_CNIT + " ),"
            + " FOREIGN KEY( " + ConstanteDB.MO_ID_MARQUE  +") REFERENCES  " + ConstanteDB.MARQUES +"( "+   ConstanteDB.MA_ID  + " ))";

    private static final String CREATE_TA_GE =

            "CREATE TABLE " + ConstanteDB.GERANTS
            + "( " + ConstanteDB.G_ID + " TEXT PRIMARY KEY ,"
            + ConstanteDB.G_NOM + " TEXT NOT NULL,"
            + ConstanteDB.G_PRENOM + " TEXT NOT NULL,"
            + ConstanteDB.G_LOGIN +" TEXT UNIQUE NOT NULL,"
            + ConstanteDB.G_PASSWORD +" TEXT NOT NULL )";

    private static final String CREATE_TA_AG =

            "CREATE TABLE " + ConstanteDB.AGENCES
            +" (" + ConstanteDB.A_ID + " TEXT PRIMARY KEY ,"
            + ConstanteDB.A_ID_GERANT + " TEXT ,"
            + ConstanteDB.A_ADRESSE + " TEXT ,"
            + ConstanteDB.A_VILLE + " TEXT , "
            + ""
            + "FOREIGN KEY( " + ConstanteDB.A_ID_GERANT  +") REFERENCES  " + ConstanteDB.GERANTS  +"( "+  ConstanteDB.G_ID   + " ))";

    private static final String CREATE_TA_VE =

            "CREATE TABLE " + ConstanteDB.VEHICULES
            +" (" + ConstanteDB.V_IMMAT + " TEXT PRIMARY KEY ,"
            + ConstanteDB.V_KM + " TEXT ,"
            + ConstanteDB.V_CNIT_VE + " TEXT ,"
            + ConstanteDB.V_PRIX_JOUR + " REAL , "
            +  ConstanteDB.V_STATUT + " TEXT ,"
            + ConstanteDB.V_AGENCE_ID + " TEXT ,"
            + ""
            + " FOREIGN KEY( " + ConstanteDB.V_CNIT_VE  +") REFERENCES  " + ConstanteDB.MODELES  +"( "+  ConstanteDB.MO_CNIT   + " ),"
            + " FOREIGN KEY( " + ConstanteDB.V_AGENCE_ID  +") REFERENCES  " + ConstanteDB.AGENCES  +"( "+  ConstanteDB.A_ID   + " ))";



    private static final String CREATE_TA_LO="" +
            "CREATE TABLE " + ConstanteDB.LOCATIONS
            + " ( " + ConstanteDB.LOC_ID + " TEXT PRIMARY KEY ,"
            + ConstanteDB.LOC_DDEBUT + " INTEGER NOT NULL ,"
            + ConstanteDB.LOC_DFIN + " INTEGER NOT NULL ,"
            + ConstanteDB.LOC_STATUT + " TEXT NOT NULL ,"
            + ConstanteDB.LOC_IMMAT_V + " TEXT NOT NULL ,"
            + ConstanteDB.LOC_CLI_ID +" TEXT NOT NULL ,"
            +""
            + " FOREIGN KEY( " + ConstanteDB.LOC_ID  +") REFERENCES  " + ConstanteDB.VEHICULES  +"( "+  ConstanteDB.V_IMMAT   + " ),"
            + " FOREIGN KEY( " + ConstanteDB.LOC_CLI_ID  +") REFERENCES  " + ConstanteDB.CLIENTS  +"( "+  ConstanteDB.CLI_ID   + " ))";


    private static final String CREATE_TA_PhV="" +
            "CREATE TABLE " + ConstanteDB.PHOTO_VOITURES +
            " ( " + ConstanteDB.PhV_ID + " TEXT PRIMARY KEY ,"
            + ConstanteDB.PhV_PATH + " TEXT NOT NULL ,"
            + ConstanteDB.PhV_Localisation + " TEXT NOT NULL , "
            + ConstanteDB.PhV_DERNIERE + " INTEGER NOT NULL , "
            + ConstanteDB.PhV_ID_LOC + " TEXT NOT NULL , "
            + ConstanteDB.PhV_IMMAT_V + " TEXT , "
            + "FOREIGN KEY( " + ConstanteDB.PhV_ID_LOC  +") REFERENCES  " + ConstanteDB.LOCATIONS  +"( "+  ConstanteDB.LOC_ID   + " ),"
            + " FOREIGN KEY( " + ConstanteDB.PhV_IMMAT_V  +") REFERENCES  " + ConstanteDB.VEHICULES  +"( "+  ConstanteDB.V_IMMAT   + " ))";

    private static final String CREATE_TA_CLI=
            " CREATE TABLE " + ConstanteDB.CLIENTS
            +" ( " + ConstanteDB.CLI_ID + " TEXT PRIMARY KEY ,"
            + ConstanteDB.CLI_NOM + " TEXT NOT NULL ,"
            + ConstanteDB.CLI_PRENOM + " TEXT NOT NULL )";


    private static final String CREATE_TA_FA=
            " CREATE TABLE " + ConstanteDB.FACTURES
            + "( " + ConstanteDB.FACT_ID  + " TEXT PRIMARY KEY ,"
            + ConstanteDB.FACT_DFIN + " INTEGER NOT NULL ,"
            + ConstanteDB.FACT_MONTANT + " REAL NOT NULL ,"
            +""
            + "FOREIGN KEY( " + ConstanteDB.FACT_ID  +") REFERENCES  " + ConstanteDB.LOCATIONS  +"( "+  ConstanteDB.LOC_ID   + " ))";


    private static final String CREATE_TA_Coo=
            " CREATE TABLE " + ConstanteDB.COORDONNEES
            + " ( " + ConstanteDB.COO_ID + " TEXT PRIMARY KEY ,"
            + ConstanteDB.COO_TEL + " TEXT NOT NULL ,"
            + ConstanteDB.COO_MAIL + " TEXT ,"
            + ConstanteDB.COO_ADRESSE + " TEXT ,"
            + ConstanteDB.COO_VILLE + " TEXT ,"
            + ""
            +" FOREIGN KEY ( " + ConstanteDB.COO_ID  +") REFERENCES  " + ConstanteDB.CLIENTS  +"( "+  ConstanteDB.CLI_ID   + " ))";








    public LocaCarDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        dropTable(db);
        createTable(db);
      //  db.execSQL("ALTER TABLE " + ConstanteDB.PHOTO_VOITURES+ " ADD COLUMN " + ConstanteDB.PhV_IMMAT_V+ " TEXT REFERENCES " + ConstanteDB.VEHICULES+ " ("+ ConstanteDB.V_IMMAT+")");

    }

    private void dropTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE " + ConstanteDB.FACTURES);
        db.execSQL("DROP TABLE  " +ConstanteDB.PHOTO_VOITURES);
        db.execSQL("DROP TABLE " + ConstanteDB.COORDONNEES)  ;
        db.execSQL("DROP TABLE " + ConstanteDB.LOCATIONS);
        db.execSQL("DROP TABLE " + ConstanteDB.CLIENTS);
        db.execSQL("DROP TABLE " + ConstanteDB.VEHICULES);
        db.execSQL("DROP TABLE " + ConstanteDB.AGENCES);
        db.execSQL("DROP TABLE " + ConstanteDB.GERANTS);
        db.execSQL("DROP TABLE " + ConstanteDB.MODELES);
        db.execSQL("DROP TABLE " + ConstanteDB.DETAILS_MODELES);
        db.execSQL("DROP TABLE " + ConstanteDB.MARQUES);
    }

    private void createTable(SQLiteDatabase db) {
        db.execSQL(CREATE_TA_MA);
        db.execSQL(CREATE_TA_DM);
        db.execSQL(CREATE_TA_MO);
        db.execSQL(CREATE_TA_GE);
        db.execSQL(CREATE_TA_AG);
        db.execSQL(CREATE_TA_VE);
        db.execSQL(CREATE_TA_CLI);
        db.execSQL(CREATE_TA_LO);
        db.execSQL(CREATE_TA_Coo);
        db.execSQL(CREATE_TA_PhV);
        db.execSQL(CREATE_TA_FA);

    }
}


