package fr.eni.lokacar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.eni.lokacar.BO.Location;
import fr.eni.lokacar.Tools.JeuxDessai;
import fr.eni.lokacar.Adapter.LocationAdapter;

public class GestionLocationActivity extends AppCompatActivity {

    private LocationAdapter adapter;
    private List<Location> listLocations;
    private ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_location);

        /**
         * Get Locations from DB
         *
         */


        listLocations = new ArrayList<Location>();


        JeuxDessai jd = new JeuxDessai();

        listLocations = jd.getLocations(this);

        adapter = new LocationAdapter(
                GestionLocationActivity.this,
                R.layout.location_list,
                listLocations);

        lstView.setAdapter(adapter);


    }
}
