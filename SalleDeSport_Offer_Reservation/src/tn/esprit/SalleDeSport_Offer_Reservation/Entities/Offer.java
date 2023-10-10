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
public class Offer {

    private Integer idOffer;
    private String titleOffer;
    private String descriptionOffer;
    private float prix;
    private Date dateDebOffer;
    private Date dateFinOffer;
    private Set<Reservation_Offer> reservations;
    //public Set<Inscription> inscriptions;

    public Offer() {
    }
    

    public Offer(Integer idOffer, String titleOffer, String descriptionOffer, float prix, Date dateDebOffer, Date dateFinOffer) {
        this.idOffer = idOffer;
        this.titleOffer = titleOffer;
        this.descriptionOffer = descriptionOffer;
        this.prix = prix;
        this.dateDebOffer = dateDebOffer;
        this.dateFinOffer = dateFinOffer;
    }

    public Offer(String descriptionOffer, float prix, Date dateDebOffer, Date dateFinOffer) {
        this.descriptionOffer = descriptionOffer;
        this.prix = prix;
        this.dateDebOffer = dateDebOffer;
        this.dateFinOffer = dateFinOffer;
    }
    

    public Offer(String titleOffer, String descriptionOffer, float prix, Date dateDebOffer, Date dateFinOffer) {
        this.titleOffer = titleOffer;
        this.descriptionOffer = descriptionOffer;
        this.prix = prix;
        this.dateDebOffer = dateDebOffer;
        this.dateFinOffer = dateFinOffer;
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
    public Set<Reservation_Offer> getReservations() {
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

    public void setReservations(Set<Reservation_Offer> reservations) {
        this.reservations = reservations;
    }
    

    /*@Override
    public String toString() {
        return "Offer{" + "idOffer=" + idOffer + 
                ", titleOffer=" + titleOffer + 
                ", descriptionOffer=" + descriptionOffer + 
                ", prix=" + prix + 
                ", dateDebOffer=" + dateDebOffer + 
                ", dateFinOffer=" + dateFinOffer + '}'+
                "\n";
    }*/
    @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Offer{")
      .append("idOffer=").append(idOffer)
      .append(", titleOffer=").append(titleOffer)
      .append(", descriptionOffer=").append(descriptionOffer)
      .append(", prix=").append(prix)
      .append(", dateDebOffer=").append(dateDebOffer)
      .append(", dateFinOffer=").append(dateFinOffer)
      .append('}')
      .append("\n");

    if (reservations != null && !reservations.isEmpty()) {
        sb.append("Reservations:\n");
        for (Reservation_Offer reservation : reservations) {
            sb.append("  - Reservation{idReservation=").append(reservation.getIdReservation())
              .append(", idUser=").append(reservation.getIdUser())
              .append(", dateReservation=").append(reservation.getDateReservation())
              .append("}\n");
        }
    }

    return sb.toString();
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
