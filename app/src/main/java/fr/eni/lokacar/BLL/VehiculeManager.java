package fr.eni.lokacar.BLL;

import android.content.Context;

import java.util.List;

import fr.eni.lokacar.BO.Client;
import fr.eni.lokacar.BO.Vehicule;
import fr.eni.lokacar.DAL.DAO.ClientDAO;
import fr.eni.lokacar.DAL.DAO.VehiculeDAO;

public class VehiculeManager {

    private Context ctx;

    public VehiculeManager(Context c ){
        this.ctx = c;
    }
    public  void insertVehicule(Vehicule v){

        VehiculeDAO vDao = new VehiculeDAO(ctx);
        vDao.insertVehicule(v);
    }

    public List<Vehicule> selectAllVehicule(){
        VehiculeDAO vDao = new VehiculeDAO(ctx);

        return vDao.selectAll();

    }
}
