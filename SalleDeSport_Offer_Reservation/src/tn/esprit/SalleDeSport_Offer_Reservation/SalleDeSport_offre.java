/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Offer;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Reservation_Offer;
import tn.esprit.SalleDeSport_Offer_Reservation.Services.Reservation_Offer_Service;
import tn.esprit.SalleDeSport_Offer_Reservation.Utiles.MyConnection;

/**
 *
 * @author chayma2
 */
public class SalleDeSport_offre {

    public static void main(String[] args) throws ParseException {
             /*===================================================*/
                        //   Teseter connectiviter     //
        MyConnection.getInstance(); 
        MyConnection.getInstance();
        /*===================================================*/
                        //   Ajouter Offer     //
            //Offer_Service serviceOffer = new Offer_Service();
            //Offer offer=new Offer();
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
      Reservation_Offer_Service RS = new Reservation_Offer_Service(); 
          Reservation_Offer R = new Reservation_Offer();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateReservation = sdf.parse("2023-05-19");
        R.setDateReservation(dateReservation); 
        R.setIdUser(1001); 
        //Offer offer = new Offer();
        //offer.setIdOffer(3);
        //RS.ajouterReservation(R, offer);
                //System.out.println(RS.fetchWithIdReservation(2));
        //System.out.println(RS.fetchReservation());
        //RS.supprimerReservation(10);
  
        int id=1;
        int idOffer=1;

    RS.modifierReservation(R,id,idOffer);

    

        

  
  
    } 
    
    
}
