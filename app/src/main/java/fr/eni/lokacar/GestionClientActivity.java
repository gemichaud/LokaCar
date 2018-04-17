package fr.eni.lokacar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.eni.lokacar.Adapter.Client.ClientRecyclerViewAdapter;
import fr.eni.lokacar.Adapter.OnItemClickListener;
import fr.eni.lokacar.BO.Client;

public class GestionClientActivity extends AppCompatActivity {

    private RecyclerView rV;
    private ClientRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_client);

        Client c = new Client();
        c.setNom("toto");
        c.setPrenom("tutu");
        c.setiD(UUID.randomUUID());

        Client c1 = new Client();
        c1.setNom("zozo");
        c1.setPrenom("zuzu");
        c1.setiD(UUID.randomUUID());

        Client c2= new Client();
        c2.setNom("roro");
        c2.setPrenom("ruru");
        c2.setiD(UUID.randomUUID());

        Client c3 = new Client();
        c3.setNom("momo");
        c3.setPrenom("mumu");
        c3.setiD(UUID.randomUUID());

        Client c4 = new Client();
        c4.setNom("koko");
        c4.setPrenom("kuku");
        c4.setiD(UUID.randomUUID());

        final List<Client> clients = new ArrayList<>();
        clients.add(c);
        clients.add(c1);
        clients.add(c2);
        clients.add(c3);
        clients.add(c4);

        rV = findViewById(R.id.list_clients);
        adapter = new ClientRecyclerViewAdapter(clients, new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                System.out.println(clients.get(position).getNom());
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GestionClientActivity.this);
        rV.setLayoutManager(layoutManager);
        rV.setItemAnimator(new DefaultItemAnimator());
        rV.setAdapter(adapter);

    }
}
