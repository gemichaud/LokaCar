package fr.eni.lokacar;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.eni.lokacar.BLL.ClientManager;
import fr.eni.lokacar.BO.Client;
import fr.eni.lokacar.BO.Coordonnee;

public class AjoutClientActivity extends AppCompatActivity {

    TextInputLayout txtNom;
    TextInputLayout txtPrenom;
    TextInputLayout txtTel;
    TextInputLayout txtMail;
    TextInputLayout txtAdresse;
    TextInputLayout txtVille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_client);

        Button btnAnnuler = findViewById(R.id.btn_annuler_ajout_cli);
        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AjoutClientActivity.this.finish();
            }
        });

        txtAdresse = findViewById(R.id.txt_add_cli_adresse);
        txtNom = findViewById(R.id.txt_add_cli_nom);
        txtPrenom = findViewById(R.id.txt_add_cli_prenom);
        txtTel = findViewById(R.id.txt_add_cli_telephone);
        txtMail = findViewById(R.id.txt_add_cli_mail);
        txtVille = findViewById(R.id.txt_add_cli_ville);

        Button btnValider = findViewById(R.id.btn_valider_ajout_cli);

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validationFormulaire()){
                    insertClient();
                    AjoutClientActivity.this.finish();
                }
            }
        });
    }

    private boolean validationFormulaire() {
        boolean isOK = true ;
        if(txtAdresse.getEditText().getText().toString().trim().equals("")){
            txtAdresse.setError("L'adresse est requise");
            isOK=false;
        }
        if(txtMail.getEditText().getText().toString().trim().equals("")){
            txtMail.setError("L'adresse mail est requise");
            isOK=false;
        }
        if(txtNom.getEditText().getText().toString().trim().equals("")){
            txtNom.setError("Le nom est requis");
            isOK=false;
        }
        if(txtPrenom.getEditText().getText().toString().trim().equals("")){
            txtPrenom.setError("Le prenom est requis");
            isOK=false;
        }
        if(txtTel.getEditText().getText().toString().trim().equals("")){
            txtTel.setError("Le numero de telephone est requis");
            isOK=false;
        }
        if(txtVille.getEditText().getText().toString().trim().equals("")){
            txtVille.setError("La ville est requise");
            isOK=false;
        }



        return isOK;
    }

    private void insertClient(){
        Client c = recuperationClient();
        ClientManager cManager = new ClientManager(AjoutClientActivity.this);
        cManager.insertClient(c);
    }

    private Client recuperationClient() {
        Client c = new Client();
        c.setNom(txtNom.getEditText().getText().toString().trim());
        c.setPrenom(txtPrenom.getEditText().getText().toString().trim());

        Coordonnee coo = new Coordonnee();
        coo.setAdresse(txtAdresse.getEditText().getText().toString().trim());
        coo.setEmail(txtMail.getEditText().getText().toString().trim());
        coo.setTelephone(txtTel.getEditText().getText().toString().trim());
        coo.setVille(txtVille.getEditText().getText().toString().trim());

        c.setCoordonee(coo);

        return c;
    }
}
