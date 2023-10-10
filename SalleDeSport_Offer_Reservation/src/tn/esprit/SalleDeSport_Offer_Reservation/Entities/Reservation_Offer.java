/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation.Entities;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author chayma2
 */
public class Reservation_Offer {
    private Integer idReservation;
    private Integer idUser;
    private Date dateReservation;
    
    private Set<Offer> offer;

    public Reservation_Offer() {
    }

    public Reservation_Offer(Integer idReservation, Integer idUser, Date dateReservation) {
        this.idReservation = idReservation;
        this.idUser = idUser;
        this.dateReservation = dateReservation;
    }
    

    public Reservation_Offer(Integer idUser, Date dateReservation) {
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

    public Set<Offer> getOffer() {
        return offer;
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

    public void setOffer(Set<Offer> offer) {
        this.offer = offer;
    }

    
    

   

            @Override
         public String toString() {
             StringBuilder stringBuilder = new StringBuilder();
             stringBuilder.append("Reservation_Offer{")
                          .append("idReservation=").append(idReservation)
                          .append(", idUser=").append(idUser)
                          .append(", dateReservation=").append(dateReservation)
                          .append(", offers=[");

             for (Offer o : offer) {
                 stringBuilder.append(o.toString()); 
             }

             if (!offer.isEmpty()) {
                 stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
             }

             stringBuilder.append("]}\n");

             return stringBuilder.toString();
         }

    /*@Override
    public String toString() {
        return "Reservation_Offer{" + 
                "idReservation=" + idReservation + 
                ", idUser=" + idUser + 
                ", dateReservation=" + dateReservation + 
                ", offer=" + offer + '}'+
                "\n";
    }*/
    


    
    

    

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
        final Reservation_Offer other = (Reservation_Offer) obj;
        if (!Objects.equals(this.idReservation, other.idReservation)) {
            return false;
        }
        if (!Objects.equals(this.idUser, other.idUser)) {
            return false;
        }
        return Objects.equals(this.dateReservation, other.dateReservation);
    }
       
    
}
