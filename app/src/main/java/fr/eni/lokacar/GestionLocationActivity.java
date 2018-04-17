package fr.eni.lokacar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import fr.eni.lokacar.BO.Location;
import fr.eni.lokacar.adapter.LocationAdapter;

public class GestionLocationActivity extends AppCompatActivity {

    private LocationAdapter adapter;
    private List<Location> listLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_location);

        /**
         * Get Locations from DB
         *
         */


    }
}
