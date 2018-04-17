package fr.eni.lokacar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.NoSuchAlgorithmException;

import fr.eni.lokacar.BLL.GerantManager;
import fr.eni.lokacar.BO.Gerant;
import fr.eni.lokacar.DAL.DAO.GerantDAO;

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

                Gerant g;
                try {
                    g = gerantManager.connect(AccueilActivity.this, login, password);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
