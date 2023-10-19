/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_salle_de_sport.Entities;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Reservation {
    private int id;
    private Date date_res;
    private User id_user;
    private Activities code;

    public Reservation(Date date_res, User id_user,Activities  code) {
        this.date_res = date_res;
        this.id_user = id_user;
        this.code = code;
    }

    public Reservation() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateRes() {
        return date_res;
    }

    public void setDateRes(Date date_res) {
        this.date_res = date_res;
    }

    public User getIdUser() {
        return id_user;
    }

    @Override
    public String toString() {
        return " date_res=" + date_res + ", user=" + id_user + ", activitites=" + code + '}';
    }

    public void setIdUser(User id_user) {
        this.id_user = id_user;
    }

    public Activities getCode() {
        return code;
    }

    public void setCode(Activities code) {
        this.code = code;
    }}

   
