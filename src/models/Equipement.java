/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;







/**
 *
 * @author Lenovo
 */
public class Equipement {
  private int IdEquipement;
  private String NomEquipement;
  private int Quantite;
  private String DateAchat;
  private float PrixAchat;
  
  private Categorie categorie; // Categorie cat;
  
  //public enum Categorie {
   // cardio, Musculation ,Poids_libres_et_halteres,quipement_de_resistanc; // Ajoutez vos catégories ici
//}

    public Equipement() {
    }

    public Equipement( String NomEquipement, int Quantite, String DateAchat, float PrixAchat, Categorie categorie) {
     
        this.NomEquipement = NomEquipement;
        this.Quantite = Quantite;
        this.DateAchat = DateAchat;
        this.PrixAchat = PrixAchat;
       
        this.categorie = categorie;
    }

  
    

    public Equipement(int IdEquipement,String NomEquipement, int Quantite, String DateAchat, float PrixAchat,int IdCategorie,Categorie categorie ) {
       this.IdEquipement=IdEquipement;
       this.NomEquipement =NomEquipement;
        this.Quantite = Quantite;
        this.DateAchat = DateAchat;
        this.PrixAchat = PrixAchat;
        
        this.categorie= categorie;
       
    } 

    public int getIdEquipement() {
        return IdEquipement;
    }

    public void setIdEquipement(int IdEquipement) {
        this.IdEquipement = IdEquipement;
    }

    public String getNomEquipement() {
        return NomEquipement;
    }

    public void setNomEquipement(String NomEquipement) {
        this.NomEquipement = NomEquipement;
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
    
    

   
    

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Equipement{" + "IdEquipement=" + IdEquipement + ", NomEquipement=" + NomEquipement + ", Quantite=" + Quantite + ", DateAchat=" + DateAchat + ", PrixAchat=" + PrixAchat +  ", categorie=" + categorie + '}';
    }

    

          
}



