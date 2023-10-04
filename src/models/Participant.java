/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author rayen
 */
public class Participant {
    
    //var
    private int idUser;
    private int idEvent;
    private int numTel;
    
    //constructor

    public Participant(int idEvent, int numTel) {
        this.idEvent = idEvent;
        this.numTel = numTel;
    }

    public Participant(int idUser,int idEvent, int numTel) {
        this.idUser = idUser;
        this.idEvent = idEvent;
        this.numTel = numTel;
    }

    public Participant() {
        
    }
     
    //getters and setters

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }
    
    //Display

    @Override
    public String toString() {
        return "Participant{" + "idUser=" + idUser + ", idEvent=" + idEvent + ", numTel=" + numTel + '}';
    }
    
    
    
}
