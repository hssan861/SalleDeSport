/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tn.esprit.com.salledesport;

import tn.esprit.com.salledesport.Entities.Reservation_Offer;
import tn.esprit.com.salledesport.Services.Offer_Service;
import tn.esprit.com.salledesport.Services.Reservation_Offer_Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import tn.esprit.com.salledesport.Entities.Offer;

/**
 *
 * @author chayma2
 */
public class SalleDeSport_offre {

    public static void main(String[] args) {
        
          /*===================================================*/
                        //   Teseter connectiviter     //
      //  MyConnection.getInstance(); 
      //  MyConnection.getInstance();
        /*===================================================*/
                        //   Ajouter Offer     //
            Offer_Service serviceOffer = new Offer_Service();
            Offer offer=new Offer();
            //1
        // Convertir la chaîne de caractères en objet de type Date
        /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          Date date_resDebut = dateFormat.parse("2023-09-30 15:30:00");
          Date date_resFin = dateFormat.parse("2023-09-30 15:30:00");
         // Créer une instance de la classe Reservation avec la date de réservation convertie
         Offer o = new Offer("Profiter Notre Offer pour les enfants",500,date_resDebut,date_resFin);*/
         /*serviceOffer.ajouterOffer(o,"Offer_Couple");*/
            //2
        /*Date date_resDebut2 = dateFormat.parse("2023-11-01 15:30:00");
          Date date_resFin2 = dateFormat.parse("2023-12-30 15:30:00");
          Offer o = new Offer("Offer_Couple","Profiter Notre Offer pour les couples",120,date_resDebut2,date_resFin2);
          Offer_Service.ajouterOffer(o);*/
                        // afficherOffer     //
        //System.out.println(serviceOffer.afficherOffer());
        //System.out.println(serviceOffer.getById(2));

                       // SupprimerrOffer     //
                       /*int idOffer=5;
                       if(serviceOffer.supprimerOffer(idOffer)==false)
                       {        System.out.println("Offer with idOffer " + idOffer + " does not exist.");}*/
                        // ModifierOffer     //   
             /*           int id=1;
        Date date_resDebut = dateFormat.parse("2023-09-30 15:30:00");
          Date date_resFin = dateFormat.parse("2023-09-30 15:30:00");
         Offer o = new Offer("Offer_Couple","Profiter Notre Offer pour les couples",300,date_resDebut,date_resFin);
        serviceOffer.modifierOffer(o,id);*/
             //serviceOffer.modifierOffer(o, 7);
        
        /*===================================================*/
        
        //ajouter Reservation
     /* Reservation_Offer_Service RS = new Reservation_Offer_Service(); 
          Reservation_Offer R = new Reservation_Offer();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateReservation = sdf.parse("2023-05-18");
        R.setDateReservation(dateReservation); 
        R.setIdUser(1200); */
                //int idOffer = 2; 
       //RS.ajouterReservation(R, idOffer);
        //System.out.println(RS.afficherReservation());
  //RS.supprimerReservation(6);
  
      /*  int id=1;
        int idOffer=1;

    if(RS.modifierReservation(R,id,idOffer)==false){System.out.println("Aucune modification effectuée");}*/
     // SupprimerrOffer     //
                   /*    int idReservation=2;
                       if(RS.supprimerReservation(idReservation)==false)
                       {        System.out.println("Offer with idOffer " + idReservation + " does not exist.");}*/
    

        

  
  
    } 
    
    
}
