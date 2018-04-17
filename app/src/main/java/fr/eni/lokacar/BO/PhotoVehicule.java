package fr.eni.lokacar.BO;

import android.os.Parcel;
import android.os.Parcelable;

public class PhotoVehicule implements Parcelable {

    private int id;
    private String path;
    private String description;
    private boolean derniere;
    private Enum<LocalisationVehicule> localisationVehiculeEnum;
    private Location location;


    public PhotoVehicule() {
    }

    public PhotoVehicule(int id, String path, String description, boolean derniere, Enum<LocalisationVehicule> localisationVehiculeEnum, Location location) {
        this.id = id;
        this.path = path;
        this.description = description;
        this.derniere = derniere;
        this.localisationVehiculeEnum = localisationVehiculeEnum;
        this.location = location;
    }


    protected PhotoVehicule(Parcel in) {
        id = in.readInt();
        path = in.readString();
        description = in.readString();
        derniere = in.readByte() != 0;
        location = in.readParcelable(Location.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(path);
        dest.writeString(description);
        dest.writeByte((byte) (derniere ? 1 : 0));
        dest.writeParcelable(location, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PhotoVehicule> CREATOR = new Creator<PhotoVehicule>() {
        @Override
        public PhotoVehicule createFromParcel(Parcel in) {
            return new PhotoVehicule(in);
        }

        @Override
        public PhotoVehicule[] newArray(int size) {
            return new PhotoVehicule[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDerniere() {
        return derniere;
    }

    public void setDerniere(boolean derniere) {
        this.derniere = derniere;
    }

    public Enum<LocalisationVehicule> getLocalisationVehiculeEnum() {
        return localisationVehiculeEnum;
    }

    public void setLocalisationVehiculeEnum(Enum<LocalisationVehicule> localisationVehiculeEnum) {
        this.localisationVehiculeEnum = localisationVehiculeEnum;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return "PhotoVehicule{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", description='" + description + '\'' +
                ", derniere=" + derniere +
                ", localisationVehiculeEnum=" + localisationVehiculeEnum +
                ", location=" + location +
                '}';
    }
}
