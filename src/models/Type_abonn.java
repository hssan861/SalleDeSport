package models;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chayma2
 */
public class Type_abonn {
   int id;
   String type, description;
   int nb_abonnement=0;

    public Type_abonn() {
    }

    public Type_abonn(int id, String type, String description, int nb_abonnement) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.nb_abonnement = nb_abonnement;
    }

    public Type_abonn(String type, String description, int nb_abonnement) {
        this.type = type;
        this.description = description;
        this.nb_abonnement = nb_abonnement;
    }

    public Type_abonn(int id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }
    
    

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getNb_abonnement() {
        return nb_abonnement;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNb_abonnement(int nb_abonnement) {
        this.nb_abonnement = nb_abonnement;
    }
    
    

    @Override
    public String toString() {
        return "TypeAbonn{" + 
                ", type=" + type + 
                ", description=" + description + 
                ", nb_abonnement=" + nb_abonnement + '}'+ 
                "\n";
    }
   
   
    
}
