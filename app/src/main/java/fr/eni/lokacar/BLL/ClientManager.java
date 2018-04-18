package fr.eni.lokacar.BLL;

import android.content.Context;

import java.util.List;

import fr.eni.lokacar.BO.Client;
import fr.eni.lokacar.DAL.DAO.ClientDAO;

public class ClientManager {

    private Context ctx;

    public ClientManager(Context c ){
        this.ctx = c;
    }
    public  void insertClient(Client c){

        ClientDAO cDao = new ClientDAO(ctx);
        cDao.insertClient(c);
    }

    public List<Client> selectAllClient(){
        ClientDAO cDao = new ClientDAO(ctx);

        return cDao.selectAll();

    }
}
