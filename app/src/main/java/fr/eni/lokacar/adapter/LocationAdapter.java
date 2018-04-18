package fr.eni.lokacar.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.eni.lokacar.BO.Location;
import fr.eni.lokacar.R;

public class LocationAdapter extends ArrayAdapter<Location> {

    private List<Location> listLocation;
    private Context c;
    private int res;

    public LocationAdapter(@NonNull Context context, int resource, @NonNull List<Location> objects) {
        super(context, resource, objects);
        this.listLocation = objects;
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
            viewHolder.avClient = (TextView)convertView.findViewById(R.id.lstClient);
            viewHolder.avImmat = (TextView)convertView.findViewById(R.id.lstVehicule);
            viewHolder.avDateDebut = (TextView)convertView.findViewById(R.id.lstDateDeDebut);
            viewHolder.avDateFin = (TextView)convertView.findViewById(R.id.lstDateDeFin);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Location location = getItem(position);

        viewHolder.avClient.setText(location.getClient().getPrenom() + " " + location.getClient().getNom());
        viewHolder.avImmat.setText(location.getVehicule().getImmatriculation());
        viewHolder.avDateDebut.setText(location.getDateDebut().toString());
        viewHolder.avDateFin.setText(location.getDateFinPrevu().toString());

        return convertView;

    }


    static class ViewHolder{
        TextView avClient;
        TextView avImmat;
        TextView avDateDebut;
        TextView avDateFin;
    }


    public Location getItem(int position) {
        return listLocation.get(position);
    }
}
