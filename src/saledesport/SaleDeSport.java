/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saledesport;

import java.time.LocalDate;
import java.util.Date;
import models.Categorie;
import models.Equipement;
import service.EquipementService;
import service.CategorieService;
 

public class SaleDeSport {

    /**
     * @param args the command line arguments
     * 
     */
    //public EquipementService Equipe;
    public static void main(String[] args) {
        // TODO code application logic here
     /* Date d=new Date();
        Equipement e =new Equipement("qlter",10,d,(float)5.2,1);
        EquipementService es=new EquipementService ();
        es.ajouterEquipement(e);
        System.out.println(es.afficherEquipement());*/

     //**Categorie c=new Categorie("NO","vg");
    // Categorie CtoUpdate=new Categorie();
    // CtoUpdate.setIdCategorie(1);
     //CtoUpdate.setNomCategorie("said");
    // CtoUpdate.setDescriptionCategorie("brahmi");
    //Categorie CtoDelete=new Categorie();
    //CtoDelete.setIdCategorie(1);
    // CategorieService cs=new CategorieService();
     //c.ajouterCategorie(c);
    //c.modifier(CtoUpdate);
    //cs.supprimer(CtoDelete);
    // System.out.println(cs.afficherCategorie());
    
    
    //Date dateAchat1 = new Date(122, 4, 20);
   // Date dt ;
  
   
   
   CategorieService s = new CategorieService();
   
   // ajouter categoire
    Categorie c=new Categorie("samir","said");
    s.ajouterCategorie(c);
 
 
 // delete categoire
 Categorie det=new Categorie();
    det.setIdCategorie(5);
   s.supprimer(det);
   
 // update categoire
 Categorie updateC = new Categorie();
 updateC.setIdCategorie(4);
 updateC.setNomCategorie("meher test");
 updateC.setDescriptionCategorie("teste le champ description");
 s.modifier(updateC);
 
 //affichier data categoire
 s.afficherCategorie();
 
 // private int ID;
 



   
       //ajouter  equipement
        Equipement ep = new Equipement("Vélo d'exercice", 30, "12-12-2023", 700, 2);
        EquipementService es = new EquipementService();
        es.ajouterEquipement( ep);
   
       //supprimer
       // Equipement e1=new Equipement();
       // e1.setID(1);
       // es.supprimer(e1);
      //modifier equipement
      Equipement e2=new Equipement();
      e2.setID(1);
      e2.setNom("Vélo d'exercice");
      e2.setQuantite(30);
      e2.setDateAchat("12-12-23");
      e2.setPrixAchat(700);
      e2.setIdCategorie(2);
      es.modifierEquipement(e2);
      //afficher equipement
      es.afficherEquipement();
}
 }
