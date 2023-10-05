/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entities;

import java.util.Date;

/**
 *
 * @author hama
 */


import java.util.Date;

public class Commande {
    private int id;
    private int id_prd; // Clé étrangère liée au produit
    private Date date;
    private String adresse;
    private String type;
    private int id_user; // Clé étrangère liée à l'utilisateur

    // Constructeur par défaut
    public Commande() {
    }

    // Constructeur avec paramètres
    public Commande(int id, int id_prd, Date date, String adresse, String type, int id_user) {
        this.id = id;
        this.id_prd = id_prd;
        this.date = date;
        this.adresse = adresse;
        this.type = type;
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPrd() {
        return id_prd;
    }

    public void setIdPrd(int id_prd) {
        this.id_prd = id_prd;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdUser() {
        return id_user;
    }

    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", id_prd=" + id_prd + ", date=" + date + ", adresse=" + adresse + ", type=" + type + ", id_user=" + id_user + '}';
    }
    
}
    