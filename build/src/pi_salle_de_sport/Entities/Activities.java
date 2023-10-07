/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_salle_de_sport.Entities;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Activities {
    private int code;
    private String titre;
    private Date date_deb;
    private Date date_fin;
    private User coach;
    private Categorie categorie;
    private String description;
    private String salle;

    public Activities( String titre, Date date_deb, Date date_fin, User coach,pi_salle_de_sport.Entities.Categorie  categorie, String description,String salle) {
        
        this.titre = titre;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.coach = coach;
        this.categorie = categorie;
        this.description = description;
        this.salle = salle;
    }

    public Activities() {

    }

    // Getters et Setters pour chaque attribut
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDateDeb() {
        return date_deb;
    }

    public void setDateDeb(Date date_deb) {
        this.date_deb = date_deb;
    }

    public Date getDateFin() {
        return date_fin;
    }

    public void setDateFin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public User getIdCoach() {
        return coach;
    }


 public void setIdCoach(User coach) {
    this.coach = coach;
}


    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    @Override
    public String toString() {
        return "Activities{" + "code=" + code + ", titre=" + titre + ", date_deb=" + date_deb + ", date_fin=" + date_fin + ", coach=" + coach + ", categorie=" + categorie + ", description=" + description + ", salle=" + salle + '}';
    }

   

   
    
}
