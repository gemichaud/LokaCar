package fr.eni.lokacar.BLL;

import android.content.Context;

import java.util.List;
import java.util.UUID;

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

    public List<Client> selectAllClient() {

        ClientDAO cDao = new ClientDAO(this.ctx);

        return cDao.selectAll();
    }

    public Client selectClientById(UUID id) {
        ClientDAO cDao = new ClientDAO(this.ctx);
        return cDao.selectClientById(id);
    }

    public boolean updateClient(Client cliUpdate) {
        ClientDAO cDao = new ClientDAO(ctx);
        return cDao.update(cliUpdate);

    }

    public List<Client> searchByName(String name) {
        ClientDAO cDao = new ClientDAO(ctx);
        return cDao.searchByName(name);
    }
}
