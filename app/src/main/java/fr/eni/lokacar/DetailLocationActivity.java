package fr.eni.lokacar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.DateInterval;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import fr.eni.lokacar.BO.Facture;
import fr.eni.lokacar.BO.Location;
import fr.eni.lokacar.DAL.DAO.LocationDAO;

public class DetailLocationActivity extends AppCompatActivity implements View.OnClickListener {


    private FloatingActionButton btnEdit;
    private FloatingActionButton btnCall;
    private FloatingActionButton btnMenu;
    private FloatingActionButton btnMail;
    private Intent intent;
    private TextView client;
    private TextView adresse;
    private TextView telephone;
    private TextView email;
    private TextView nomVehicule;
    private TextView immat;
    private TextView dateDebut;
    private TextView dateFin;
    private TextView txtDateRetourReel;

    private Button btnFinLocation;
    private Button btnEtatDesLieux;
    private Date dateRetour;
    private Location loc;
    private FloatingActionButton btnDateRetourReel;
    private boolean isMenuOpen = false;
    private static final int ANIMATION = 300;
    private static final float FACTEUR_UN_DEMI = 0.5f;
    private static final float FACTEUR_RACINE_3_2 = 0.866025404f;


    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);

        intent = getIntent();
        loc = intent.getParcelableExtra("loc");

        LocationDAO locationDAO = new LocationDAO(DetailLocationActivity.this);
        loc = locationDAO.getDetail(loc);


        client = findViewById(R.id.client_detail_location);
        adresse = findViewById(R.id.adresse_detail_location);
        telephone = findViewById(R.id.telephone_detail_location);
        email = findViewById(R.id.email_detail_location);
        nomVehicule = findViewById(R.id.nom_vehicule_detail_location);
        immat = findViewById(R.id.immat_detail_location);
        dateDebut = findViewById(R.id.date_debut_detail_location);
        dateFin = findViewById(R.id.date_fin_detail_location);
        btnDateRetourReel = findViewById(R.id.floatBtnPickDateReel);
        txtDateRetourReel = findViewById(R.id.txt_date_retour_reel);

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
        btnDateRetourReel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v, txtDateRetourReel);
            }
        });
        btnMenu = findViewById(R.id.btn_loc_menu_cli);
        btnCall = findViewById(R.id.btn_loc_call_cli);
        btnEdit = findViewById(R.id.btn_loc_edit_cli);
        btnMail = findViewById(R.id.btn_loc_mail_cli);


        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMenuOpen) {

                    btnCall.animate().translationX(0).translationY((0));
                    btnCall.setVisibility(View.GONE);
                    btnMail.animate().translationX(0).translationY(0);
                    btnMail.setVisibility(View.GONE);
                    isMenuOpen = false;

                } else {

                    btnCall.setVisibility(View.VISIBLE);
                    btnCall.animate().translationX(-ANIMATION * 0.94f).translationY((-ANIMATION * 0.34f));
                    btnMail.setVisibility(View.VISIBLE);
                    btnMail.animate().translationX(-ANIMATION * 0.34f).translationY(-ANIMATION * 0.94f);
                    isMenuOpen = true;

                }
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + loc.getClient().getCoordonee().getTelephone()));
                DetailLocationActivity.this.startActivity(i);
            }
        });

        btnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:" + loc.getClient().getCoordonee().getEmail()));

                DetailLocationActivity.this.startActivity(i);
            }
        });
    }


    @Override
    public void onClick(View v) {

        int tagBtn = (int)v.getTag();
        System.out.println(tagBtn);

        switch (tagBtn){
            case 0:
                terminerLocation(v);
                break;
            case 1 :

                Intent intat = new Intent(DetailLocationActivity.this, EtatDesLieuxActivity.class);
                //based on item add info to intent
                intat.putExtra("location", loc);
                startActivity(intat);

                break;
        }
    }


    private void terminerLocation(View v) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        EditText kilometrage = findViewById(R.id.txt_det_km_ret);
        String kilometrageString = kilometrage.getText().toString();
        try {
            Date dateRetourReel = sdf.parse(txtDateRetourReel.getText().toString());
            if (dateRetourReel.after(this.loc.getDateDebut()) && !kilometrageString.trim().equals("")) {
                Facture f = new Facture();

                try {
                    f.getLocation().getVehicule().setKilometrage(Integer.parseInt(kilometrageString));
                } catch (Exception e) {
                    Toast.makeText(DetailLocationActivity.this, "format de kilometrage invalide", Toast.LENGTH_LONG);
                }


                long diffInMillies = Math.abs(dateRetourReel.getTime() - loc.getDateDebut().getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                f.setMontant(diff * loc.getVehicule().getPrixJournalier());
                f.setDateFinReel(dateRetourReel);
                f.setLocation(loc);
                loc.setStatut("T");
                loc.getVehicule().setEtat("LB");

                LocationDAO locationDAO = new LocationDAO(DetailLocationActivity.this);

                boolean terminateOK = locationDAO.terminateFacture(f);
                if (terminateOK) {
                    btnEtatDesLieux.setVisibility(View.GONE);
                    btnFinLocation.setVisibility(View.GONE);
                    btnDateRetourReel.setVisibility(View.GONE);
                    AlertDialog alertDialog = new AlertDialog.Builder(DetailLocationActivity.this).create();
                    alertDialog.setTitle("Location Terminée");
                    alertDialog.setMessage("La location a duré " + diff + " jours, le montant à facturer est de " + f.getMontant() + " € ");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                } else {
                    Toast.makeText(DetailLocationActivity.this, "Impossible de terminer la location", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(DetailLocationActivity.this, "La date de retour doit être supérieur à la date de début de location", Toast.LENGTH_LONG).show();
            }
        } catch (ParseException e) {
            Toast.makeText(DetailLocationActivity.this, "Format de date invalide", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


    }

    public void showDatePickerDialog(View view, TextView txt) {

        DialogFragment newFragment = new DetailLocationActivity.DatePickerFragment();
        ((DetailLocationActivity.DatePickerFragment) newFragment).setTv(txt);


        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private TextView tv;


        public void setTv(TextView tv) {
            this.tv = tv;
        }

        public DatePickerFragment() {

        }


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            CharSequence title = "Saisissez la date de retour";
            DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, year, month, day);
            dpd.setTitle(title);


            // Create a new instance of DatePickerDialog and return it
            return dpd;
        }


        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            CharSequence title = "Saisissez la date de retour";

            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                date = sdf.parse(String.valueOf(day) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));

            } catch (ParseException e) {
                e.printStackTrace();
            }

            this.tv.setText(sdf.format(date));

        }


    }
}
