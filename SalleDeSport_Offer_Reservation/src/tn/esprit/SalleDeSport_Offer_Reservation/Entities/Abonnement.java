/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation.Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author chayma2
 */
public class Abonnement {
    
    int idUser;
    int idAbonement;
    LocalDateTime dateAbonnement;
    TypeAbonnement typeAbon;

    public Abonnement() {
    }

    
    
    public Abonnement(int idUser, int idAbonement, LocalDateTime dateAbonnement, TypeAbonnement typeAbon) {
        this.idUser = idUser;
        this.idAbonement = idAbonement;
        this.dateAbonnement = dateAbonnement;
        this.typeAbon = typeAbon;
    }

    public Abonnement(int idUser, LocalDateTime dateAbonnement, TypeAbonnement typeAbon) {
        this.idUser = idUser;
        this.dateAbonnement = dateAbonnement;
        this.typeAbon = typeAbon;
    }
    

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdAbonement(int idAbonement) {
        this.idAbonement = idAbonement;
    }

    public void setDateAbonnement(LocalDateTime dateAbonnement) {
        this.dateAbonnement = dateAbonnement;
    }

    public void setTypeAbon(TypeAbonnement typeAbon) {
        this.typeAbon = typeAbon;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdAbonement() {
        return idAbonement;
    }

    public LocalDateTime getDateAbonnement() {
        return dateAbonnement;
    }

    public TypeAbonnement getTypeAbon() {
        return typeAbon;
    }

    @Override
    public String toString() {
        return "Abonnement{" + 
                "idUser=" + idUser + 
                ", idAbonement=" + idAbonement + 
                ", dateAbonnement=" + dateAbonnement + 
                ", typeAbon=" + typeAbon + '}'+ 
                "\n";
    }
    
    
    
    
    
}
