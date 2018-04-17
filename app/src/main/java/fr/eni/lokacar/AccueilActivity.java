package fr.eni.lokacar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;
import java.util.Date;
import java.util.UUID;

import fr.eni.lokacar.BLL.GerantManager;
import fr.eni.lokacar.BO.Agence;
import fr.eni.lokacar.BO.Gerant;
import fr.eni.lokacar.DAL.DAO.AgenceDAO;
import fr.eni.lokacar.DAL.DAO.GerantDAO;
import fr.eni.lokacar.Tools.DateTools;

public class AccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);


        Button btnLog = findViewById(R.id.connect_accueil);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtLogin = findViewById(R.id.login_accueil);
                EditText txtPassword = findViewById(R.id.pass_accueil);

                String login = txtLogin.getText().toString();
                String password = txtPassword.getText().toString();

                GerantManager gerantManager = new GerantManager();

                Agence a= null;
                try {
                    a = gerantManager.connect(AccueilActivity.this, login, password);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                if (a != null){
                    Intent i = new Intent(AccueilActivity.this, MenuActivity.class);
                    i.putExtra("agence" , a);
                    AccueilActivity.this.startActivity(i);


                }



            }
        });
    }
}
