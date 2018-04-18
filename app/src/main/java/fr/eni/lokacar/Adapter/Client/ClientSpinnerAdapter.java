package fr.eni.lokacar.Adapter.Client;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = inflater.inflate(res,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.avClient = (TextView)convertView.findViewById(R.id.lstClient);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Client client = getItem(position);

        viewHolder.avClient.setText(client.getPrenom() + " " + client.getNom());


        return convertView;


    }


    static class ViewHolder{
        TextView avClient;
    }


    public Client getItem(int position) {
        return listClient.get(position);
    }
}
