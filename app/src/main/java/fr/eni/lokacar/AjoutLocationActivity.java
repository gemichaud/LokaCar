package fr.eni.lokacar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fr.eni.lokacar.BO.Agence;

public class AjoutLocationActivity extends AppCompatActivity implements View.OnClickListener{

    private Agence agence;

    private Spinner spinnerClients;
    private Spinner spinnerVehicules;
    private TextView dateDebut;
    private TextView dateFin;
    private FloatingActionButton floatBtnDateDebut;
    private FloatingActionButton floatBtndateFin;
    private Button eregistrer;

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






     /*   ArrayAdapter<String> dataAdapterClients = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listClients);
        dataAdapterClients.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClients.setAdapter(dataAdapterClients);


        ArrayAdapter<String> dataAdapterVehicules = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listVehicules);
        dataAdapterVehicules.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVehicules.setAdapter(dataAdapterVehicules);*/

    }

    public void showDatePickerDialog(View view) {
        int tag = (int)view.getTag();
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View v) {

    }


    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        public DatePickerFragment(){

        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }




        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date date = sdf.parse(String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }
}


