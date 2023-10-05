/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entities;

import java.util.Date;

/**
 *
 * @author hama
 */
public class Produit {
      
    private int id;
    private int idAdmin;
    private String titre;
    private String image;
    private Date date;
    private String description;
    private String categorie;

    // Constructeur par défaut
    public Produit() {
    }

    // Constructeur avec paramètres
    

    public Produit(int idAdmin, String titre, String image, Date date, String description, String categorie) {
        this.idAdmin = idAdmin;
        this.titre = titre;
        this.image = image;
        this.date = date;
        this.description = description;
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", idAdmin=" + idAdmin + ", titre=" + titre + ", image=" + image + ", date=" + date + ", description=" + description + ", categorie=" + categorie + '}';
    }

    

}

  

  

    
