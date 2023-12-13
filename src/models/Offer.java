/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import services.Offer_Service;
/**
 *
 * @author chayma2
 */
public class Offer {
     private Integer idOffer;
    private String titleOffer;
    private String descriptionOffer;
    private float prix;
    private Date dateDebOffer;
    private Date dateFinOffer;
    private String img;
    private int nb_reservation;
    private int nb_max_reservation;
    private Set<Reservation_offer> reservations;
    
    
    public Offer(String titleOffer, String descriptionOffer, float prix, Date dateDebOffer, Date dateFinOffer, String img, int nb_max_reservation) {
        this.titleOffer = titleOffer;
        this.descriptionOffer = descriptionOffer;
        this.prix = prix;
        this.dateDebOffer = dateDebOffer;
        this.dateFinOffer = dateFinOffer;
        this.img = img;
        this.nb_max_reservation = nb_max_reservation;
    }
    
    public Offer(int nb_max_reservation) {
        this.nb_max_reservation = nb_max_reservation;
        this.nb_reservation = 0; // Initially, no places are reserved
    }
    public Offer() {}

    public Offer(Integer idOffer, String titleOffer, String descriptionOffer, float prix, Date dateDebOffer, Date dateFinOffer, String img, int nb_reservation, int nb_max_reservation) {
        this.idOffer = idOffer;
        this.titleOffer = titleOffer;
        this.descriptionOffer = descriptionOffer;
        this.prix = prix;
        this.dateDebOffer = dateDebOffer;
        this.dateFinOffer = dateFinOffer;
        this.img = img;
        this.nb_reservation = nb_reservation;
        this.nb_max_reservation = nb_max_reservation;
    }
   
    //Les getter
    public Integer getIdOffer() {
        return idOffer;
    }
    public String getTitleOffer() {
        return titleOffer;
    }
    public String getDescriptionOffer() {
        return descriptionOffer;
    }
    public float getPrix() {
        return prix;
    }
    public Date getDateDebOffer() {
        return dateDebOffer;
    }
    public Date getDateFinOffer() {
        return dateFinOffer;
    }

    public String getImg() {
        return img;
    }

    public int getNb_reservation() {
        return nb_reservation;
    }

    public int getNb_max_reservation() {
        return nb_max_reservation;
    }
    
    
    public Set<Reservation_offer> getReservations() {
        return reservations;
    }
    

    
    //Les setter
    public void setIdOffer(Integer idOffer) {
        this.idOffer = idOffer;
    }
    public void setTitleOffer(String titleOffer) {
        this.titleOffer = titleOffer;
    }
    public void setDescriptionOffer(String descriptionOffer) {
        this.descriptionOffer = descriptionOffer;
    }
    public void setPrix(float prix) {
        this.prix = prix;
    }
    public void setDateDebOffer(Date dateDebOffer) {
        this.dateDebOffer = dateDebOffer;
    }
    public void setDateFinOffer(Date dateFinOffer) {
        this.dateFinOffer = dateFinOffer;
    }

    public void setReservations(Set<Reservation_offer> reservations) {
        this.reservations = reservations;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setNb_reservation(int nb_reservation) {
        this.nb_reservation = nb_reservation;
    }

    public void setNb_max_reservation(int nb_max_reservation) {
        this.nb_max_reservation = nb_max_reservation;
    }
    
    public int incrementNombreReserves() {
            return nb_reservation++;
        }
    //Offer_Service s = new Offer_Service();
    
    
            @Override
        public String toString() {
           StringBuilder stringBuilder = new StringBuilder();
           stringBuilder.append("Offer{")
                        .append(" titleOffer=").append(titleOffer)
                        .append(", descriptionOffer=").append(descriptionOffer)
                        .append(", prix=").append(prix)
                        .append(", dateDebOffer=").append(dateDebOffer)
                        .append(", dateFinOffer=").append(dateFinOffer)
                        .append(", nb_reservation=").append(nb_reservation)
                        .append(", nb_max_reservation=").append(nb_max_reservation)
                        //.append(", Nombre reservation=").append(s.reservationCount(idOffer))
                        .append('}')
                        .append("\n");

            return stringBuilder.toString();
        }

   

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.idOffer);
        hash = 47 * hash + Objects.hashCode(this.titleOffer);
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
        final Offer other = (Offer) obj;
        if (!Objects.equals(this.titleOffer, other.titleOffer)) {
            return false;
        }
        return Objects.equals(this.idOffer, other.idOffer);
    }
    
    
    
}
