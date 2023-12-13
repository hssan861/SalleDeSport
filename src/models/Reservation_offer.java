package models;

import java.time.LocalDateTime;
import java.util.Objects;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chayma2
 */
public class Reservation_offer {
     private Integer idReservation;
    private Integer idUser;
    private LocalDateTime dateReservation;
    private String codePromo;
    private Offer offer;

    public Reservation_offer() {
    }

    public Reservation_offer(Integer idReservation, Integer idUser, LocalDateTime dateReservation, String codePromo) {
        this.idReservation = idReservation;
        this.idUser = idUser;
        this.dateReservation = dateReservation;
        this.codePromo = codePromo;
    }

    
    public Reservation_offer(Integer idReservation, Integer idUser, LocalDateTime dateReservation, String codePromo, Offer offer) {
        this.idReservation = idReservation;
        this.idUser = idUser;
        this.dateReservation = dateReservation;
        this.codePromo = codePromo;
        this.offer = offer;
    }

    public Reservation_offer(Integer idUser, LocalDateTime dateReservation, String codePromo, Offer offer) {
        this.idUser = idUser;
        this.dateReservation = dateReservation;
        this.codePromo = codePromo;
        this.offer = offer;
    }

    public Reservation_offer(Integer idUser, LocalDateTime dateReservation, String codePromo) {
        this.idUser = idUser;
        this.dateReservation = dateReservation;
        this.codePromo = codePromo;
    }

    

    //Les getter
  

    public Integer getIdReservation() {
        return idReservation;
    }

    public LocalDateTime getDateReservation() {
        return dateReservation;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public Offer getOffer() {
        return offer;
    }

    public String getCodePromo() {
        return codePromo;
    }

    
    

    
    

   
    //Les setter

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }
       public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public void setCodePromo(String codePromo) {
        this.codePromo = codePromo;
    }

    
    
    
    

   

            @Override
         public String toString() {
             StringBuilder stringBuilder = new StringBuilder();
             stringBuilder.append("Reservation_Offer{")
                          .append(" idUser=").append(idUser)
                          .append(" dateReservation=").append(dateReservation)
                          .append(" CodePromo=").append(codePromo)
                          .append(" offers=[");

             stringBuilder.append(offer.getTitleOffer()); 

             stringBuilder.append("]}\n");

             return stringBuilder.toString();
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
        final Reservation_offer other = (Reservation_offer) obj;
        if (!Objects.equals(this.idReservation, other.idReservation)) {
            return false;
        }
        if (!Objects.equals(this.idUser, other.idUser)) {
            return false;
        }
        return Objects.equals(this.dateReservation, other.dateReservation);
    }
       
}
