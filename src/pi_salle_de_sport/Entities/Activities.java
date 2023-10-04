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
    private int id_coach;
    private String categorie;
    private String description;
    private String salle;

    public Activities( String titre, Date date_deb, Date date_fin, int id_coach, String categorie, String description,String salle) {
        
        this.titre = titre;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.id_coach = id_coach;
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

    public int getIdCoach() {
        return id_coach;
    }

    public void setIdCoach(int id_coach) {
        this.id_coach = id_coach;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
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

    public void ajouter() {

    }

    public void modifier(String nouveauTitre, String nouvelleDescription) {

    }

    public void supprimer() {

    }

    public void consulter() {
        System.out.println("Titre : " + this.titre);
        System.out.println("Description : " + this.description);
        System.out.println("Catégorie : " + this.categorie);
        System.out.println("Dates : " + this.date_deb.toString() + " - " + this.date_fin.toString());
        System.out.println("ID Coach : " + this.id_coach);
    }
    
}
