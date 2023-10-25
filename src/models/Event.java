/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author rayen
 */
public class Event {
    //var
    private int idEvent;
    private String titreEvent,nomCoach,typeEvent,adresseEvent,imgEvent;
    private LocalDate dateEvent;
    private double PrixEvent;
    private int nombrePlacesTotal;
    private int nombrePlacesReservees;
    
    
    //constructor
    public Event() {
    }

    public Event(String titreEvent, String nomCoach, String typeEvent, String adresseEvent, LocalDate dateEvent, double PrixEvent,String imgEvent) {
        this.titreEvent = titreEvent;
        this.nomCoach = nomCoach;
        this.typeEvent = typeEvent;
        this.adresseEvent = adresseEvent;
        this.dateEvent = dateEvent;
        this.PrixEvent = PrixEvent;
        this.imgEvent = imgEvent;
    }

    public Event(int idEvent, String titreEvent, String nomCoach, String typeEvent, String adresseEvent, LocalDate dateEvent, double PrixEvent,String imgEvent) {
        this.idEvent = idEvent;
        this.titreEvent = titreEvent;
        this.nomCoach = nomCoach;
        this.typeEvent = typeEvent;
        this.adresseEvent = adresseEvent;
        this.dateEvent = dateEvent;
        this.PrixEvent = PrixEvent;
        this.imgEvent = imgEvent;
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

    public LocalDate getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(LocalDate dateEvent) {
        this.dateEvent = dateEvent;
    }

    public double getPrixEvent() {
        return PrixEvent;
    }

    public void setPrixEvent(double PrixEvent) {
        this.PrixEvent = PrixEvent;
    }

    public String getImgEvent() {
        return imgEvent;
    }

    public void setImgEvent(String imgEvent) {
        this.imgEvent = imgEvent;
    }
     public Event(int nombrePlacesTotal) {
        this.nombrePlacesTotal = nombrePlacesTotal;
        this.nombrePlacesReservees = 0; // Initially, no places are reserved
    }

    public int getNombrePlacesTotal() {
        return nombrePlacesTotal;
    }

    public int getNombrePlacesReservees() {
        return nombrePlacesReservees;
    }

    public int getNombrePlacesDisponibles() {
        return nombrePlacesTotal - nombrePlacesReservees;
    }

    public void incrementNombrePlacesReservees() {
        nombrePlacesReservees++;
    }

   

    //Display

    @Override
    public String toString() {
        return   titreEvent  ;
    }

    public void setDateEvent(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
   
}
