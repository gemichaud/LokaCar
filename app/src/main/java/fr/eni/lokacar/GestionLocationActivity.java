package fr.eni.lokacar;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.eni.lokacar.Adapter.LocationAdapter;
import fr.eni.lokacar.BLL.LocationManager;
import fr.eni.lokacar.BO.Agence;
import fr.eni.lokacar.BO.Location;

public class GestionLocationActivity extends AppCompatActivity {
    private FloatingActionButton btnEdit;
    private FloatingActionButton btnCall;
    private FloatingActionButton btnMenu;
    private FloatingActionButton btnMail;
    private LocationAdapter adapter;
    private List<Location> listLocations;
    private ListView lstView;
    private FloatingActionButton floatBtnAddLocation;
    private Agence agence;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_location);

        intent = getIntent();
        agence = intent.getParcelableExtra("agence");

        floatBtnAddLocation = findViewById(R.id.addLocation);

        floatBtnAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestionLocationActivity.this, AjoutLocationActivity.class);
                intent.putExtra("agence", agence);
                startActivity(intent);
            }
        });

        /**
         * Get Locations from DB
         *
         */


        lstView = findViewById(R.id.listLocation);
        listLocations = new ArrayList<>();


        /*JeuxDessai jd = new JeuxDessai();

        listLocations = jd.getLocations(this);


*/

    }


    @Override
    protected void onResume() {
        super.onResume();
        LocationManager locationManager = new LocationManager();
        listLocations = locationManager.selectAllEC(GestionLocationActivity.this);

        adapter = new LocationAdapter(
                GestionLocationActivity.this,
                R.layout.location_list,
                listLocations);

        lstView.setAdapter(adapter);


        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Location location = (Location) parent.getItemAtPosition(position);

                System.out.println(location.getiD());
                Intent intent = new Intent(GestionLocationActivity.this, DetailLocationActivity.class);
                //based on item add info to intent
                intent.putExtra("loc", location);
                startActivity(intent);
            }
        });

    }
}
