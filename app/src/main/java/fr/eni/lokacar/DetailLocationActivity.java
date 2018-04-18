package fr.eni.lokacar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import fr.eni.lokacar.BO.Location;

public class DetailLocationActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    private TextView client;
    private TextView adresse;
    private TextView telephone;
    private TextView email;
    private TextView nomVehicule;
    private TextView immat;
    private TextView dateDebut;
    private TextView dateFin;

    private Button btnFinLocation;
    private Button btnEtatDesLieux;

    private Location loc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);

        intent = getIntent();
        loc = intent.getParcelableExtra("loc");


        client = findViewById(R.id.client_detail_location);
        adresse = findViewById(R.id.adresse_detail_location);
        telephone = findViewById(R.id.telephone_detail_location);
        email = findViewById(R.id.email_detail_location);
        nomVehicule = findViewById(R.id.nom_vehicule_detail_location);
        immat = findViewById(R.id.immat_detail_location);
        dateDebut = findViewById(R.id.date_debut_detail_location);
        dateFin = findViewById(R.id.date_fin_detail_location);


        client.setText(loc.getClient().getPrenom() +" "+ loc.getClient().getNom());
        adresse.setText(loc.getClient().getCoordonee().getAdresse() + " " + loc.getClient().getCoordonee().getVille());
        telephone.setText(loc.getClient().getCoordonee().getTelephone());
        email.setText(loc.getClient().getCoordonee().getEmail());
        nomVehicule.setText(loc.getVehicule().getModele().getDetailModele().getModeleCommercial());
        immat.setText(loc.getVehicule().getImmatriculation());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date dateD = loc.getDateDebut();
        Date dateF = loc.getDateFinPrevu();
        System.out.println(dateD);
        System.out.println(dateF);

        dateDebut.setText(sdf.format(dateD));
        dateFin.setText(sdf.format(dateF));


        btnFinLocation = findViewById(R.id.btn_terminer_detail_location);
        btnEtatDesLieux = findViewById(R.id.btn_etat_des_lieux_detail_location);
        btnFinLocation.setOnClickListener(this);
        btnEtatDesLieux.setOnClickListener(this);
        btnFinLocation.setTag(0);
        btnEtatDesLieux.setTag(1);



    }

    @Override
    public void onClick(View v) {

        int tagBtn = (int)v.getTag();
        System.out.println(tagBtn);

        switch (tagBtn){
            case 0 : //Terminer location ==> update location
                break;
            case 1 :

                Intent intat = new Intent(DetailLocationActivity.this, EtatDesLieuxActivity.class);
                //based on item add info to intent
                intat.putExtra("location", loc);
                startActivity(intat);

                break;
        }
    }
}
