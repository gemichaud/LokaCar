package fr.eni.lokacar.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import fr.eni.lokacar.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView nom,designation;


    public ViewHolder(View itemView) {
        super(itemView);
        nom = itemView.findViewById(R.id.row_nom);
        designation = itemView.findViewById(R.id.row_prenom);


    }



}
