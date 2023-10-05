/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;




import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class Equipement {
  private int ID;
  private String Nom;
  private int Quantite;
  private String DateAchat;
  private float PrixAchat;
  private int IdCategorie; 

    public Equipement() {
    }

    public Equipement( String Nom, int Quantite, String DateAchat, float PrixAchat, int IdCategorie) {
     
        this.Nom = Nom;
        this.Quantite = Quantite;
        this.DateAchat = DateAchat;
        this.PrixAchat = PrixAchat;
        this.IdCategorie = IdCategorie;
    }

  
    

  /*  public Equipement(String Nom, int Quantite, Date DateAchat, float PrixAchat ) {
        this.Nom = Nom;
        this.Quantite = Quantite;
        this.DateAchat = DateAchat;
        this.PrixAchat = PrixAchat;
       
    } */

    /*public Equipement(String Nom, int Quantite, float PrixAchat, int IdCategorie) {
        this.Nom = Nom;
        this.Quantite = Quantite;
        this.PrixAchat = PrixAchat;
        this.IdCategorie = IdCategorie;
    }

    public Equipement(String Nom, int Quantite, Date DateAchat, float PrixAchat, int IdCategorie) {
        this.Nom = Nom;
        this.Quantite = Quantite;
        this.DateAchat = DateAchat;
        this.PrixAchat = PrixAchat;
        this.IdCategorie = IdCategorie;
    }
    public Equipement(int ID, String Nom,int Quantite, Date DateAchat, float PrixAchat, Categorie CategorieEquipement, int IdCategorie) {
        this.ID = ID;
        this.Nom = Nom;
        this.Quantite = Quantite;
        this.DateAchat = DateAchat;
        this.PrixAchat = PrixAchat;
        this.IdCategorie =IdCategorie ;
    }

    public Equipement(String vélo_dexercice, int i, LocalDate dt, int i0, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }


    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    public String getDateAchat() {
        return DateAchat;
    }

    public void setDateAchat(String DateAchat) {
        this.DateAchat = DateAchat;
    }

    public float getPrixAchat() {
        return PrixAchat;
    }

    public void setPrixAchat(float PrixAchat) {
        this.PrixAchat = PrixAchat;
    }

   

   

    public int getIdCategorie() {
        return IdCategorie;
    }

    public void setIdCategorie(int IdCategorie) {
        this.IdCategorie = IdCategorie;
    }
    
    
  
          
}



