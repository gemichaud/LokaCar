package fr.eni.lokacar.BLL;

import android.content.Context;

import java.util.List;

import fr.eni.lokacar.BO.Location;
import fr.eni.lokacar.DAL.DAO.LocationDAO;

public class LocationManager {

    public List<Location> selectAllEC(Context c) {

        LocationDAO locationDAO = new LocationDAO(c);
        return locationDAO.selectCurrentLocation();
    }
}
