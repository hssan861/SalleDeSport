/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author rayen
 */
public class Participation {
    
    //var
    private int idPart;
    //private int idUser;
    private Event event;
    private LocalDate datePart;
    private String Nom,Prenom,Ntel;

    public Participation() {
    }

    public Participation(int idPart, Event event, LocalDate datePart, String Nom, String Prenom, String Ntel) {
        this.idPart = idPart;
        this.event = event;
        this.datePart = datePart;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Ntel = Ntel;
    }

   
    public Participation(Event event, LocalDate datePart, String Nom, String Prenom, String Ntel) {
        this.event = event;
        this.datePart = datePart;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Ntel = Ntel;
    }

   

    public int getIdPart() {
        return idPart;
    }

    public void setIdPart(int idPart) {
        this.idPart = idPart;
    }

    

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDate getDatePart() {
        return datePart;
    }

    public void setDatePart(LocalDate datePart) {
        this.datePart = datePart;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getNtel() {
        return Ntel;
    }

    public void setNtel(String Ntel) {
        this.Ntel = Ntel;
    }

    @Override
    public String toString() {
        return  "event=" + event  + "       Nom=" + Nom + "        Prenom=" + Prenom  ;
    }

    public void setIdEvent(Event event) {
    }

   
   
}
