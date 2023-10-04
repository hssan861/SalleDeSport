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
public class Event {
    //var
    private int idEvent;
    private String titreEvent,nomCoach,typeEvent,adresseEvent;
    private Date dateEvent;
    private double PrixEvent;
   //private admin idAdmin;
    
    
    //constructor
    public Event() {
    }

    public Event(String titreEvent, String nomCoach, String typeEvent, String adresseEvent, Date dateEvent, double PrixEvent) {
        this.titreEvent = titreEvent;
        this.nomCoach = nomCoach;
        this.typeEvent = typeEvent;
        this.adresseEvent = adresseEvent;
        this.dateEvent = dateEvent;
        this.PrixEvent = PrixEvent;
    }

    public Event(int idEvent, String titreEvent, String nomCoach, String typeEvent, String adresseEvent, Date dateEvent, double PrixEvent) {
        this.idEvent = idEvent;
        this.titreEvent = titreEvent;
        this.nomCoach = nomCoach;
        this.typeEvent = typeEvent;
        this.adresseEvent = adresseEvent;
        this.dateEvent = dateEvent;
        this.PrixEvent = PrixEvent;
    }

    //getters and setters
    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getTitreEvent() {
        return titreEvent;
    }

    public void setTitreEvent(String titreEvent) {
        this.titreEvent = titreEvent;
    }

    public String getNomCoach() {
        return nomCoach;
    }

    public void setNomCoach(String nomCoach) {
        this.nomCoach = nomCoach;
    }

    public String getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(String typeEvent) {
        this.typeEvent = typeEvent;
    }

    public String getAdresseEvent() {
        return adresseEvent;
    }

    public void setAdresseEvent(String adresseEvent) {
        this.adresseEvent = adresseEvent;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    public double getPrixEvent() {
        return PrixEvent;
    }

    public void setPrixEvent(double PrixEvent) {
        this.PrixEvent = PrixEvent;
    }
    
    //Display

    @Override
    public String toString() {
        return "Event{" + "idEvent=" + idEvent + ", titreEvent=" + titreEvent + ", nomCoach=" + nomCoach + ", typeEvent=" + typeEvent + ", adresseEvent=" + adresseEvent + ", dateEvent=" + dateEvent + ", PrixEvent=" + PrixEvent + '}';
    }

    
   
}
