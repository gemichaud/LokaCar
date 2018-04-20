package fr.eni.lokacar.Adapter.Client;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import fr.eni.lokacar.BO.Client;
import fr.eni.lokacar.BO.Location;
import fr.eni.lokacar.R;

public class ClientSpinnerAdapter extends ArrayAdapter<Client> {

    private List<Client> listClient;
    private Context c;
    private int res;

    public ClientSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Client> objects) {
        super(context, resource, objects);
        this.listClient = objects;
        this.res = resource;
        this.c = context;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(this.getItem(position).getPrenom() + " " + this.getItem(position).getNom());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(this.getItem(position).getPrenom() + " " + this.getItem(position).getNom());
        return label;
    }

    static class ViewHolder {

        TextView avClient;
    }


    public Client getItem(int position) {
        return listClient.get(position);
    }
}
