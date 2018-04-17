package fr.eni.lokacar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import fr.eni.lokacar.BO.Agence;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Agence a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        TextView welcome = findViewById(R.id.menu_welcome);
        Intent i = getIntent();
        if (i != null){
            this.a = i.getParcelableExtra("agence");
            welcome.setText(" Bonjour " + a.getGerant().getPrenom() + " " +a.getGerant().getPrenom() + " ! Bienvenue sur l'agence de " +a.getVille());
        }
        ImageButton btnAgence = findViewById(R.id.btn_menu_agence);
        ImageButton btnCli= findViewById(R.id.btn_menu_client);
        ImageButton btnVehi= findViewById(R.id.btn_menu_vehicule);
        ImageButton btnLoc= findViewById(R.id.btn_menu_location);

        btnAgence.setOnClickListener(this);
        btnCli.setOnClickListener(this);
        btnVehi.setOnClickListener(this);
        btnLoc.setOnClickListener(this);

        btnAgence.setTag(0);
        btnCli.setTag(1);
        btnLoc.setTag(2);
        btnVehi.setTag(3);



    }

    @Override
    public void onClick(View v) {

        int btnClicked = (int)v.getTag();
        Class c = null ;
        switch (btnClicked){
            case 0 : c = AccueilActivity.class;
                break;
            case 1 : c = GestionClientActivity.class;
                break;
            case 2 : c = null;
                break;
            case 3 : c = null;
                break;


        }

        if (c != null){

            Intent i = new Intent(MenuActivity.this, c);
            i.putExtra("agence",this.a );
            MenuActivity.this.startActivity(i);
        }








    }
}
