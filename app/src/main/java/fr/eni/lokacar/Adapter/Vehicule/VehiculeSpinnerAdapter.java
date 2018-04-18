package fr.eni.lokacar.Adapter.Vehicule;

import android.content.Context;
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


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = inflater.inflate(res,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.avVehicule = (TextView)convertView.findViewById(R.id.lstVehicule);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Vehicule vehicule = getItem(position);

        viewHolder.avVehicule.setText(vehicule.getModele().getDetailModele().getModeleCommercial() + " (" + vehicule.getImmatriculation() + ")");


        return convertView;

    }


    static class ViewHolder{
        TextView avVehicule;
    }


    public Vehicule getItem(int position) {
        return listVehicule.get(position);
    }
}
