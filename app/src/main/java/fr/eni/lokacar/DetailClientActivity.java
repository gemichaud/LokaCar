package fr.eni.lokacar;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.eni.lokacar.BLL.ClientManager;
import fr.eni.lokacar.BO.Client;
import fr.eni.lokacar.BO.Coordonnee;

public class DetailClientActivity extends AppCompatActivity {

    private FloatingActionButton btnEdit;
    private FloatingActionButton btnCall;
    private FloatingActionButton btnMenu;
    private FloatingActionButton btnMail;
    private List<FloatingActionButton> floatingActionButtons = new ArrayList<>();
    private Client cli;
    private TextInputLayout txtNom;
    private TextInputLayout txtPrenom;
    private TextInputLayout txtPhone;
    private TextInputLayout txtAdresse;
    private TextInputLayout txtVille;
    private TextInputLayout txtMail;
    private Button btnValider;
    private Button btnAnnuler;

    private List<TextInputLayout> textInputLayouts = new ArrayList<>();
    private static final int ANIMATION = 300;
    private static final float FACTEUR = 0.707106781f;
    public boolean isMenuOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_client);

        Intent i = getIntent();

        if (i != null) {
            cli = i.getParcelableExtra("client");
        }

        initIHM();
        btnMenu = findViewById(R.id.btn_det_menu_cli);
        btnCall = findViewById(R.id.btn_det_call_cli);
        btnEdit = findViewById(R.id.btn_det_edit_cli);
        btnMail = findViewById(R.id.btn_det_mail_cli);
        floatingActionButtons.add(btnCall);
        floatingActionButtons.add(btnEdit);
        floatingActionButtons.add(btnMenu);
        floatingActionButtons.add(btnMail);
        btnAnnuler = findViewById(R.id.btn_annuler_det_cli);

        btnValider = findViewById(R.id.btn_valider_det_cli);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEdit.setVisibility(View.GONE);
                for (TextInputLayout t : textInputLayouts) {
                    t.getEditText().setEnabled(true);
                }

                for (FloatingActionButton f : floatingActionButtons) {
                    f.setVisibility(View.GONE);
                }

                btnValider.setVisibility(View.VISIBLE);


                btnAnnuler.setVisibility(View.VISIBLE);
            }


        });

        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeEdit(cli);
            }
        });


        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client cliUpdate = new Client();

                cliUpdate.setiD(cli.getiD());
                cliUpdate.setNom(txtNom.getEditText().getText().toString());
                cliUpdate.setPrenom(txtPrenom.getEditText().getText().toString());
                Coordonnee coo = new Coordonnee();
                coo.setEmail(txtMail.getEditText().getText().toString());
                coo.setTelephone(txtPhone.getEditText().getText().toString());
                coo.setAdresse(txtAdresse.getEditText().getText().toString());
                coo.setVille(txtVille.getEditText().getText().toString());
                cliUpdate.setCoordonee(coo);

                ClientManager clientManager = new ClientManager(DetailClientActivity.this);
                boolean isOK = clientManager.updateClient(cliUpdate);
                if (isOK) {
                    cli = cliUpdate;
                    closeEdit(cliUpdate);

                } else {

                    Toast.makeText(DetailClientActivity.this, "Modification impossible", Toast.LENGTH_SHORT).show();

                }

            }
        });


        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMenuOpen) {
                    btnEdit.animate().translationY(0);
                    btnEdit.setVisibility(View.GONE);
                    btnCall.animate().translationX(0).translationY((0));
                    btnCall.setVisibility(View.GONE);
                    btnMail.animate().translationX(0);
                    btnMail.setVisibility(View.GONE);
                    isMenuOpen = false;

                } else {
                    btnEdit.setVisibility(View.VISIBLE);
                    btnEdit.animate().translationY(-ANIMATION);
                    btnCall.setVisibility(View.VISIBLE);
                    btnCall.animate().translationX(-ANIMATION * FACTEUR).translationY((-ANIMATION * FACTEUR));
                    btnMail.setVisibility(View.VISIBLE);
                    btnMail.animate().translationX(-ANIMATION);
                    isMenuOpen = true;

                }
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + txtPhone.getEditText().getText()));
                DetailClientActivity.this.startActivity(i);
            }
        });

        btnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:" + txtMail.getEditText().getText()));

                DetailClientActivity.this.startActivity(i);
            }
        });
    }

    private void closeEdit(Client cliUpdate) {

        initIHM();
        for (TextInputLayout t : textInputLayouts) {
            t.getEditText().setEnabled(false);
        }
        btnAnnuler.setVisibility(View.GONE);
        btnValider.setVisibility(View.GONE);
        btnMenu.setVisibility(View.VISIBLE);
    }

    private void initIHM() {
        txtNom = findViewById(R.id.txt_det_cli_nom);
        txtNom.getEditText().setText(cli.getNom());
        textInputLayouts.add(txtNom);

        txtPrenom = findViewById(R.id.txt_det_cli_prenom);
        textInputLayouts.add(txtPrenom);
        txtPrenom.getEditText().setText(cli.getPrenom());

        txtPhone = findViewById(R.id.txt_det_cli_telephone);
        textInputLayouts.add(txtPhone);
        txtPhone.getEditText().setText(cli.getCoordonee().getTelephone());

        txtAdresse = findViewById(R.id.txt_det_cli_adresse);
        textInputLayouts.add(txtAdresse);
        txtAdresse.getEditText().setText(cli.getCoordonee().getAdresse());

        txtVille = findViewById(R.id.txt_det_cli_ville);
        textInputLayouts.add(txtVille);
        txtVille.getEditText().setText(cli.getCoordonee().getVille());

        txtMail = findViewById(R.id.txt_det_cli_mail);
        textInputLayouts.add(txtMail);
        txtMail.getEditText().setText(cli.getCoordonee().getEmail());
    }
}
