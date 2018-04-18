package fr.eni.lokacar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.eni.lokacar.Adapter.Client.ClientRecyclerViewAdapter;
import fr.eni.lokacar.Adapter.OnItemClickListener;
import fr.eni.lokacar.BLL.ClientManager;
import fr.eni.lokacar.BO.Client;

public class GestionClientActivity extends AppCompatActivity {

    private RecyclerView rV;
    private ClientRecyclerViewAdapter adapter;
    private List<Client> clients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_client);


        FloatingActionButton addCli = findViewById(R.id.btn_add_cli);
        addCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GestionClientActivity.this, AjoutClientActivity.class);
                GestionClientActivity.this.startActivity(i);
            }
        });

        ImageButton btnSearch = findViewById(R.id.cli_search_button);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtName = findViewById(R.id.cli_search_name);
                String name = txtName.getText().toString();
                ClientManager clientManager = new ClientManager(GestionClientActivity.this);
                clients = clientManager.searchByName(name);
                initIHM();

            }
        });


    }

    private void initList() {
        this.clients = getClients();
        initIHM();
    }

    private void initIHM() {


        rV = findViewById(R.id.list_clients);
        adapter = new ClientRecyclerViewAdapter(clients, new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                ClientManager clientManager = new ClientManager(GestionClientActivity.this);
                Client cli = clientManager.selectClientById(clients.get(position).getiD());
                Intent i = new Intent(GestionClientActivity.this, DetailClientActivity.class);
                i.putExtra("client", cli);
                GestionClientActivity.this.startActivity(i);

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GestionClientActivity.this);
        rV.setLayoutManager(layoutManager);
        rV.setItemAnimator(new DefaultItemAnimator());
        rV.setAdapter(adapter);
    }

    @NonNull
    private List<Client> getClients() {
        ClientManager cM = new ClientManager(GestionClientActivity.this);
        return cM.selectAllClient();
    }


    @Override
    protected void onResume() {
        super.onResume();

        initList();
    }
}
