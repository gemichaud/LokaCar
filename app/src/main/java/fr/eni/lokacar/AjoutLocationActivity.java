package fr.eni.lokacar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.sip.SipSession;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;
import java.util.List;
import java.util.UUID;

import fr.eni.lokacar.Adapter.Client.ClientSpinnerAdapter;
import fr.eni.lokacar.Adapter.Vehicule.VehiculeSpinnerAdapter;
import fr.eni.lokacar.BLL.ClientManager;
import fr.eni.lokacar.BLL.VehiculeManager;
import fr.eni.lokacar.BO.Agence;
import fr.eni.lokacar.BO.Client;
import fr.eni.lokacar.BO.Location;
import fr.eni.lokacar.BO.Vehicule;
import fr.eni.lokacar.DAL.DAO.LocationDAO;

public class AjoutLocationActivity extends AppCompatActivity implements View.OnClickListener{

    private Agence agence;

    private Spinner spinnerClients;
    private Spinner spinnerVehicules;
    private TextView dateDebut;
    private TextView dateFin;
    private FloatingActionButton floatBtnDateDebut;
    private FloatingActionButton floatBtndateFin;
    private Button enregistrer;

    private List<Client> listClients;
    private List<Vehicule> listVehicules;

    private ClientSpinnerAdapter adapterClient;
    private VehiculeSpinnerAdapter adapterVehicule;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_location);

        intent = getIntent();
        agence = intent.getParcelableExtra("agence");

        spinnerClients = findViewById(R.id.clients_add_location);
        spinnerVehicules = findViewById(R.id.vehicules_add_location);

        dateDebut = findViewById(R.id.date_debut_add_location);
        dateFin = findViewById(R.id.date_fin_add_location);

        floatBtnDateDebut = findViewById(R.id.floatBtnPickDateDebut);
        floatBtndateFin = findViewById(R.id.floatBtnPickDateFin);


        ClientManager clientManager = new ClientManager(AjoutLocationActivity.this);
        listClients = clientManager.selectAllClient();

        VehiculeManager vehicule = new VehiculeManager(AjoutLocationActivity.this);
        listVehicules = vehicule.selectAllVehicule();


        adapterClient = new ClientSpinnerAdapter(
                AjoutLocationActivity.this,
                android.R.layout.simple_spinner_item,
                listClients);
        adapterClient.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerClients.setAdapter(adapterClient);

        adapterVehicule = new VehiculeSpinnerAdapter(
                AjoutLocationActivity.this,
                android.R.layout.simple_spinner_item,
                listVehicules);
        adapterVehicule.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerVehicules.setAdapter(adapterVehicule);

        floatBtnDateDebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v, 1, dateDebut);
            }
        });

        floatBtndateFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v, 2, dateFin);
            }
        });

        enregistrer = findViewById(R.id.btn_valider_loc);
        enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validerLoc();
            }
        });


    }

    private void validerLoc() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {

            Date dDebut = sdf.parse(dateDebut.getText().toString());
            Date dFin = sdf.parse(dateFin.getText().toString());
            Client c = (Client) spinnerClients.getSelectedItem();
            Vehicule v = (Vehicule) spinnerVehicules.getSelectedItem();

            Location l = new Location();
            l.setClient(c);
            l.setStatut("EC");
            l.setVehicule(v);
            l.setDateFinPrevu(dFin);
            l.setDateDebut(dDebut);
            l.setiD(UUID.randomUUID());
            LocationDAO locationDAO = new LocationDAO(AjoutLocationActivity.this);
            locationDAO.insertLocation(l);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void showDatePickerDialog(View view, int id, TextView tv) {

        DialogFragment newFragment = new DatePickerFragment();
        ((DatePickerFragment) newFragment).setId(id);
        ((DatePickerFragment) newFragment).setTv(tv);
        
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View v) {


    }


    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        private FloatingActionButton button;
        private TextView tv;
        private int id;

        public void setTv(TextView tv) {
            this.tv = tv;
        }

        public void setId(int id) {
            this.id = id;
        }

        public DatePickerFragment(){

        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            System.out.println(this.id);


            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }




        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                date = sdf.parse(String.valueOf(day) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));
                System.out.println(DatePickerFragment.this.getId());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            tv.setText(sdf.format(date));

        }
    }
}


