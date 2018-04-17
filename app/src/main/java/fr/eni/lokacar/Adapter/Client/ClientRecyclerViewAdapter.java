package fr.eni.lokacar.Adapter.Client;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


import fr.eni.lokacar.Adapter.OnItemClickListener;
import fr.eni.lokacar.Adapter.ViewHolder;
import fr.eni.lokacar.BO.Client;
import fr.eni.lokacar.R;

public class ClientRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private OnItemClickListener listener;
    private List<Client> clients;

    public ClientRecyclerViewAdapter(List<Client> clients, OnItemClickListener listener) {
        this.clients = clients;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater  .from(parent.getContext()).inflate(R.layout.client_row,parent, false);
        final ViewHolder m = new ViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, m.getAdapterPosition());
            }
        });
        return m;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Client c = clients.get(position);

        holder.nom.setText(c.getNom());
        holder.designation.setText(c.getPrenom());

    }

    @Override
    public int getItemCount() {
        return clients.size();
    }


}
