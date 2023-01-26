package com.soft.conseilagricole.entities;

import java.util.Date;


public class Actualite {
    public int getIdActualite() {
        return idActualite;
    }

    public void setIdActualite(int idActualite) {
        this.idActualite = idActualite;
    }

    public String getUidActualite() {
        return uidActualite;
    }

    public void setUidActualite(String uidActualite) {
        this.uidActualite = uidActualite;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public boolean isView() {
        return isView;
    }

    public void setView(boolean view) {
        isView = view;
    }

    private int idActualite;
    private String uidActualite;
    private String titre;
    private String description;
    private Date datePublication;
    private boolean isView;
}
