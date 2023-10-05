/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.com.salledesport.Entities;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author chayma2
 */
public class Reservation {
    private Integer idReservation;
    private Integer idUser;
    private Date dateReservation;
    
    private Integer idOffer;

    public Reservation() {
    }

    public Reservation(Integer idReservation, Integer idUser, Date dateReservation, Integer idOffer) {
        this.idReservation = idReservation;
        this.idUser = idUser;
        this.dateReservation = dateReservation;
        this.idOffer = idOffer;
    }
    

    public Reservation(Integer idUser, Date dateReservation, Integer idOffer) {
        this.idUser = idUser;
        this.dateReservation = dateReservation;
        this.idOffer = idOffer;
    }

    public Reservation(Integer idUser, Date dateReservation) {
        this.idUser = idUser;
        this.dateReservation = dateReservation;
    }

    

    public Reservation(Integer idReservation, Integer idUser, Date dateReservation) {
        this.idReservation = idReservation;
        this.idUser = idUser;
        this.dateReservation = dateReservation;
    }

   
    
    

    //Les getter
  

    public Integer getIdReservation() {
        return idReservation;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public Integer getIdOffer() {
        return idOffer;
    }
    

 

   
    //Les setter

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }
       public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public void setIdOffer(Integer idOffer) {
        this.idOffer = idOffer;
    }

    @Override
    public String toString() {
        return "Reservation_Offer{" + "idReservation=" + idReservation + ", idUser=" + idUser + ", dateReservation=" + dateReservation + ", idOffer=" + idOffer + '}';
    }
    
    

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.idReservation);
        hash = 11 * hash + Objects.hashCode(this.idUser);
        hash = 11 * hash + Objects.hashCode(this.dateReservation);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (!Objects.equals(this.idReservation, other.idReservation)) {
            return false;
        }
        if (!Objects.equals(this.idUser, other.idUser)) {
            return false;
        }
        return Objects.equals(this.dateReservation, other.dateReservation);
    }
       
    
}
