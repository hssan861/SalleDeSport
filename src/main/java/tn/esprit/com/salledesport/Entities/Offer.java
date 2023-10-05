/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.com.salledesport.Entities;

/**
 *
 * @author chayma2
 */



import java.util.Objects;
import java.util.Set;

public class Offer {

    private Integer idOffer;
    private String titleOffer;
    private String descriptionOffer;
    private float prix; 
    //public Set<Inscription> inscriptions;

    public Offer() {
    }

    public Offer(Integer idOffer, String titleOffer, String descriptionOffer, float prix) {
        this.idOffer = idOffer;
        this.titleOffer = titleOffer;
        this.descriptionOffer = descriptionOffer;
        this.prix = prix;
    }

    public Offer(String titleOffer, String descriptionOffer, float prix) {
        this.titleOffer = titleOffer;
        this.descriptionOffer = descriptionOffer;
        this.prix = prix;
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

    

    @Override
    public String toString() {
        return "Offer{" + "idOffer=" + idOffer + ", titleOffer=" + titleOffer + ", descriptionOffer=" + descriptionOffer + ", prix=" + prix + '}';
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






    

