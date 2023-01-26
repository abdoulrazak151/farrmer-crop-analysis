package com.soft.conseilagricole.entities;

import java.util.Date;

public class Localisation {
    private int idLocation;
    private double latitude;
    private double longitude;
    private Date dateGetLocation;

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getDateGetLocation() {
        return dateGetLocation;
    }

    public void setDateGetLocation(Date dateGetLocation) {
        this.dateGetLocation = dateGetLocation;
    }
}
