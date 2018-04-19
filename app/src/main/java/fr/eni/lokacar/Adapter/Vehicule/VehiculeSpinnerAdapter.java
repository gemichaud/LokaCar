package fr.eni.lokacar.Adapter.Vehicule;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.eni.lokacar.BO.Client;
import fr.eni.lokacar.BO.Vehicule;
import fr.eni.lokacar.R;

public class VehiculeSpinnerAdapter extends ArrayAdapter<Vehicule> {

    private List<Vehicule> listVehicule;
    private Context c;
    private int res;

    public VehiculeSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Vehicule> objects) {
        super(context, resource, objects);
        this.listVehicule = objects;
        this.res = resource;
        this.c = context;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(this.getItem(position).getModele().getNom());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(this.getItem(position).getModele().getNom() + " " + this.getItem(position).getImmatriculation());
        return label;
    }


    static class ViewHolder{
        TextView avVehicule;
    }


    public Vehicule getItem(int position) {
        return listVehicule.get(position);
    }
}
