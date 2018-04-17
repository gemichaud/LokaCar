package fr.eni.lokacar;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import fr.eni.lokacar.BO.LocalisationVehicule;
import fr.eni.lokacar.BO.PhotoVehicule;
import fr.eni.lokacar.BO.Vehicule;

public class EtatDesLieuxActivity extends AppCompatActivity implements OnClickListener{

    private FloatingActionButton floatBtnAvant;
    private FloatingActionButton floatBtnAvGauche;
    private FloatingActionButton floatBtnGauche;
    private FloatingActionButton floatBtnArGauche;
    private FloatingActionButton floatBtnArriere;
    private FloatingActionButton floatBtnArDroit;
    private FloatingActionButton floatBtnDroit;
    private FloatingActionButton floatBtnAvDroit;
    private FloatingActionButton floatBtnInterieur;
    private Intent intent;
    private Vehicule vehicule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etat_des_lieux);

        floatBtnAvant = findViewById(R.id.avant);
        floatBtnAvGauche = findViewById(R.id.avGauche);
        floatBtnGauche = findViewById(R.id.gauche);
        floatBtnArGauche = findViewById(R.id.arGauche);
        floatBtnArriere = findViewById(R.id.arriere);
        floatBtnArDroit = findViewById(R.id.arDroit);
        floatBtnDroit = findViewById(R.id.droit);
        floatBtnAvDroit = findViewById(R.id.avDroit);
        floatBtnInterieur = findViewById(R.id.interieur);

        floatBtnAvant.setOnClickListener(this);
        floatBtnAvGauche.setOnClickListener(this);
        floatBtnGauche.setOnClickListener(this);
        floatBtnArGauche.setOnClickListener(this);
        floatBtnArriere.setOnClickListener(this);
        floatBtnArDroit.setOnClickListener(this);
        floatBtnDroit.setOnClickListener(this);
        floatBtnAvDroit.setOnClickListener(this);
        floatBtnInterieur.setOnClickListener(this);

        floatBtnAvant.setTag(0);
        floatBtnAvGauche.setTag(1);
        floatBtnGauche.setTag(2);
        floatBtnArGauche.setTag(3);
        floatBtnArriere.setTag(4);
        floatBtnArDroit.setTag(5);
        floatBtnDroit.setTag(6);
        floatBtnAvDroit.setTag(7);
        floatBtnInterieur.setTag(8);



        intent = getIntent();

        vehicule = intent.getParcelableExtra("vehicule");

    }

    @Override
    public void onClick(View v) {

        PhotoVehicule photoVehicule = new PhotoVehicule();
        int btnClicked = (int)v.getTag();

       switch (btnClicked){
           case 0 : photoVehicule.setLocalisationVehiculeEnum(LocalisationVehicule.Avant);
               break;
           case 1 : photoVehicule.setLocalisationVehiculeEnum(LocalisationVehicule.AvantGauche);
               break;
           case 2 : photoVehicule.setLocalisationVehiculeEnum(LocalisationVehicule.Gauche);
               break;
           case 3 : photoVehicule.setLocalisationVehiculeEnum(LocalisationVehicule.ArriereGauche);
               break;
           case 4 : photoVehicule.setLocalisationVehiculeEnum(LocalisationVehicule.Arriere);
               break;
           case 5 : photoVehicule.setLocalisationVehiculeEnum(LocalisationVehicule.ArriereDroit);
               break;
           case 6 : photoVehicule.setLocalisationVehiculeEnum(LocalisationVehicule.Droit);
               break;
           case 7 : photoVehicule.setLocalisationVehiculeEnum(LocalisationVehicule.AvantDroit);
               break;
           case 8 : photoVehicule.setLocalisationVehiculeEnum(LocalisationVehicule.Interieur);
               break;

       }

       intent.putExtra("photoVehicule", photoVehicule);



    }
}
