/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author rayen
 */
public class Participation {
    
    //var
    private int idPart;
    private int idUser;
    private Event event;
    private Date datePart;

    public Participation() {
    }

    public Participation(int idUser, Date datePart) {
        this.idUser = idUser;
        this.datePart = datePart;
    }

  
    public Participation(int idUser,Event event, Date datePart) {
        this.idUser = idUser;
        this.event = event;
        this.datePart = datePart;
    }

    public Participation(int idPart, int idUser, Event event, Date datePart) {
        this.idPart = idPart;
        this.idUser = idUser;
        this.event = event;
        this.datePart = datePart;
    }

    public int getIdPart() {
        return idPart;
    }

    public void setIdPart(int idPart) {
        this.idPart = idPart;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getDatePart() {
        return datePart;
    }

    public void setDatePart(Date datePart) {
        this.datePart = datePart;
    }

    @Override
    public String toString() {
        return "Participation{" + "idPart=" + idPart + ", idUser=" + idUser + ", Event=" + event + ", datePart=" + datePart + '}';
    }
    
    
    
}
