package fr.eni.lokacar.DAL.CreationBASE;

public final class ConstanteDB {

    //CONSTANTES DETAILS MODELES
    public static final String DETAILS_MODELES = "DETAILS_MODELES";
    public static final String DM_CNIT="CNIT";
    public static final String DM_MODEL_COM="MODELE_COMMERCIAL";
    public static final String DM_DESIGNATION= "DESIGNATION";
    public static final String DM_BOITE="BOITE_VITESSE";
    public static final String DM_GAMME="GAMME";
    public static final String DM_CARBURANT="CARBURANT";
    public static final String DM_CARROSSERIE="CARROSSERIE";

    //CONSTANTES MODELES
    public static final String MODELES = "MODELES";
    public static final String MO_CNIT = "CNIT";
    public static final String MO_NOM= "Nom";
    public static final String MO_ID_MARQUE = "ID_MARQUE";

    //CONSTANTES MARQUES;
    public static final String MARQUES= "MARQUES";
    public static final String MA_NOM ="NOM";
    public static final String MA_ID="ID";

    //CONSTANTES VEHICULES
    public static final String VEHICULES = "VEHICULES";
    public static final String V_KM="KILOMETRAGE";
    public static final String V_STATUT="STATUT";
    public static final String V_IMMAT ="IMMATRICULATION";
    public static final String V_CNIT_VE ="CNIT";
    public static final String V_AGENCE_ID ="AGENCE_ID";
    public static final String V_PRIX_JOUR ="PRIX_JOURNALIER";

    //CONSTANTES AGENCE

    public static final String AGENCES = "AGENCES";
    public static final String A_ID ="ID";
    public static final String A_VILLE ="VILLE";
    public static final String A_ADRESSE ="ADRESSE";
    public static final String A_ID_GERANT = "ID_GERANT";

    //CONSTANTES GERANT
    public static final String GERANTS = "GERANTS";
    public static final String G_ID= "ID";
    public static final String G_NOM= "NOM";
    public static final String G_PRENOM = "PRENOM";
    public static final String G_LOGIN = "LOGIN";
    public static final String G_PASSWORD = "PASSWORD";

    //CONSTANTES LOCATIONS
    public static final String LOCATIONS= "LOCATIONS";
    public static final String LOC_ID= "ID";
    public static final String LOC_DDEBUT= "DATE_DEBUT";
    public static final String LOC_DFIN = "DATE_FIN_PREVU";
    public static final String LOC_STATUT = "STATUT";
    public static final String LOC_IMMAT_V= "IMMAT_VEHICULE";
    public static final String LOC_CLI_ID = "ID_CLIENT";

    //CONSTANTES FACTURE
    public static final String FACTURES= "FACTURES";
    public static final String FACT_ID= "ID";
    public static final String FACT_DFIN= "DATE_RETOUR_REEL";
    public static final String FACT_MONTANT= "MONTANT";

    //CONSTANTES PHOTO VOITURE
    public static final String PHOTO_VOITURES= "PHOTO_VEHICULE";
    public static final String PhV_ID = "ID";
    public static final String PhV_PATH  = "PATH";
    public static final String PhV_DERNIERE = "DERNIERE";
    public static final String PhV_Localisation  = "PATH";
    public static final String PhV_ID_LOC = "ID_LOCATION";

    //CONSTANTES PHOTO Client
    public static final String CLIENTS= "CLIENTS";
    public static final String CLI_ID= "ID";
    public static final String CLI_NOM = "NOM";
    public static final String CLI_PRENOM= "PRENOM";


    //CONSTANTES PHOTO COORDONNEES
    public static final String COORDONNEES= "COORDONNEES";
    public static final String COO_ID= "ID_CLIENT";
    public static final String COO_TEL= "TELEPHONE";
    public static final String COO_MAIL= "MAIL";
    public static final String COO_ADRESSE= "ADRESSE";
    public static final String COO_VILLE= "VILLE";
}
