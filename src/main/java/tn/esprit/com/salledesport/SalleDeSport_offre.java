/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tn.esprit.com.salledesport;

import tn.esprit.com.salledesport.Entities.Reservation;
import tn.esprit.com.salledesport.Services.Offer_Service;
import tn.esprit.com.salledesport.Services.Reservation_Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static tn.esprit.com.salledesport.Services.Reservation_Service.offerExists;

/**
 *
 * @author chayma2
 */
public class SalleDeSport_offre {

    public static void main(String[] args) {
        
      //  MyConnection.getInstance(); 
      //  MyConnection.getInstance();
        /*===================================================*/
        //test ajout Offer
        //Offer o = new Offer("Offer_Couplel","Profiter Notre Offer pour les couples",120);
        Offer_Service s = new Offer_Service();
        //s.ajouterOffer(o);
        //test afficher_Offer
        //System.out.println(s.afficherOffer());
        //s.supprimerOffer(3);
        //Offer o1 = new Offer(2,"Offer_Kids","Profiter Notre Offer pour kids",100);
        //s.ajouterOffer(o1);
        //s.modifierOffer(o1);
        
        /*===================================================*/
        
        //ajouter Reservation
        Reservation_Service RS = new Reservation_Service(); 
          
    Reservation R = new Reservation();
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    try {
        Date dateReservation = sdf.parse("2023-06-14");
        R.setDateReservation(dateReservation); 
    } catch (ParseException e) {
        e.printStackTrace();
    }
     R.setIdUser(1246); 
       int idOffer = 1; 
        R.setIdOffer(idOffer); 
        if (offerExists(idOffer)) {
            RS.ajouterReservation(R, idOffer);}
         
        else {
        System.out.println("Offer with idOffer " + idOffer + " does not exist.");
        }
        
  //System.out.println(RS.afficherReservation());
  //RS.supprimerReservation(6);
  
   //RS.modifierOffer(R);
        

  
  
    } 
    
    
}
